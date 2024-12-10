package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;

@Model(adaptables = {
        Resource.class }, resourceType = "vida/components/vida-2.0/content/chargingbanner", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ChargingBannerModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String variantTwoTitle;

    @ValueMapValue
    private Boolean variantTwo;

    @ValueMapValue
    private String titleTag;

    @ValueMapValue
    private String title2Tag;

    @ValueMapValue
    private String bgDesktopImage;

    @ValueMapValue
    private String bgMobileImage;

    @ValueMapValue
    private String dataPosition;

    @ChildResource
    private List<ChargingBannerBeanModel> cardsInfo;


    @SlingObject
    private ResourceResolver resolver;

    @OSGiService
    private GlobalConfig globalConfig;

    boolean enableDMFeature;

    @PostConstruct
    public void init() {
        enableDMFeature = globalConfig.enableDMFeature();
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public String getTitle2Tag() {
        return title2Tag;
    }

    public String getTitle() {
        return title != null ? title.replace("<p>", "").replace("</p>", "") : title;
    }

    public String getBgDesktopImage() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(bgDesktopImage, resolver) : bgDesktopImage;
    }

    public String getBgMobileImage() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(bgMobileImage, resolver) : bgMobileImage;
    }

    public String getVariantTwoTitle() {
        return variantTwoTitle != null ? variantTwoTitle.replace("<p>", "").replace("</p>", "") : variantTwoTitle;
    }

    public Boolean getVariantTwo() {
        return variantTwo;
    }

    public List<ChargingBannerBeanModel> getCardsInfo() {
        return cardsInfo != null ? new ArrayList<>(cardsInfo) : Collections.emptyList();
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("title", getTitle());
        jsonMap.put("titleTag", getTitleTag());
        jsonMap.put("cardsInfo", getCardsInfo());
        jsonMap.put("variantTwoTitle", getVariantTwoTitle());
        
        jsonMap.put("isVariantTwo", getVariantTwo());
        jsonMap.put("bgDesktopImage", getBgDesktopImage());
        jsonMap.put("bgMobileImage", getBgMobileImage());
        jsonMap.put("title2Tag", getTitle2Tag());
        return CommonUtils.toJson(jsonMap);
    }

}
