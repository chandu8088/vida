package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

@Model(adaptables = {
        Resource.class }, resourceType = "vida/components/vida-2.0/content/modelvariantview", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ModelVariantViewModel {

    private static final Logger log = LoggerFactory.getLogger(ModelVariantViewModel.class);

    /** The resource resolver. */
    @SlingObject
    private ResourceResolver resourceResolver;

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String titleTag;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String colorChoice;

    @ValueMapValue
    private String cityDropdownLabel;

    @ValueMapValue
    private String exShowroomText;

    @ValueMapValue
    private String buyNowLabel;

    @ValueMapValue
    private String buyNowButtonUrl;

    @ValueMapValue
    private String whatsIncluded;

    @ValueMapValue
    private String productSku;

    @ValueMapValue
    private String firstIcon;

    @ValueMapValue
    private String secondIcon;

    @ValueMapValue
    private String thirdIcon;

    @ValueMapValue
    private String imageBasePath;

    @ValueMapValue
    private String imagesPerDesign;

    @ValueMapValue
    private String backgroundImagePath;

    @ValueMapValue
    private String cityDropdownPlaceholder;


    @ValueMapValue
    private String visualizerIcon;

    @ValueMapValue
    private String imageExtension;

    @ValueMapValue
    private String imageBasePathMobile;

    @ValueMapValue
    private String dataPosition;

    @ValueMapValue
    private String baseImgAlt;

    @ValueMapValue
    private String baseImgTitle;

    @ChildResource
    private List<GenericDataModel> offerCardItems;

    @ChildResource
    private List<CompareVidaBeanModel> whatsIncludedContent;

    public String getCityDropdownPlaceholder() {
        return cityDropdownPlaceholder;
    }

    public String getHeading() {
        return heading;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public String getDescription() {
        return description;
    }

    public String getColorChoice() {
        return colorChoice;
    }

    public String getCityDropdownLabel() {
        return cityDropdownLabel;
    }

    public String getExShowroomText() {
        return exShowroomText;
    }

    public String getBuyNowLabel() {
        return buyNowLabel;
    }

    public String getBuyNowButtonUrl() {
        return CommonUtils.getLinkWithHTML(buyNowButtonUrl,resourceResolver);
    }

    public String getProductSku() {
        return productSku;
    }

    public String getWhatsIncluded() {
        return whatsIncluded;
    }

    public String getFirstIcon() {
        return CommonUtils.getDMImagePathLink(firstIcon,resourceResolver);
    }

    public String getSecondIcon() {
        return CommonUtils.getDMImagePathLink(secondIcon,resourceResolver);
    }

    public String getThirdIcon() {
        return CommonUtils.getDMImagePathLink(thirdIcon,resourceResolver);
    }

    public String getImageBasePath() {
        return CommonUtils.getDMImagePathLink(imageBasePath,resourceResolver);
    }

    public String getImageExtension() {
        return imageExtension;
    }

    public String getImageBasePathMobile() {
        return CommonUtils.getDMImagePathLink(imageBasePathMobile,resourceResolver);
    }

    public List<GenericDataModel> getOfferCardItems() {
        return offerCardItems != null ? new ArrayList<>(offerCardItems) : Collections.emptyList();
    }

    public String getImagesPerDesign() {
        return imagesPerDesign;
    }

    public String getBackgroundImagePath() {
        return CommonUtils.getDMImagePathLink(backgroundImagePath,resourceResolver);
    }

    public String getVisualizerIcon() {
        return CommonUtils.getDMImagePathLink(visualizerIcon,resourceResolver);
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getBaseImgAlt() {
        return baseImgAlt;
    }

    public String getBaseImgTitle() {
        return baseImgTitle;
    }

    /**
     * Retrieves the JSON data representing product details with variant
     * information.
     *
     * @param resolver The Sling Resource Resolver.
     * @return JsonNode containing product details with variant information.
     * @throws IOException if an error occurs while retrieving the data.
     */
    public JsonNode getData(ResourceResolver resolver) throws IOException {
        JsonNode node = CommonUtils.getProductDetailsWithVariant(resolver);
        if (null != node) {
            Iterator<JsonNode> nodes = node.elements();
            while (nodes.hasNext()) {
                if (!(getProductSku().equals(nodes.next().get("sku").textValue()))) {
                    nodes.remove();
                }
            }
            return node;
        } else {
            log.error("Error retrieving product details with variant. The JsonNode object is null.");
            return null;
        }
    }

    public List<CompareVidaBeanModel> getWhatsIncludedContent() {
        return whatsIncludedContent != null ? new ArrayList<>(whatsIncludedContent) : Collections.emptyList();
    }

    /**
     * Generates JSON data for consumption by React.
     *
     * @return JSON data as a String.
     * @throws IOException if an error occurs while generating JSON data.
     */
    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("heading",getHeading());
        jsonMap.put("titleTag",getTitleTag());
        jsonMap.put("description",getDescription());
        jsonMap.put("imageBasePath",getImageBasePath());
        jsonMap.put("imageBasePathMobile",getImageBasePathMobile());
        jsonMap.put("imageExtension",getImageExtension());
        jsonMap.put("backgroundImagePath",getBackgroundImagePath());
        jsonMap.put("imagesPerDesign",getImagesPerDesign());
        jsonMap.put("colorChoice",getColorChoice());
        jsonMap.put("text1",getCityDropdownLabel());
        jsonMap.put("text2",getExShowroomText());
        jsonMap.put("buyNowLabel",getBuyNowLabel());
        jsonMap.put("buyNowButtonUrl",getBuyNowButtonUrl());
        jsonMap.put("offerCardContent",getOfferCardItems());
        jsonMap.put("cityDropdownPlaceholder",getCityDropdownPlaceholder());
        jsonMap.put("visualizerIcon",getVisualizerIcon());
        jsonMap.put("baseImgAlt",getBaseImgAlt());
        jsonMap.put("baseImgTitle",getBaseImgTitle());
        List<Map<String, Object>> iconArray = new ArrayList<>();

        Map<String, Object> firstIconMap = new HashMap<>();
        firstIconMap.put("url", getFirstIcon());
        iconArray.add(firstIconMap);

        Map<String, Object> secondIconMap = new HashMap<>();
        secondIconMap.put("url", getSecondIcon());
        iconArray.add(secondIconMap);

        Map<String, Object> thirdIconMap = new HashMap<>();
        thirdIconMap.put("url", getThirdIcon());
        iconArray.add(thirdIconMap);

        jsonMap.put("icons", iconArray);
        jsonMap.put("colors",getData(resourceResolver));

        Map<String, Object> whatsIncludedMap = new HashMap<>();
        whatsIncludedMap.put("whatsIncludedLabel", getWhatsIncluded());
        whatsIncludedMap.put("whatsIncludedContent", getWhatsIncludedContent());
        jsonMap.put("whatsIncluded",whatsIncludedMap);
        return CommonUtils.toJson(jsonMap);
    }
}
