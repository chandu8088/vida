package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * This is the main footer model
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AboutVidaBeanModel {
    /**
     * Taking the footer data in the below list.
     */

    @ChildResource
    private List<AboutVidaInnerBeanModel> tabCardContent;

    @ValueMapValue
    private String tabContentTitle;

    @ValueMapValue
    private String tabContentDescription;

    @ValueMapValue
    private String tabTitle;

    private static final Logger LOG = LoggerFactory.getLogger(AboutVidaBeanModel.class);

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<AboutVidaInnerBeanModel> getTabCardContent() {
        return tabCardContent != null ? new ArrayList<>(tabCardContent) : Collections.emptyList();
    }

    public String getTabContentTitle() {
        return tabContentTitle;
    }

    public String getTabContentDescription() {
        return tabContentDescription;
    }

    public String getTabTitle() {
        return tabTitle;
    }
}
