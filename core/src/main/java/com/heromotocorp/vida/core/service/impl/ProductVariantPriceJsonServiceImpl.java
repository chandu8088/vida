package com.heromotocorp.vida.core.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.heromotocorp.vida.core.models.ProductVariantPriceMasterInfoBeanModdel;
import com.heromotocorp.vida.core.service.ProductVariantPriceJsonService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;
import com.heromotocorp.vida.core.vo.ProductInfoVO;

@Component(service = ProductVariantPriceJsonService.class, immediate = true)
@ServiceDescription("Product Variant Price Json Service")
public class ProductVariantPriceJsonServiceImpl implements ProductVariantPriceJsonService {

    /** The Constant log. */
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

    /** The price master. */
	List<ProductVariantPriceMasterInfoBeanModdel> priceMaster = new ArrayList<>();

    /** The price master map. */
	Map<String, ProductInfoVO> priceMasterMap = new HashMap<>();

    @Override
    public void productVariantPriceJson(ResourceResolver resourceResolver, Map<String, String> citypriceMap, String skuParam) {
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
                                        ? CommonUtils.rupeeFormatWithoutDecimal(effectivePrice)
                                        : StringUtils.EMPTY;
                                productVariantPriceMasterInfoBeanModdel.setEffectivePrice(effectivePrice);
                                priceMaster.add(productVariantPriceMasterInfoBeanModdel);
                                if (Objects.equals(varian_sku, skuParam)) {
                                    product.setCity(city);
                                    product.setPrice(effectivePrice);
                                    priceMasterMap.put(varian_sku, product);
                                    if (!Objects.equals(effectivePrice, StringUtils.EMPTY))
                                        citypriceMap.put(city, effectivePrice);
                                }
                            }
                        }
                    }
                }

            }
        } catch (IOException e) {
            LOG.error("IOException  occured method: readProductVariantPriceJson cause : %s", e);
        }
    }

}
