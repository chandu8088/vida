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
public class AboutVidaInnerBeanModel {
    /**
     * Taking the footer data in the below list.
     */

    @ValueMapValue
    private String cardTitle;

    @ValueMapValue
    private String cardDescription;

    @ValueMapValue
    private String cardNavLink;

    @ValueMapValue
    private boolean newTab;

    @ValueMapValue
    private String cardDesktopImg;

    @ValueMapValue
    private String imagealttext;

    @ValueMapValue
    private String cardMobileImg;

    @ValueMapValue
    private String cardVideo;

    @ValueMapValue
    private String itemType;

    @ValueMapValue
    private String imageTitle;

    @SlingObject
    private ResourceResolver resolver;

    private static final Logger LOG = LoggerFactory.getLogger(AboutVidaInnerBeanModel.class);

    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardDescription() {
        return cardDescription;
    }

    public String getCardNavLink() {
        return CommonUtils.getLinkWithHTML(cardNavLink,resolver);
    }

    public boolean isNewTab() {
        return newTab;
    }

    public String getCardDesktopImg() {
        return CommonUtils.getDMImagePathLink(cardDesktopImg,resolver);
    }

    public String getImagealttext() {
        return imagealttext;
    }

    public String getCardMobileImg() {
        return CommonUtils.getDMImagePathLink(cardMobileImg,resolver);
    }

    public String getCardVideo() {
        return CommonUtils.getDMImagePathLink(cardVideo,resolver);
    }
    public boolean getIsVideo() {
        return itemType.equals("video")?true:false;
    }
    
    public String getImageTitle() {
        return imageTitle;
    }
}
