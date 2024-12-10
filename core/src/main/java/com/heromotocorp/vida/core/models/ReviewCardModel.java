package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.heromotocorp.vida.core.utils.CommonUtils;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

@Model(adaptables = {Resource.class,SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ReviewCardModel {

    @ChildResource
	private List<ReviewCardBeanModel> reviewCards;

    public List<ReviewCardBeanModel> getReviewCards() {
        return Collections.unmodifiableList(reviewCards);
    }

    public String getJsonString() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(getReviewCards());
    }
}
