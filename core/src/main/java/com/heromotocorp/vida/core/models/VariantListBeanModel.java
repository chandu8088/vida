package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VariantListBeanModel {

    @Inject
    private String variantName;

    @Inject
    private String variantSKU;

    public String getVariantName() {
        return variantName;
    }

    public String getVariantSKU() {
        return variantSKU;
    }
}
