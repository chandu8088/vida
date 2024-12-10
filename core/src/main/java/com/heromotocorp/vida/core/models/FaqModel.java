package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.io.IOException;
import java.util.*;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FaqModel {
    @ChildResource
    private List<FaqBeanModel> queryContent;
    @ValueMapValue
    private String searchIconMob;
    @ValueMapValue
    private String searchIconDesk;
    @ValueMapValue
    private String searchAltText;
    @ValueMapValue
    private String searchImgTitle;
    @ValueMapValue
    private String searchLabel;
    @ValueMapValue
    private boolean showSearchBar;
    @ValueMapValue
    private String redirectLabel;
    @ValueMapValue
    private String redirectUrl;
    @ValueMapValue
    private String contentNewTab;
    @SlingObject
    private ResourceResolver resolver;

    public String getSearchIconMob() {
        return CommonUtils.getDMImagePathLink(searchIconMob,resolver);
    }

    public String getSearchIconDesk() {
        return CommonUtils.getDMImagePathLink(searchIconDesk,resolver);
    }

    public String getSearchAltText() {
        return searchAltText;
    }

    public String getSearchImgTitle() {
        return searchImgTitle;
    }

    public String getSearchLabel() {
        return searchLabel;
    }
    public boolean isShowSearchBar(){
        return showSearchBar;
    }

    public String getRedirectLabel() {
        return redirectLabel;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public String isContentNewTab() {
        return contentNewTab;
    }

    public List<FaqBeanModel> getQueryContent() {
        return queryContent != null ? new ArrayList<>(queryContent) : Collections.emptyList();
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("searchIconMob", getSearchIconMob());
        jsonMap.put("searchIconDesk", getSearchIconDesk());
        jsonMap.put("searchAltText", getSearchAltText());
        jsonMap.put("searchIconImageTitle", getSearchImgTitle());
        jsonMap.put("searchLabel", getSearchLabel());
        jsonMap.put("showSearchBar",isShowSearchBar());
        jsonMap.put("reDirectLabel", getRedirectLabel());
        jsonMap.put("reDirectUrl", getRedirectUrl());
        jsonMap.put("newTab", isContentNewTab());
        jsonMap.put("FaqQueriesContent", getQueryContent());
        return CommonUtils.toJson(jsonMap);

    }

}
