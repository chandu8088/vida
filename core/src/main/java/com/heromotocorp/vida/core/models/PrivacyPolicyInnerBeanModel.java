package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the main footer model
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PrivacyPolicyInnerBeanModel {
    /**
     * Taking the footer data in the below list.
     */

    @ValueMapValue
    private String contentTitle;

    @ValueMapValue
    private String contentDescription;

    private static final Logger LOG = LoggerFactory.getLogger(PrivacyPolicyInnerBeanModel.class);

    public String getContentTitle() {
        return contentTitle;
    }

    public String getContentDescription() {
        return contentDescription;
    }
}
