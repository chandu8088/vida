package com.heromotocorp.vida.core.models;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.heromotocorp.vida.core.utils.CommonUtils;

/**
 * This is the main navigationmenu model
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BannerCarouselModel {
    /**
     * Taking the Primary Navigation data in the below list.
     */

    @ChildResource
    private List <BannerCarouselBeanModel>items;

    @ValueMapValue
    private String exploreContentLabel;

    @ValueMapValue
    private String exploreContentUrl;

    @ValueMapValue
    private String exploreContentIconTitle;

    @ValueMapValue
    private String exploreContentIconAltText;

    @ValueMapValue
    private String swipeContentLable;

    @ValueMapValue
    private String timeOutSeconds;

    @ValueMapValue
    private boolean showHeader;

    @ValueMapValue
    private boolean isFeatureTemplateVariation;

    @ValueMapValue
    private String exploreContentLabelColor;

    @ValueMapValue
    private String exploreContentIcon;

    @SlingObject
    private ResourceResolver resolver;

    public List<BannerCarouselBeanModel> getItems() {
        return items != null ? new ArrayList<>(items) : Collections.emptyList();
    }

    public String getExploreContentLabel() {
        return exploreContentLabel;
    }

    public String getExploreContentLabelColor() {
        return exploreContentLabelColor;
    }


    public String getExploreContentIcon() {
        return exploreContentIcon;
    }

    public String getExploreContentUrl() {
        return CommonUtils.getLinkWithHTML(exploreContentUrl,resolver);
    }

    public String getExploreContentIconTitle() {
        return exploreContentIconTitle;
    }

    public String getExploreContentIconAltText() {
        return exploreContentIconAltText;
    }

    public String getSwipeContentLable() {
        return swipeContentLable;
    }

    public String getTimeOutSeconds() {
        return timeOutSeconds;
    }

    public boolean isShowHeader() {
        return showHeader;
    }

    public boolean isFeatureTemplateVariation() {
        return isFeatureTemplateVariation;
    }

    /**
     * Gets BannerCarousalItems.
     *This is used in vida V2.0
     * @return the BannerCarousalItems
     */

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("bannerContents", getItems());
        Map<String, Object> exploreContentMap = new HashMap<>();
        exploreContentMap.put("label", getExploreContentLabel());
        exploreContentMap.put("labelColor", getExploreContentLabelColor());
        exploreContentMap.put("icon", getExploreContentIcon());
        exploreContentMap.put("url", getExploreContentUrl());
        exploreContentMap.put("iconTitle", getExploreContentIconTitle());
        exploreContentMap.put("iconAltText", getExploreContentIconAltText());
        exploreContentMap.put("isShowHeader", isShowHeader());
        jsonMap.put("exploreContent", exploreContentMap);
        Map<String, Object> swipeContentMap = new HashMap<>();
        swipeContentMap.put("label", getSwipeContentLable());
        jsonMap.put("swipeContent", swipeContentMap);
        jsonMap.put("timeOutSeconds", getTimeOutSeconds());
        jsonMap.put("isVariantTwo",isFeatureTemplateVariation());
        return CommonUtils.toJson(jsonMap);
    }
}
