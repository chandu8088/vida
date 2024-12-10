package com.heromotocorp.vida.core.models;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


/**
 * The type Bike Variant Bean model.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BikeVariantBeanModel {

    @ValueMapValue
    private String variantName;

    @ChildResource
	private List<BikeVariantChildBeanModel> variantDetails;

    public String getVariantName() {
        return variantName;
    }

    public List<BikeVariantChildBeanModel> getVariantDetails() {
        return variantDetails!= null ? new ArrayList<>(variantDetails) : Collections.emptyList();
    }


}