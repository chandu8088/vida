package com.heromotocorp.vida.core.models;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.Resource;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TestRidePopupBeanModel {

    @ValueMapValue
    private String value;

    @ValueMapValue
    private String id;

    public String getValue() {
        return value;
    }

    public String getId() {
        return id;
    }
}
