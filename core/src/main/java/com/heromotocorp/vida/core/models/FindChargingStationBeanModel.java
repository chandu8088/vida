package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

/**
 * This is the main FindChargingStationBean model
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FindChargingStationBeanModel {

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String number;

    @SlingObject
    private ResourceResolver resolver;

    private static final Logger LOG = LoggerFactory.getLogger(FindChargingStationBeanModel.class);

    public String getIcon() {
        return CommonUtils.getDMImagePathLink(icon,resolver);
    }

    public String getTitle() {
        return title;
    }

    public String getNumber() {
        return number;
    }
}
