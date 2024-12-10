package com.heromotocorp.vida.core.models;


import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import java.io.IOException;
import java.util.*;

import javax.inject.Inject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

/**
 * This is the ExclusiveDeals model
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ExclusiveDealsModel{
    /**
     * Taking the ExclusiveDeals Multifield data in the below list.
     */

    @ChildResource
    private List <ExclusiveDealsBeanModel>dealsCardItems;

    @ValueMapValue
    private String pageType;

    @ValueMapValue
    private String primaryText;

    @ValueMapValue
    private String boldText;

    @ValueMapValue
    private String seeMoreText;

    @ValueMapValue
    private String seeLessText;

    @ValueMapValue
    private String redirectionCardLabel;

    @ValueMapValue
    private String redirectionCardHeading;

    @Inject 
    @Self 
    private LoveRedirectionCardModel loveRedirectionCardModel;

    @SlingObject
    private ResourceResolver resolver;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<ExclusiveDealsBeanModel> getDealsCardItems() {
        return dealsCardItems != null ? new ArrayList<>(dealsCardItems) : Collections.emptyList();
    }

    public String getPageType() {
        return pageType;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public String getBoldText() {
        return boldText;
    }

    public String getSeeMoreText() {
        return seeMoreText;
    }

    public String getSeeLessText() {
        return seeLessText;
    }

    public String getRedirectionCardLabel() {
        return redirectionCardLabel;
    }

    public String getRedirectionCardHeading() {
        return redirectionCardHeading;
    }

    /**
     * Gets ExclusiveDealsItems.
     *This is used in vida V2.0
     * @return the ExclusiveDealsItems
     */

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("primaryText", getPrimaryText());
        jsonMap.put("boldText", getBoldText());
        jsonMap.put("seeMoreText", getSeeMoreText());
        jsonMap.put("seeLessText", getSeeLessText());
        jsonMap.put("dealsCardsContent", getDealsCardItems());
        if(getPageType().equalsIgnoreCase("offer-details-page")){
            jsonMap.put("redirectionCardLabel", getRedirectionCardLabel());
            jsonMap.put("redirectionCardHeader", getRedirectionCardHeading());
            jsonMap.put("redirectionCardConfig", loveRedirectionCardModel.getJsonString());
        }
        return CommonUtils.toJson(jsonMap);
    }
}