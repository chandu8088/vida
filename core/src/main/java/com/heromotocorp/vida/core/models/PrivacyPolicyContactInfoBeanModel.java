package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * This is the main footer model
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PrivacyPolicyContactInfoBeanModel {
    /**
     * Taking the footer data in the below list.
     */

    @ChildResource
    private List<PrivacyPolicyContactInfoInnerBeanModel> contactInfo;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    private static final Logger LOG = LoggerFactory.getLogger(PrivacyPolicyContactInfoBeanModel.class);

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<PrivacyPolicyContactInfoInnerBeanModel> getContactInfo() {
        return contactInfo != null ? new ArrayList<>(contactInfo) : Collections.emptyList();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
