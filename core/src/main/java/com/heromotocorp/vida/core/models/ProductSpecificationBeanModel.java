package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductSpecificationBeanModel {

    @ValueMapValue
    private String specificationIcon;

    @ValueMapValue
    private String specificationIconMobile;

    @ValueMapValue
    private String specificationIconAlt;

    @ValueMapValue
    private String specificationLabel;

    @ValueMapValue
    private String specificationValue;

    @ValueMapValue
    private String specificationUnit;

    @ValueMapValue
    private String specificationOrder;

    public String getSpecificationIcon() {
        return specificationIcon;
    }

    public String getSpecificationIconMobile() {
        return specificationIconMobile;
    }

    public String getSpecificationIconAlt() {
        return specificationIconAlt;
    }

    public String getSpecificationLabel() {
        return specificationLabel;
    }

    public String getSpecificationValue() {
        return specificationValue;
    }

    public String getSpecificationUnit() {
        return specificationUnit;
    }

    public String getSpecificationOrder() {
        return specificationOrder;
    }
}
