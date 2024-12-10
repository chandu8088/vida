package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.heromotocorp.vida.core.utils.CommonUtils;

@Model(adaptables = {
        Resource.class }, resourceType = "vida/components/vida-2.0/content/charginglocatorfeaturedetail", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ChargingLocatorFeatureDetailModel {

    @ValueMapValue
    private String preTitle;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String titleTag;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String dataPosition;

    @ChildResource
    private List<ChargingBannerBeanModel> cardsInfo;

    @SlingObject
    private ResourceResolver resolver;

    public String getDataPosition() {
        return dataPosition;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public String getDescription() {
        return description != null ? description.replace("<p>", "").replace("</p>", "") : description;
    }

    public String getPreTitle() {
        return preTitle;
    }

    public String getTitle() {
        return title;
    }

    public List<ChargingBannerBeanModel> getCardsInfo() {
        return cardsInfo != null ? new ArrayList<>(cardsInfo) : Collections.emptyList();
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("preTitle", getPreTitle());
        jsonMap.put("title", getTitle());
        jsonMap.put("titleTag", getTitleTag());
        jsonMap.put("description", getDescription());
        jsonMap.put("cardsInfo", getCardsInfo());
        return CommonUtils.toJson(jsonMap);
    }

}
