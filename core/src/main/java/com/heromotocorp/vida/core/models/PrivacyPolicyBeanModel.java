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
public class PrivacyPolicyBeanModel {
    /**
     * Taking the footer data in the below list.
     */

    @ChildResource
    private List<PrivacyPolicyInnerBeanModel> privacyPolicies;

    @ValueMapValue
    private String privacyPolicyTitle;

    @ValueMapValue
    private String privacyPolicyLastUpdatedDate;

    @ValueMapValue
    private String titleTag;

    private static final Logger LOG = LoggerFactory.getLogger(AboutVidaBeanModel.class);

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<PrivacyPolicyInnerBeanModel> getPrivacyPolicies() {
        return privacyPolicies != null ? new ArrayList<>(privacyPolicies) : Collections.emptyList();
    }

    public String getPrivacyPolicyTitle() {
        return privacyPolicyTitle;
    }

    public String getPrivacyPolicyLastUpdatedDate() {
        return privacyPolicyLastUpdatedDate;
    }

    public String getTitleTag() {
        return titleTag;
    }

}
