package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the main footer model
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PrivacyPolicyContactInfoInnerBeanModel {
    /**
     * Taking the footer data in the below list.
     */

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String text;

    @SlingObject
    private ResourceResolver resolver;

    private static final Logger LOG = LoggerFactory.getLogger(PrivacyPolicyInnerBeanModel.class);

    public String getIcon() {
        return CommonUtils.getDMImagePathLink(icon,resolver);
    }

    public String getText() {
        return text;
    }
}
