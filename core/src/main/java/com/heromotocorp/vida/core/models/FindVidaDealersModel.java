package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FindVidaDealersModel {

    @ValueMapValue
    private String preTitle;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String titleTag;

    @ValueMapValue
    private String redirectionIcon;

    @ValueMapValue
    private String redirectionLink;

    @ValueMapValue
    private boolean redirectionNewTab;

    @ValueMapValue
    private String dealersCardImgDesktop;

    @ValueMapValue
    private String dealersCardImgMobile;

    @ValueMapValue
    private String locationErrorMsg;

    @ValueMapValue
    private String getDirectionsCta;

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String secondIcon;

    @ValueMapValue
    private String placeholder;

    @ValueMapValue
    private String noDealerErrorMsg;

    @ValueMapValue
    private String noValueErrorMsg;

    @SlingObject
    private ResourceResolver resolver;

    @ValueMapValue
    private String redirectionIconTitle;
    
    @ValueMapValue
    private String redirectionIconAltText;

    @ValueMapValue
    private String dealersCardImgTitle;

    @ValueMapValue
    private String dealersCardImgAltText;

    @ValueMapValue
    private String locationIcon;

    @ValueMapValue
    private String locationIconAltText;

    @ValueMapValue
    private String secondLocationIcon;

    @ValueMapValue
    private String secondLocationIconAltText;

    public String getPreTitle() {
        return preTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public String getRedirectionIcon() {
        return CommonUtils.getDMImagePathLink(redirectionIcon,resolver);
    }

    public String getRedirectionLink() {
        return CommonUtils.getLinkWithHTML(redirectionLink,resolver);
    }

    public boolean isRedirectionNewTab() {
        return redirectionNewTab;
    }

    public String getDealersCardImgDesktop() {
        return CommonUtils.getDMImagePathLink(dealersCardImgDesktop,resolver);
    }

    public String getDealersCardImgMobile() {
        return CommonUtils.getDMImagePathLink(dealersCardImgMobile,resolver);
    }

    public String getLocationErrorMsg() {
        return locationErrorMsg;
    }

    public String getGetDirectionsCta() {
        return getDirectionsCta;
    }

    public String getIcon() {
        return CommonUtils.getDMImagePathLink(icon,resolver);
    }

    public String getSecondIcon() {
        return CommonUtils.getDMImagePathLink(secondIcon,resolver);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getNoDealerErrorMsg() {
        return noDealerErrorMsg;
    }

    public String getNoValueErrorMsg() {
        return noValueErrorMsg;
    }
    
    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("titlePreText", getPreTitle());
        jsonMap.put("title",getTitle());
        jsonMap.put("titleTag",getTitleTag());
        jsonMap.put("redirectionIcon",getRedirectionIcon());
        jsonMap.put("redirectionIconTitle",getRedirectionIconTitle());
        jsonMap.put("redirectionIconAltText",getRedirectionIconAltText());
        jsonMap.put("redirectionNavLink",getRedirectionLink());
        jsonMap.put("redirectionNewTab",isRedirectionNewTab());
        jsonMap.put("dealersCardImgDesktop",getDealersCardImgDesktop());
        jsonMap.put("dealersCardImgMobile",getDealersCardImgMobile());
        jsonMap.put("dealersCardImgTitle",getDealersCardImgTitle());
        jsonMap.put("dealersCardImgAltText",getDealersCardImgAltText());
        jsonMap.put("locationErrorMsg",getLocationErrorMsg());
        jsonMap.put("getDirectionsCta",getGetDirectionsCta());
        Map<String, Object> cityMap = new HashMap<>();
        Map<String, Object> cityInnerMap = new HashMap<>();
        cityInnerMap.put("noDealerErrorMsg",getNoDealerErrorMsg());
        cityInnerMap.put("noValueErrorMsg",getNoValueErrorMsg());
        cityMap.put("validationRules",cityInnerMap);
        cityMap.put("placeholder",getPlaceholder());
        cityMap.put("icon",getIcon());
        cityMap.put("locationIcon",getLocationIcon());
        cityMap.put("locationIconAltText",getLocationIconAltText());
        cityMap.put("secondIcon",getSecondIcon());
        cityMap.put("secondLocationIcon",getSecondLocationIcon());
        cityMap.put("secondLocationIconAltText",getSecondLocationIconAltText());
        jsonMap.put("cityField",cityMap);
        return CommonUtils.toJson(jsonMap);
    }

    public String getRedirectionIconTitle() {
      return redirectionIconTitle;
    }

    public String getRedirectionIconAltText() {
      return redirectionIconAltText;
    }

    public String getDealersCardImgTitle() {
      return dealersCardImgTitle;
    }

    public String getDealersCardImgAltText() {
      return dealersCardImgAltText;
    }

    public String getLocationIcon() {
      return locationIcon;
    }

    public String getLocationIconAltText() {
      return locationIconAltText;
    }

    public String getSecondLocationIcon() {
      return secondLocationIcon;
    }

    public String getSecondLocationIconAltText() {
      return secondLocationIconAltText;
    }

    
}