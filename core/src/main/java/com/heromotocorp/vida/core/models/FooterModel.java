package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import java.util.*;

import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * This is the main footer model
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {
    /**
     * Taking the footer data in the below list.
     */

    @SlingObject
    private ResourceResolver resolver;
    @ChildResource
    private List<FooterBeanModel> footerDescription;

    @ChildResource
    private List<FooterBeanModel> footeritems;

    @ChildResource
    private List<FooterBeanModel> socialmediaitems;

    @ChildResource
    private List<GenericDataModel> termsPolicyItem;

    @ValueMapValue
    private String footerLogo;

    @ValueMapValue
    private String faqheading;

    @ValueMapValue
    private String faqContent;

    @ValueMapValue
    private String readMoreData;
    
    @ValueMapValue
    private String footerAddress;

    @ValueMapValue
    private String footerEmail;

    @ValueMapValue
    private String footerPhone;

    @ValueMapValue
    private String termsAndSeriviceText;

    @ValueMapValue
    private String followUsText;

    @ValueMapValue
    private String PrivacyPolicyText;

    @ValueMapValue
    private String disclaimerText;

    @ValueMapValue
    private String copyright;

    @ValueMapValue
    private String dataPosition;

    @ValueMapValue
    private String footerLink;

    @ValueMapValue
    private String footerLogoAlt;

    @ValueMapValue
    private String footerLogoTitle;

    @ValueMapValue
    private String footerHeroLogo;

    @ValueMapValue
    private String footerHeroLogoAlt;

    @ValueMapValue
    private String footerHeroLogoTitle;

    @ValueMapValue
    private String footerHeroLink;

    @ValueMapValue
    private boolean footerHeroLogoNewTab;

    @ValueMapValue
    private boolean isShowFaqSection;

    private static final Logger LOG = LoggerFactory.getLogger(FooterModel.class);

    public List<FooterBeanModel> getFooterDescription() {
        return footerDescription != null ? new ArrayList<>(footerDescription) : Collections.emptyList();
    }

    public List<FooterBeanModel> getFooteritems() {
        return footeritems != null ? new ArrayList<>(footeritems) : Collections.emptyList();
    }

    public List<GenericDataModel> getTermsPolicyItem() {
        return termsPolicyItem != null ? new ArrayList<>(termsPolicyItem) : Collections.emptyList();
    }
    public List<FooterBeanModel> getSocialmediaitems() {
        return socialmediaitems != null ? new ArrayList<>(socialmediaitems) : Collections.emptyList();
    }

    public String getFaqheading() {
        return faqheading;
    }

    public String getFaqContent() {
        return faqContent;
    }

    public String getReadMoreData() {
        return readMoreData;
    }

    public String getFooterAddress() {
        return footerAddress;
    }

    public String getFooterEmail() {
        return footerEmail;
    }

    public String getFooterPhone() {
        return footerPhone;
    }


    public String getFollowUsText() {
        return followUsText;
    }

    public String getCopyright() {
        return copyright;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getFooterLink() {
        return footerLink;
    }

    public String getFooterLogoAlt() {
        return footerLogoAlt;
    }

    public String getFooterLogoTitle() {
        return footerLogoTitle;
    }

     public String getFooterHeroLogo() {
        return footerHeroLogo;
    }

    public String getFooterHeroLogoAlt() {
        return footerHeroLogoAlt;
    }

    public String getFooterHeroLogoTitle() {
        return footerHeroLogoTitle;
    }

    public String getFooterHeroLink() {
        return footerHeroLink;
    }
    
    public boolean getFooterHeroLogoNewTab() {
        return footerHeroLogoNewTab;
    }
    
    public boolean getIsShowFaqSection() {
        return isShowFaqSection;
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("footerDescription", getFooterDescription());
        Map<String, Object> accordionMap = new HashMap<>();
        accordionMap.put("accordianContent", getFooteritems());
        accordionMap.put("isShowMore", false);
        jsonMap.put("accordianData", accordionMap);
        Map<String, Object> faqsContentMap = new HashMap<>();
        faqsContentMap.put("questionAndAnswer", getFaqContent());
        faqsContentMap.put("readMoreData", getReadMoreData());
        faqsContentMap.put("isShowMore", readMoreData != null ? true : false);
        List<Map<String, Object>> faqsInnerArray = new ArrayList<>();
        faqsInnerArray.add(faqsContentMap);
        Map<String, Object> faqsInnerContentMap = new HashMap<>();
        faqsInnerContentMap.put("title", getFaqheading());
        faqsInnerContentMap.put("questionContent", faqsInnerArray);
        // Create a list to hold the map(s)
        List<Map<String, Object>> faqsArray = new ArrayList<>();
        // Add the faqsInnerContentMap to the array
        faqsArray.add(faqsInnerContentMap);
        Map<String, Object> faqsMainContentMap = new HashMap<>();
        faqsMainContentMap.put("faqsContent",faqsArray);
        jsonMap.put("faqs", faqsMainContentMap);
        jsonMap.put("footerAddress", getFooterAddress());
        jsonMap.put("footerEmail", getFooterEmail());
        jsonMap.put("isShowFaqSection", getIsShowFaqSection());
        jsonMap.put("footerPhno", getFooterPhone());
        jsonMap.put("followUsText", getFollowUsText());
        jsonMap.put("footerTermsAndSerivice", getTermsPolicyItem());
        jsonMap.put("footerLogo", getFooterLogo());
        jsonMap.put("footerLink", getFooterLink());
        jsonMap.put("footerMediaIcons", getSocialmediaitems());
        jsonMap.put("copyWriteText", getCopyright());
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("footerLogoAlt", getFooterLogoAlt());
        jsonMap.put("footerLogoTitle", getFooterLogoTitle());
        jsonMap.put("footerHeroLogo", getFooterHeroLogo());
        jsonMap.put("footerHeroLogoAlt", getFooterHeroLogoAlt());
        jsonMap.put("footerHeroLogoTitle", getFooterHeroLogoTitle());
        jsonMap.put("footerHeroLink", getFooterHeroLink());
        jsonMap.put("footerHeroLogoNewTab", getFooterHeroLogoNewTab());

        return CommonUtils.toJson(jsonMap);
    }

    public String getFooterLogo() {
        return footerLogo;
    }
}
