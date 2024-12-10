package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.api.resource.ResourceResolver;

/**
 * This is the main BottomTrayBean model
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BottomTrayBeanModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String cardBgImg;

    @ValueMapValue
    private String navLink;

    @ValueMapValue
    private Boolean isTestRideUrl;

    @ValueMapValue
    private Boolean newTab;

    @SlingObject
    private ResourceResolver resolver;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public String getCardBgImg() {
        return CommonUtils.getDMImagePathLink(cardBgImg,resolver);
    }

    public String getNavLink() {
        return CommonUtils.getLinkWithHTML(navLink,resolver);
    }

    public Boolean getNewTab() {
        return newTab;
    }

    public Boolean getIsTestRideUrl() {
        return isTestRideUrl;
    }
}
