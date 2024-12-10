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
public class ChargingLocatorChoosePlanBeanModel {

    @ValueMapValue
    private String planType;

    @ValueMapValue
    private String price;

    @ValueMapValue
    private String freeLabel;

    @ValueMapValue
    private String tabDescription;

    @ValueMapValue
    private boolean strikePrice;


    @SlingObject
    private ResourceResolver resolver;

    public String getPlanType() {
        return planType;
    }

    public String getPrice() {
        return price;
    }

    public String getFreeLabel() {
        return freeLabel;
    }

    public String getTabDescription() {
        return tabDescription;
    }

    public boolean isStrikePrice() {
        return strikePrice;
    }
}