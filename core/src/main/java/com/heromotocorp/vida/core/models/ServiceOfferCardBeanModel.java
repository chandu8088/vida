package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.ResourceResolver;

/**
 * This is the main ServiceOfferCard model
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceOfferCardBeanModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String itemType;

    @ValueMapValue
    private String label;

    @ValueMapValue
    private String desktopImage;

    @ValueMapValue
    private String mobileImage;

    @ValueMapValue
    private String imagepath;

    @ValueMapValue
    private String imagealttext;

    @ValueMapValue
    private String knowMoreLink;

    @ValueMapValue
    private boolean newTab;

    @ValueMapValue
    private boolean youTubeVideo;


    @ValueMapValue
    private String videopath;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String knowMoreLabel;

    @ValueMapValue
    private String imageTitle;

    @SlingObject
    private ResourceResolver resolver;

    public String getTitle() {
        return title;
    }

    public String getItemType() {
        return itemType;
    }

    public String getLabel() {
        return label;
    }

    public String getImagepath() {
        return CommonUtils.getDMImagePathLink(imagepath,resolver);
    }

    public String getImagealttext() {
        return imagealttext;
    }

    public String getKnowMoreLink() {
        return CommonUtils.getLinkWithHTML(knowMoreLink,resolver);
    }

    public boolean getNewTab() {
        return newTab;
    }

    public String getVideopath() {
        return CommonUtils.getDMVideoPathLink(videopath,resolver);
    }

    public String getDescription() {
        return description;
    }

    public String getDesktopImage() {
        return CommonUtils.getDMImagePathLink(desktopImage,resolver);
    }

    public String getMobileImage() {
        return CommonUtils.getDMImagePathLink(mobileImage,resolver);
    }

    public String getKnowMoreLabel() {
        return knowMoreLabel;
    }

    public boolean isYouTubeVideo() {
        return youTubeVideo;
    }

    public String getImageTitle() {
        return imageTitle;
    }
}
