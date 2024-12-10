package com.heromotocorp.vida.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;

/**
 * Class storing Charging Banner Data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ChargingBannerBeanModel {

    @ValueMapValue
    private String order;

    @ValueMapValue
    private String label;

    @ValueMapValue
    private String labelTag;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String desktopImage;

    @ValueMapValue
    private String mobileImage;

    @ValueMapValue
    private String imageAlt;

    @ValueMapValue
    private String imageTitle;

    @ValueMapValue
    private String labelPositionDesktop;

    @ValueMapValue
    private String labelPositionMobile;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String sectionId;

    @SlingObject
    private ResourceResolver resolver;

    @OSGiService
    private GlobalConfig globalConfig;

    boolean enableDMFeature;

    @PostConstruct
    public void init() {
        enableDMFeature = globalConfig.enableDMFeature();
    }

    public String getOrder() {
        return order;
    }

    public String getDesktopImage() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(desktopImage, resolver) : desktopImage;
    }

    public String getMobileImage() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(mobileImage, resolver) : mobileImage;
    }

    public String getLabel() {
        return label;
    }

    public String getLabelTag() {
        return labelTag;
    }

    public String getLink() {
        return CommonUtils.getLinkWithHTML(link, resolver);
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getLabelPositionDesktop() {
        return labelPositionDesktop;
    }

    public String getLabelPositionMobile() {
        return labelPositionMobile;
    }

    public String getDescription() {
        return description != null ? description.replace("<p>", "").replace("</p>", "") : description;
    }

    public String getSectionId() {
        return sectionId;
    }

}
