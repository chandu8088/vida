package com.heromotocorp.vida.core.models;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.ProductInfoVO;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PriceFetchModel {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    /** The resource resolver. */
    @SlingObject
    private ResourceResolver resourceResolver;

    @ChildResource
    private List<VariantListBeanModel> variantDetails;

    @Inject
    private String city;

    List<ProductVariantPriceMasterInfoBeanModdel> priceMaster = new ArrayList<>();


    Map<String, Map<String, String>> priceMasterMap = new HashMap<>();


    @PostConstruct
    protected void init() {   
        if(!variantDetails.isEmpty()){
            for(VariantListBeanModel variantListBeanModel : variantDetails){
                priceMasterMap.put(variantListBeanModel.getVariantSKU(),readProductVariantPriceJson(variantListBeanModel.getVariantSKU()));
            }
        }
    }
    private Map<String, String> readProductVariantPriceJson(String skuParam) {
        Map<String, String> citypriceMap = new HashMap<>();
        String location = "/content/dam/vida/config/price-master.json";
        ProductInfoVO product = new ProductInfoVO();
        Resource assetResource = resourceResolver.getResource(location);
        assert assetResource != null;
        Asset asset = assetResource.adaptTo(Asset.class);
        assert asset != null;
        Rendition original = asset.getOriginal();
        try {
            if (original != null) {
                InputStream content = original.adaptTo(InputStream.class);
                StringBuilder sb = new StringBuilder();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8));
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                Gson gson = new Gson();
                JsonElement element = gson.fromJson(sb.toString(), JsonElement.class);
                if (Objects.nonNull(element)) {
                    JsonArray jsonArray = element.getAsJsonArray();
                    if (Objects.nonNull(jsonArray)) {
                        for (JsonElement jsonElement : jsonArray) {
                            JsonObject jsonObject = jsonElement.getAsJsonObject();
                            ProductVariantPriceMasterInfoBeanModdel productVariantPriceMasterInfoBeanModdel = new ProductVariantPriceMasterInfoBeanModdel();
                            if (Objects.nonNull(jsonObject)) {
                                String varian_sku = jsonObject.get(Constants.VARIANTSKU) != null
                                        ? jsonObject.get(Constants.VARIANTSKU).getAsString()
                                        : StringUtils.EMPTY;
                                productVariantPriceMasterInfoBeanModdel.setVarinatSku(varian_sku);

                                String effectivePrice = jsonObject.get(Constants.EFFECTIVEPRICE) != null
                                        ? jsonObject.get(Constants.EFFECTIVEPRICE).getAsString()
                                        : StringUtils.EMPTY;
                                String city = jsonObject.get(Constants.CITY_PRODUCT) != null
                                        ? jsonObject.get(Constants.CITY_PRODUCT).getAsString()
                                        : StringUtils.EMPTY;
                                String cityValue = StringUtils.substringBefore(city, "~").toLowerCase();
                                city = CommonUtils.formatCityName(cityValue);
                                effectivePrice = !Objects.equals(effectivePrice, StringUtils.EMPTY)
                                        ? CommonUtils.rupeeFormat(effectivePrice) : StringUtils.EMPTY;
                                LOG.info("effective price {}",effectivePrice);
                                effectivePrice = effectivePrice.contains(".") && ! StringUtils.isEmpty(effectivePrice) ?
                                        effectivePrice.split("\\.")[0]:effectivePrice;
                                productVariantPriceMasterInfoBeanModdel.setEffectivePrice(effectivePrice);
                                priceMaster.add(productVariantPriceMasterInfoBeanModdel);
                                if (Objects.equals(varian_sku, skuParam)) {
                                    product.setCity(city);
                                    product.setPrice(effectivePrice);
                                    //priceMasterMap.put(varian_sku, product);
                                    if(!Objects.equals(effectivePrice, StringUtils.EMPTY)){
                                        citypriceMap.put(city, effectivePrice);
                                    }
                                }
                            }
                        }
                    }
                }

            }
            return citypriceMap;
        } catch (IOException e) {
            LOG.error("IOException  occured method: readProductVariantPriceJson cause : %s", e);
            return null;
        }
    }

    public List<VariantListBeanModel> getVariantDetails() {
        return variantDetails != null ? new ArrayList<>(variantDetails) : Collections.emptyList();
    }

    public Map<String, Map<String, String>> getPriceMasterMap() {
        return priceMasterMap;
    }

    public String getCity() {
		String cityValue = StringUtils.substringBefore(city, "~").toLowerCase();
		return CommonUtils.formatCityName(cityValue);
	}
}
