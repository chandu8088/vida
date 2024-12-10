package com.heromotocorp.vida.core.models;

import com.day.cq.wcm.api.Page;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CustomMetaDataModel {

    @SlingObject
    SlingHttpServletRequest request;

    @Inject
    private Page currentPage;

    private String modifiedPageTitle;

    private String modifiedDescription;

    private String modifiedCanonicalUrl;


    @PostConstruct
    protected void init() {
        String cityName = request.getRequestPathInfo().getSelectorString();
        if (cityName != null && !cityName.isEmpty()) {
            cityName = cityName.contains("-") ? cityName.replaceAll("-", " ") : cityName;
            String pageTitle = currentPage.getPageTitle();
            String pageDescription = currentPage.getDescription();
            modifiedPageTitle = pageTitle!=null && pageTitle.contains("$cityName") ? pageTitle.replace("$cityName", cityName) : pageTitle;
            modifiedDescription = pageDescription!=null && pageDescription.contains("$cityName") ? pageDescription.replace("$cityName", cityName) : pageDescription;
            String modifiedCityName = cityName!=null && cityName.contains(" ") ? cityName.replaceAll(" ", "-") : cityName;
            modifiedCanonicalUrl = currentPage.getPath() + "/" + modifiedCityName;
        }
}

public String getModifiedPageTitle() {
    return modifiedPageTitle != null ? modifiedPageTitle : currentPage.getPageTitle();
}

public String getModifiedDescription() {
    return modifiedDescription != null ? modifiedDescription : currentPage.getDescription();
}

public String getModifiedCanonicalUrl(){
        return modifiedCanonicalUrl != null ? modifiedCanonicalUrl : currentPage.getPath();
}
}
