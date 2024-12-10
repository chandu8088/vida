package com.heromotocorp.vida.core.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;


/**
 * The type Exclusive Deals bean model.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, 
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ExclusiveDealsBeanModel {

    @ValueMapValue
    private String cardDesktopBgImg;

    @ValueMapValue
    private String cardMobileBgImg;

    @ValueMapValue
    private String cardBgImgAltText;

    @ValueMapValue
    private String cardBgImgTitle;

    @ValueMapValue
    private String cardDesktopImg;

    @ValueMapValue
    private String cardMobileImg;

    @ValueMapValue
    private String cardImgAltText;

    @ValueMapValue
    private String cardImgTitle;

    @ValueMapValue
    private String cardTitleText;

    @ValueMapValue
    private String redirectionIcon;

    @ValueMapValue
    private String backgroundImageTitle;

    @ValueMapValue
    private String imageTitle;

    @ValueMapValue
    private String redirectionIconImgTitle;

    @ValueMapValue
    private String backgroundImageAltText;

    @ValueMapValue
    private String imageAltText;

    @ValueMapValue
    private String redirectionIconAltText;

    @ValueMapValue
    private String navLink;

    @ValueMapValue
    private boolean newTab;

    @SlingObject
    private ResourceResolver resolver;

    public String getCardDesktopBgImg() {
        return CommonUtils.getDMImagePathLink(cardDesktopBgImg, resolver);
    }

    public String getCardMobileBgImg() {
        return CommonUtils.getDMImagePathLink(cardMobileBgImg, resolver);
    }

    public String getCardBgImgAltText() {
        return cardBgImgAltText;
    }

    public String getCardBgImgTitle() {
        return cardBgImgTitle;
    }

    public String getCardDesktopImg() {
        return CommonUtils.getDMImagePathLink(cardDesktopImg, resolver);
    }

    public String getCardMobileImg() {
        return CommonUtils.getDMImagePathLink(cardMobileImg, resolver);
    }

    public String getCardImgAltText() {
        return cardImgAltText;
    }

    public String getCardImgTitle() {
        return cardImgTitle;
    }

    public String getCardTitleText() {
        return cardTitleText;
    }

    public String getBackgroundImageTitle() {
        return backgroundImageTitle;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getBackgroundImageAltText() {
        return backgroundImageAltText;
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public String getRedirectionIcon() {
        return CommonUtils.getDMImagePathLink(redirectionIcon, resolver);
    }

    public String getRedirectionIconAltText() {
        return redirectionIconAltText;
    }

    public String getRedirectionIconImgTitle() {
        return redirectionIconImgTitle;
    }

    public String getNavLink() {
        return CommonUtils.getLinkWithHTML(navLink,resolver);
    }

    public boolean getNewTab() {
        return newTab;
    }

    
}