package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;

/**
 * This is the main ServiceOfferCard model
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceOfferCardModel {


    @ChildResource
    private List <GenericDataModel>offerCardItems;

    @ValueMapValue
    private String primaryText;

    @ValueMapValue
    private String boldText;

    @ValueMapValue
    private String secondaryText;

    @ValueMapValue
    private String variation;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String brandPageTitle;

    @ValueMapValue
    private String subheading;

    @ValueMapValue
    private String helperText;

    @ValueMapValue
    private String image;

    @ValueMapValue
    private boolean darkTheme;

    @ValueMapValue
    private int defaultOpenIndex;

    @ValueMapValue
    private boolean isHeader;

    @ValueMapValue
    private String dataPosition;

    @ValueMapValue
    private String subTitle;

    @ValueMapValue
    private String headerDesktopImage;

    @ValueMapValue
    private String headerMobileImage;

    @ValueMapValue
    private String headerImagealttext;

    @ValueMapValue
    private String headerImageTitle;

    @ValueMapValue
    private boolean varientTwo;

    @ChildResource
    private List <ServiceOfferCardBeanModel> serviceItems;

    private List <ServiceOfferCardBeanModel> serviceContentItems;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<GenericDataModel> getOfferCardItems() {
        return offerCardItems != null ? new ArrayList<>(offerCardItems) : Collections.emptyList();
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public String getBoldText() {
        return boldText;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public String getVariation() {
        return variation;
    }

    public String getTitle() {
        return title;
    }

    public String getSubheading() {
        return subheading;
    }

    public String getHelperText() {
        return helperText;
    }

    public String getImage() {
        return image;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<ServiceOfferCardBeanModel> getServiceItems() {
        return serviceItems != null ? new ArrayList<>(serviceItems) : Collections.emptyList();
    }

    public boolean getDarkTheme() {
        return darkTheme;
    }

    public int getDefaultOpenIndex() {
        return defaultOpenIndex;
    }
    public boolean isHeader() {
        return isHeader;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getHeaderDesktopImage() {
        return headerDesktopImage;
    }

    public String getHeaderMobileImage() {
        return headerMobileImage;
    }

    public String getHeaderImagealttext() {
        return headerImagealttext;
    }

    public String getHeaderImageTitle() {
        return headerImageTitle;
    }

    public boolean getVarientTwo() {
        return varientTwo;
    }

    public String getBrandPageTitle(){
        return brandPageTitle;
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("offerCardContent", getOfferCardItems());
        jsonMap.put("offersCardPrimaryText", getPrimaryText());
        jsonMap.put("offersCardBoldText", getBoldText());
        jsonMap.put("offersCardSecondaryText", getSecondaryText());
        return CommonUtils.toJson(jsonMap);
    }

    public  List<Map<String, Object>> getContentItem(List<ServiceOfferCardBeanModel> serviceContentItems) throws IOException {
        List<Map<String, Object>> contentArrayitems = new ArrayList<>();
        for (ServiceOfferCardBeanModel  serviceItem : serviceContentItems)  {
            Map<String, Object> jsonInnerMap = new HashMap<>();
            List<Map<String, Object>> contentArray = new ArrayList<>();
            Map<String, Object> contentMap = new HashMap<>();
            contentMap.put("label", serviceItem.getLabel());
            contentMap.put("knowMoreLabel", serviceItem.getKnowMoreLabel());
            contentMap.put("knowMoreLink", serviceItem.getKnowMoreLink());
            contentMap.put("newtab", serviceItem.getNewTab());
            if(serviceItem.getItemType().equals("video")) {
                contentMap.put("video", serviceItem.getVideopath());
                contentMap.put("isyouTubeVideo", serviceItem.isYouTubeVideo());
            }else{
                if (serviceItem.getDesktopImage()!=null || serviceItem.getMobileImage()!=null) {
                    contentMap.put("desktopImage", serviceItem.getDesktopImage());
                    contentMap.put("mobileImage", serviceItem.getMobileImage());
                    contentMap.put("imagealttext", serviceItem.getImagealttext());
                    contentMap.put("imageTitle", serviceItem.getImageTitle());
                }

            }
            contentArray.add(contentMap);
            jsonInnerMap.put("contentItem", contentArray);
            jsonInnerMap.put("title", serviceItem.getTitle());
            jsonInnerMap.put("videoContent",serviceItem.getItemType().equals("video")?true:false);
            contentArrayitems.add(jsonInnerMap);
        }

        return contentArrayitems;
    }

    public String getServiceJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        Map<String, Object> infoDataMap = new HashMap<>();
        infoDataMap.put("title",getTitle());
        infoDataMap.put("subheading",getSubheading());
        if(getDarkTheme()) {
            infoDataMap.put("helperText", getHelperText());
            infoDataMap.put("image", getImage());
        }
        Map<String, Object> accordianMap = new HashMap<>();
        accordianMap.put("accordianContent", getContentItem(getServiceItems()));
        jsonMap.put("accordianData",accordianMap );
        jsonMap.put("infoContent", infoDataMap);
        jsonMap.put("darkTheme",getDarkTheme());
        jsonMap.put("defaultOpenIndex",getDefaultOpenIndex());
        jsonMap.put("isHeader",isHeader());
        jsonMap.put("subTitle",getSubTitle());
        jsonMap.put("headerImageDesktop",getHeaderDesktopImage());
        jsonMap.put("headerImageMobile",getHeaderMobileImage());
        jsonMap.put("altHeaderImageText",getHeaderImagealttext());
        jsonMap.put("headerImageTitle",getHeaderImageTitle());
        jsonMap.put("variantTwo",getVarientTwo());
        jsonMap.put("title", getBrandPageTitle());

        return CommonUtils.toJson(jsonMap);
    }
}
