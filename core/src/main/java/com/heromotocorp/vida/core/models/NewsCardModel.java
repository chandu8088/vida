package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsCardModel {

    @ChildResource
    private List<NewsCardBeanModel> newsCard;

    public List<NewsCardBeanModel> getNewsCard() {
        return Collections.unmodifiableList(newsCard);
    }

    public String getJsonString() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(getNewsCard());
    }
}
