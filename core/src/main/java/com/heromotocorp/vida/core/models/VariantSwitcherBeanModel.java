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
 * Class storing Variant Switcher Data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VariantSwitcherBeanModel {

    @ValueMapValue
    private String productSku;

    @ValueMapValue
    private String baseImg;

    @ValueMapValue
    private String batteryImg;

    @ValueMapValue
    private String label;

    @ValueMapValue
    private String labelLink;

    @ValueMapValue
    private boolean labelNewtab;

    @ValueMapValue
    private String defaultCity;

    @ValueMapValue
    private String imageAltBase;

    @ValueMapValue
    private String imageTitleBase;

    @ValueMapValue
    private String imageAltBattery;

    @ValueMapValue
    private String imageTitleBattery;

    @SlingObject
    private ResourceResolver resolver;

    public String getLabel() {
        return label;
    }

    public String getBaseImg() {
        return CommonUtils.getDMImagePathLink(baseImg, resolver);
    }

    public String getLabelLink() {
        return CommonUtils.getLinkWithHTML(labelLink, resolver);
    }

    public String getBatteryImg() {
        return CommonUtils.getDMImagePathLink(batteryImg, resolver);
    }

    public String getProductSku() {
        return productSku;
    }

    public boolean getIsLabelNewtab() {
        return labelNewtab;
    }

    public String getDefaultCity() {
        return defaultCity;
    }

    public String getImageAltBase() {
        return imageAltBase;
    }

    public String getImageTitleBase() {
        return imageTitleBase;
    }

    public String getImageAltBattery() {
        return imageAltBattery;
    }

    public String getImageTitleBattery() {
        return imageTitleBattery;
    }
    
}
