package com.heromotocorp.vida.core.models;

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


@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PrivacyPolicyModel {

    @ChildResource
    private PrivacyPolicyBeanModel sectionOne;

    @ChildResource
    private PrivacyPolicyBeanModel sectionTwo;

    @ChildResource
    private PrivacyPolicyContactInfoBeanModel sectionThree;

    @ValueMapValue
    private String dataPosition;

    @ValueMapValue
    private boolean termsAndConditions;


    private static final Logger LOG = LoggerFactory.getLogger(PrivacyPolicyModel.class);

    public PrivacyPolicyBeanModel getSectionOne() {
        return sectionOne;
    }

    public PrivacyPolicyBeanModel getSectionTwo() {
        return sectionTwo;
    }

    public PrivacyPolicyContactInfoBeanModel getSectionThree() {
        return sectionThree;
    }

    public String getDataPosition(){
        return dataPosition;
    }

    public boolean isTermsAndConditions() {
        return termsAndConditions;
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("sectionOne", getSectionOne());
        jsonMap.put("sectionTwo", getSectionTwo());
        jsonMap.put("sectionThree", getSectionThree());
        jsonMap.put("isTermsAndConditions", isTermsAndConditions());
        jsonMap.put("dataPosition", getDataPosition());
        return CommonUtils.toJson(jsonMap);
    }

}
