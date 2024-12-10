package com.heromotocorp.vida.core.models;

import com.adobe.xfa.ut.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LoveRedirectionCardBeanModel {

    @SlingObject
    private ResourceResolver resolver;

    @ValueMapValue
    private String redirectImagePath;

    @ValueMapValue
    private String redirectionPath;

    @ValueMapValue
    private String newTab;

    @ValueMapValue
    private String cardText;

    @ChildResource
    private List<Resource> cardImg;

    @ValueMapValue
    private String cardBgImgDesktop;

    @ValueMapValue
    private String cardBgImgMobile;

    @ValueMapValue
    private String imageAltText;

    @ValueMapValue
    private String cardImgText;

    @ValueMapValue
    private String cardTextColor;

    @ValueMapValue
    private String cardType;

    @ValueMapValue
    private String cardBgColor;

    public List<String> getCardImg() {
        List<String> imageList = new ArrayList<>();

        if (cardImg != null) {
            for (Resource item : cardImg) {
                if (item != null) {
                    String linkText = item.getValueMap().get("cardImgItem", String.class);
                    imageList.add(CommonUtils.getDMImagePathLink(linkText, resolver));
                }
            }
        }
        return !imageList.isEmpty() ? new ArrayList<>(imageList) : Collections.emptyList();
    }

    public String getRedirectImagePath() {
        return CommonUtils.getDMImagePathLink(redirectImagePath,resolver);
    }

    public String getRedirectionPath() {
        if(!StringUtils.isEmpty(redirectionPath)){
            return resolver.map(redirectionPath);
        }
        return null;

    }

    public String getNewTab() {
        return newTab;
    }

    public String getCardText() {
        return cardText;
    }

    public String getCardBgImgDesktop() {
        return CommonUtils.getDMImagePathLink(cardBgImgDesktop,resolver);
    }

    public String getCardBgImgMobile() {
        return CommonUtils.getDMImagePathLink(cardBgImgMobile,resolver);
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public String getCardImgText() {
        return cardImgText;
    }

    public String getCardTextColor() {
        return cardTextColor;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardBgColor() {
        return cardBgColor;
    }
}
