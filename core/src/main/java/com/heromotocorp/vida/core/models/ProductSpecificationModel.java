package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.heromotocorp.vida.core.utils.CommonUtils;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductSpecificationModel {

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String title;

    @ChildResource
    private List<ProductSpecificationBeanModel> productSpecificationList;

    public List<ProductSpecificationBeanModel> getProductSpecificationList() {
        return productSpecificationList != null ? new ArrayList<>(productSpecificationList) : Collections.emptyList();
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("variant_specification_details", getProductSpecificationList());
        return CommonUtils.toJson(jsonMap);
    }

    public String getHeading() {
        return heading;
    }

    public String getTitle() {
        return title;
    }
}
