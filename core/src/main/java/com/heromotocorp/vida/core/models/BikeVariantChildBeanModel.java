package com.heromotocorp.vida.core.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


/**
 * The type Bike Variant child Bean model.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BikeVariantChildBeanModel {

    @ValueMapValue
    private String color;

    @ValueMapValue
    private String textColor;

    @ValueMapValue
    private String bikeImg;

    @ValueMapValue
    private String bgImg;

    @SlingObject
    private ResourceResolver resolver;

    public String getColor() {
        return color;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getBikeImg() {
        return CommonUtils.getDMImagePathLink(bikeImg,resolver);
    }

    public String getBgImg() {
        return CommonUtils.getDMImagePathLink(bgImg,resolver);
    }

}