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

import java.io.IOException;
import java.util.*;


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AboutVidaModel {

    @ChildResource
    private List<AboutVidaBeanModel> aboutVidaTabContent;

    @ValueMapValue
    private String aboutVidaTitle;

    @ValueMapValue
    private String aboutVidaText;

    @ValueMapValue
    private String dataPosition;


    private static final Logger LOG = LoggerFactory.getLogger(AboutVidaModel.class);

    public List<AboutVidaBeanModel> getAboutVidaTabContent() {
        return aboutVidaTabContent != null ? new ArrayList<>(aboutVidaTabContent) : Collections.emptyList();
    }

    public String getAboutVidaTitle() {
        return aboutVidaTitle;
    }

    public String getAboutVidaText() {
        return aboutVidaText;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("aboutVidaTabContent", getAboutVidaTabContent());
        jsonMap.put("aboutVidaTitle", getAboutVidaTitle());
        jsonMap.put("aboutVidaText", getAboutVidaText());

        return CommonUtils.toJson(jsonMap);
    }

}
