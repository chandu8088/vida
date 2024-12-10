package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.IOException;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class WhyYouNeedVidaModel {

    @ChildResource
    private List<WhyYouNeedVidaBeanModel> tabContent;

    @ValueMapValue
    private String title;

    private static final Logger LOG = LoggerFactory.getLogger(WhyYouNeedVidaModel.class);

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<WhyYouNeedVidaBeanModel> getTabContent() {
        return tabContent != null ? new ArrayList<>(tabContent) : Collections.emptyList();
    }

    public String getTitle() {
        return title;
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("title", getTitle());
        jsonMap.put("tabContent", getTabContent());
        return CommonUtils.toJson(jsonMap);
    }

}
