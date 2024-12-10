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
 * The type Foooter bean model.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DealershipFinderBeanModel {

    @ValueMapValue
    private String cityName;

    @ValueMapValue
    private String cityIcon;

    @ValueMapValue
    private String id;

    @ValueMapValue
    private String locationInfoTitle;

    @ValueMapValue
    private String locationInfoFirstIcon;

    @ValueMapValue
    private String locationInfoSecondIcon;

    @ValueMapValue
    private String tabTitle;

    @ValueMapValue
    private String tabContentTitle;

    @ValueMapValue
    private String tabContentDescription;

    @SlingObject
    private ResourceResolver resolver;

    public String getCityName() {
        return cityName;
    }

    public String getCityIcon() {
        return CommonUtils.getDMImagePathLink(cityIcon,resolver);
    }

    public String getId() {
        return id;
    }

    public String getLocationInfoTitle() {
        return locationInfoTitle;
    }

    public String getLocationInfoFirstIcon() {
        return CommonUtils.getDMImagePathLink(locationInfoFirstIcon,resolver);
    }

    public String getLocationInfoSecondIcon() {
        return CommonUtils.getDMImagePathLink(locationInfoSecondIcon,resolver);
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public String getTabContentTitle() {
        return tabContentTitle;
    }

    public String getTabContentDescription() {
        return tabContentDescription;
    }
}