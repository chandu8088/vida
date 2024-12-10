package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;


/**
 * The type Banner carousel bean model.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BannerCarouselBeanModel {

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String headingTextColor;

    @ValueMapValue
    private  String titleTag;

    @ValueMapValue
    private String variationClass;

    @ValueMapValue
    private String subheading;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String itemType;

    @ValueMapValue
    private String imagepath;

    @ValueMapValue
    private String imagealttext;

    @ValueMapValue
    private String videopath;

    @ValueMapValue
    private String imageDesktop;

    @ValueMapValue
    private String imageMobile;

    @ValueMapValue
    private String videoMobile;

    @ValueMapValue
    private String videoDesktop;

    @ValueMapValue
    private String subheadinglink;

    @ValueMapValue
    private boolean isRedirectionButton;

    @ValueMapValue
    private Boolean isTestRideUrl;

    @ValueMapValue
    private String mobiletitlecolor;

    @ValueMapValue
    private boolean newTab;

    @SlingObject
    private ResourceResolver resolver;


    @ValueMapValue
    private String assetType;

    @ValueMapValue
    private String imageTitle;

    @ValueMapValue
    private String videoTitle;

    @ValueMapValue
    private String videoWithControlTitle;

    @ValueMapValue
    private String videoControlMobile;

    @ValueMapValue
    private String videoControlDesktop;

    @ValueMapValue
    private String playIconMob;

    @ValueMapValue
    private String playIconDesk;

    @ValueMapValue
    private String pauseIconMob;

    @ValueMapValue
    private String pauseIconDesk;

    @ValueMapValue
    private String altIconText;

    /**
     * Gets heading.
     *
     * @return the heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * Gets heading text color.
     *
     * @return the headingTextColor
     */
    public String getHeadingTextColor() {
        return headingTextColor;
    }

    /**
     * Gets titletag.
     *
     * @return the titleTag
     */
    public  String getTitleTag(){
        return titleTag;
    }

    /**
     * Gets titletag.
     *
     * @return the titleTag
     */
    public  String getVariationClass(){
        return variationClass;
    }

    /**
     * Gets subheading.
     *
     * @return the subheading
     */
    public String getSubheading() {
        return subheading;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets item type.
     *
     * @return the item type
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * Gets imagepath.
     *
     * @return the imagepath
     */
    public String getImagepath() {
        return imagepath;
    }

    /**
     * Gets imagealttext.
     *
     * @return the imagealttext
     */
    public String getImagealttext() {
        return imagealttext;
    }

    /**
     * Gets videopath.
     *
     * @return the videopath
     */
    public String getVideopath() {
        return videopath;
    }

    /**
     * Gets mobileimagepath.
     *This fields used in v2.0
     * @return the mobileimagepath
     */
    public String getImageMobile() {
        return CommonUtils.getDMImagePathLink(imageMobile,resolver);
    }

    /**
     * Gets subheadinglink.
     *This fields used in v2.0
     * @return the subheadinglink
     */
    public String getSubheadinglink() {
        return CommonUtils.getLinkWithHTML(subheadinglink,resolver);
    }

    public boolean getNewTab() {
        return newTab;
    }

    public boolean getIsRedirectionButton() {
        return isRedirectionButton;
    }

    public String getMobiletitlecolor() {
        return mobiletitlecolor;
    }

    public String getImageDesktop() {
        return CommonUtils.getDMImagePathLink(imageDesktop,resolver);
    }

    public String getVideoMobile() {
        return CommonUtils.getDMVideoPathLink(videoMobile,resolver);
    }

    public String getVideoDesktop() {
        return CommonUtils.getDMVideoPathLink(videoDesktop,resolver);
    }

    public String getAssetType() {
        return assetType;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public Boolean getIsTestRideUrl() {
        return isTestRideUrl;
    }
    
    public String getVideoTitle() {
        return videoTitle;
    }

    public String getVideoWithControlTitle() {
        return videoWithControlTitle;
    }

    public String getVideoControlMobile() {
        return CommonUtils.getDMVideoPathLink(videoControlMobile, resolver);
    }

    public String getVideoControlDesktop() {
        return CommonUtils.getDMVideoPathLink(videoControlDesktop, resolver);
    }

    public String getPlayIconMob() {
        return CommonUtils.getDMImagePathLink(playIconMob, resolver);
    }

    public String getPlayIconDesk() {
        return CommonUtils.getDMImagePathLink(playIconDesk, resolver);
    }

    public String getPauseIconMob() {
        return CommonUtils.getDMImagePathLink(pauseIconMob, resolver);
    }

    public String getPauseIconDesk() {
        return CommonUtils.getDMImagePathLink(pauseIconDesk, resolver);
    }

    public String getAltIconText() {
        return altIconText;
    }

}
