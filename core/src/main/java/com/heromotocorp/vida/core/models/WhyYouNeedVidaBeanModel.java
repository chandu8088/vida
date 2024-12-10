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
public class WhyYouNeedVidaBeanModel {
    /**
     * Taking the footer data in the below list.
     */

    @ValueMapValue
    private String tabTitle;

    @ValueMapValue
    private String tabDescription;

    @ValueMapValue
    private String tabCtaLabel;

    @ValueMapValue
    private String tabCtaNavLink;

    @ValueMapValue
    private boolean openInaNewTab;

    @ValueMapValue
    private String tabIcon;

    @ValueMapValue
    private String altText;

    @SlingObject
    private ResourceResolver resolver;

    private static final Logger LOG = LoggerFactory.getLogger(WhyYouNeedVidaBeanModel.class);

    public String getTabTitle() {
        return tabTitle;
    }

    public String getTabDescription() {
        return tabDescription;
    }

    public String getTabCtaLabel() {
        return tabCtaLabel;
    }

    public String getTabCtaNavLink() {
        return CommonUtils.getLinkWithHTML(tabCtaNavLink,resolver);
    }

    public boolean isOpenInaNewTab() {
        return openInaNewTab;
    }

    public String getTabIcon() {
        return CommonUtils.getDMImagePathLink(tabIcon,resolver);
    }

    public String getAltText() {
        return altText;
    }
}
