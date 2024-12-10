package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FaqBeanModel {
    @ValueMapValue
    private String queryIconMob;
    @ValueMapValue
    private String queryIconDesk;
    @ValueMapValue
    private String queryIconAltText;
    @ValueMapValue
    private String queryIconImgTitle;
    @ValueMapValue
    private String queryDescription;
    @ChildResource
    private List<FaqChildBeanModel> faqContent;
    @SlingObject
    private ResourceResolver resolver;

    public String getQueryIconMob() {
        return CommonUtils.getDMImagePathLink(queryIconMob,resolver);
    }

    public String getQueryIconDesk() {
        return CommonUtils.getDMImagePathLink(queryIconDesk,resolver);
    }

    public String getQueryIconAltText() {
        return queryIconAltText;
    }

    public String getQueryIconImgTitle() {
        return queryIconImgTitle;
    }

    public String getQueryDescription() {
        return queryDescription;
    }

    public List<FaqChildBeanModel> getFaqChildItems() {
        return faqContent != null ? new ArrayList<>(faqContent) : Collections.emptyList();
    }

}
