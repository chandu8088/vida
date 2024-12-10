package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;

import org.apache.commons.text.WordUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the main footer model
 */
@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DealershipFinderModel {
    /**
     * Taking the footer data in the below list.
     */

    @SlingObject
    SlingHttpServletRequest request;

    @ValueMapValue
    private String titleText;

    @ValueMapValue
    private String titleTag;

    @ValueMapValue
    private String dealershipFinderTitle;

    @ValueMapValue
    private String popularCitiesText;

    @ValueMapValue
    private String dealershipFinderBgDesktop;

    @ValueMapValue
    private String redirectionUrl;

    @ValueMapValue
    private String dealershipFinderBgMobile;

    @ValueMapValue
    private String placeholder;

    @ValueMapValue
    private String noDealerErrorMsg;

    @ValueMapValue
    private String bannerBgDesktop;

    @ValueMapValue
    private String bannerTitle;

    @ValueMapValue
    private String bannerBgMobile;

    @ValueMapValue
    private String bannerImgDesktop;

    @ValueMapValue
    private String bannerImgMobile;

    @ValueMapValue
    private String bannerImgAlt;

    @ValueMapValue
    private String bannerImgTitle;

    @ValueMapValue
    private String dealersMapPrimaryText;

    @ValueMapValue
    private String dealersMapSecondaryText;

    @ValueMapValue
    private String noValueErrorMsg;

    @ValueMapValue
    private String geoLocationErrorMsg;

    @ValueMapValue
    private String geolocationIcon;

    @ValueMapValue
    private String geolocationSecondIcon;

    @ValueMapValue
    private String addressText;

    @ValueMapValue
    private String cardClosedMobileImg;

    @ValueMapValue
    private String cardClosedDesktopImg;

    @ValueMapValue
    private String cardClosedImgAlt;

    @ValueMapValue
    private String cardClosedImgTitle;

    @ValueMapValue
    private String cardOpenedMobileImg;

    @ValueMapValue
    private String cardOpenedDesktopImg;

    @ValueMapValue
    private String cardOpenedImgAlt;

    @ValueMapValue
    private String cardOpenedImgTitle;

    @ValueMapValue
    private String mobileIcon;

    @ValueMapValue
    private String messageIcon;

    @ValueMapValue
    private String clockIcon;

    @ValueMapValue
    private String calendarIcon;

    @ValueMapValue
    private String websiteUrl;

    @ValueMapValue
    private String timeSlot;

    @ValueMapValue
    private String daySlot;

    @ValueMapValue
    private String directionsText;

    @ValueMapValue
    private String directionsNavLink;

    @ValueMapValue
    private boolean directionsNewTab;

    @ValueMapValue
    private String loadMoreText;

    @ValueMapValue
    private String loadLessText;

    @ValueMapValue
    private String dealersInfoTabTitle;

    @ValueMapValue
    private String dealersInfoTabText;

    @ValueMapValue
    private boolean showCityBanner;

    @ValueMapValue
    private boolean showDealersMap;

    @ValueMapValue
    private boolean showDealersInfoTab;

    @ValueMapValue
    private String dataPosition;

    @ChildResource
    private List<DealershipFinderBeanModel> items;

    @ChildResource
    private List<DealershipFinderBeanModel> dealersInfoTabContent;

    @SlingObject
    private ResourceResolver resolver;

    private static final Logger LOG = LoggerFactory.getLogger(DealershipFinderModel.class);

    public String getTitleText() {
        return titleText;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public String getDealershipFinderTitle() {
        return dealershipFinderTitle;
    }

    public String getPopularCitiesText() {
        return popularCitiesText;
    }

    public String getDealershipFinderBgDesktop() {
        return CommonUtils.getDMImagePathLink(dealershipFinderBgDesktop, resolver);
    }

    public String getRedirectionUrl() {
        return CommonUtils.getLinkWithHTML(redirectionUrl, resolver);
    }

    public String getDealershipFinderBgMobile() {
        return CommonUtils.getDMImagePathLink(dealershipFinderBgMobile, resolver);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getNoDealerErrorMsg() {
        return noDealerErrorMsg;
    }

    public List<DealershipFinderBeanModel> getItems() {
        return items != null ? new ArrayList<>(items) : Collections.emptyList();
    }

    public String getBannerBgDesktop() {
        return bannerBgDesktop;
    }

    public String getBannerBgMobile() {
        return bannerBgMobile;
    }

    public String getBannerImgDesktop() {
        return bannerImgDesktop;
    }

    public String getBannerImgMobile() {
        return bannerImgMobile;
    }

    public String getBannerImgAlt() {
        return bannerImgAlt;
    }

    public String getBannerImgTitle() {
        return bannerImgTitle;
    }

    public String getDealersMapPrimaryText() {
        return dealersMapPrimaryText;
    }

    public String getDealersMapSecondaryText() {
        return dealersMapSecondaryText;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public String getNoValueErrorMsg() {
        return noValueErrorMsg;
    }

    public String getGeoLocationErrorMsg() {
        return geoLocationErrorMsg;
    }

    public List<DealershipFinderBeanModel> getDealersInfoTabContent() {
        return dealersInfoTabContent != null ? new ArrayList<>(dealersInfoTabContent) : Collections.emptyList();
    }

    public String getGeolocationIcon() {
        return CommonUtils.getDMImagePathLink(geolocationIcon, resolver);
    }

    public String getGeolocationSecondIcon() {
        return CommonUtils.getDMImagePathLink(geolocationSecondIcon, resolver);
    }

    public String getAddressText() {
        return addressText;
    }

    public String getCardClosedMobileImg() {
        return cardClosedMobileImg;
    }

    public String getCardClosedDesktopImg() {
        return cardClosedDesktopImg;
    }

    public String getCardClosedImgAlt() {
        return cardClosedImgAlt;
    }

    public String getCardClosedImgTitle() {
        return cardClosedImgTitle;
    }

    public String getCardOpenedMobileImg() {
        return cardOpenedMobileImg;
    }

    public String getCardOpenedDesktopImg() {
        return cardOpenedDesktopImg;
    }

    public String getCardOpenedImgAlt() {
        return cardOpenedImgAlt;
    }

    public String getCardOpenedImgTitle() {
        return cardOpenedImgTitle;
    }

    public String getMobileIcon() {
        return mobileIcon;
    }

    public String getMessageIcon() {
        return messageIcon;
    }

    public String getClockIcon() {
        return clockIcon;
    }

    public String getCalendarIcon() {
        return calendarIcon;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public String getDaySlot() {
        return daySlot;
    }

    public String getDirectionsText() {
        return directionsText;
    }

    public String getDirectionsNavLink() {
        return directionsNavLink;
    }

    public boolean isDirectionsNewTab() {
        return directionsNewTab;
    }

    public String getLoadMoreText() {
        return loadMoreText;
    }

    public String getLoadLessText() {
        return loadLessText;
    }

    public String getDealersInfoTabTitle() {
        return dealersInfoTabTitle;
    }

    public String getDealersInfoTabText() {
        return dealersInfoTabText;
    }

    public boolean isShowCityBanner() {
        return showCityBanner;
    }

    public boolean isShowDealersMap() {
        return showDealersMap;
    }

    public boolean isShowDealersInfoTab() {
        return showDealersInfoTab;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getCityName() {
        String cityName = request.getRequestPathInfo().getSelectorString();
        cityName = cityName.contains("-") ? cityName.replaceAll("-", " ") : cityName;
        return WordUtils.capitalizeFully(cityName);
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dealershipFinderTitle", getDealershipFinderTitle());
        jsonMap.put("titleText", getTitleText());
        jsonMap.put("titleTag", getTitleTag());
        jsonMap.put("popularCitiesText", getPopularCitiesText());
        jsonMap.put("popularCitiesList", getItems());
        jsonMap.put("dealershipFinderBgDesktop", getDealershipFinderBgDesktop());
        jsonMap.put("dealershipFinderBgMobile", getDealershipFinderBgMobile());
        jsonMap.put("redirectionUrl", getRedirectionUrl());
        Map<String, Object> cityMap = new HashMap<>();
        Map<String, Object> cityInnerMap = new HashMap<>();
        cityInnerMap.put("noDealerErrorMsg", getNoDealerErrorMsg());
        cityInnerMap.put("noValueErrorMsg", getNoValueErrorMsg());
        cityMap.put("validationRules", cityInnerMap);
        cityMap.put("placeholder", getPlaceholder());
        cityMap.put("icon", getGeolocationIcon());
        cityMap.put("secondIcon", getGeolocationSecondIcon());
        jsonMap.put("cityField", cityMap);
        jsonMap.put("geoLocationErrorMsg", getGeoLocationErrorMsg());
        jsonMap.put("dataPosition", getDataPosition());
        return CommonUtils.toJson(jsonMap);
    }

    public String getCityTemplateJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("isShowCityBanner", isShowCityBanner());
        jsonMap.put("isShowDealersMap", isShowDealersMap());
        jsonMap.put("cityName", getCityName());
        jsonMap.put("isShowDealersInfoTab", isShowDealersInfoTab());
        Map<String, Object> cityMap = new HashMap<>();
        cityMap.put("bannerTitle", getBannerTitle());
        cityMap.put("bannerBgDesktop", getBannerBgDesktop());
        cityMap.put("bannerBgMobile", getBannerBgMobile());
        cityMap.put("bannerImgDesktop", getBannerImgDesktop());
        cityMap.put("bannerImgMobile", getBannerImgMobile());
        cityMap.put("bannerImgAlt", getBannerImgAlt());
        cityMap.put("bannerImgTitle", getBannerImgTitle());
        jsonMap.put("cityBannerConfig", cityMap);
        Map<String, Object> dealersMap = new HashMap<>();
        dealersMap.put("dealersMapPrimaryText", getDealersMapPrimaryText());
        dealersMap.put("dealersMapSecondaryText", getDealersMapSecondaryText());
        dealersMap.put("dealersMapLocationInfo", getItems());
        jsonMap.put("dealersMapConfig", dealersMap);
        Map<String, Object> dealerInfoMap = new HashMap<>();
        Map<String, Object> dealersInfoCardMap = new HashMap<>();
        dealersInfoCardMap.put("addressText", getAddressText());
        dealersInfoCardMap.put("cardClosedMobileImg", getCardClosedMobileImg());
        dealersInfoCardMap.put("cardClosedDesktopImg", getCardClosedDesktopImg());
        dealersInfoCardMap.put("cardClosedImgAlt", getCardClosedImgAlt());
        dealersInfoCardMap.put("cardClosedImgTitle", getCardClosedImgTitle());
        dealersInfoCardMap.put("cardOpenedMobileImg", getCardOpenedMobileImg());
        dealersInfoCardMap.put("cardOpenedDesktopImg", getCardOpenedDesktopImg());
        dealersInfoCardMap.put("cardOpenedImgAlt", getCardOpenedImgAlt());
        dealersInfoCardMap.put("cardOpenedImgTitle", getCardOpenedImgTitle());
        dealersInfoCardMap.put("mobileIcon", getMobileIcon());
        dealersInfoCardMap.put("messageIcon", getMessageIcon());
        dealersInfoCardMap.put("clockIcon", getClockIcon());
        dealersInfoCardMap.put("calendarIcon", getCalendarIcon());
        dealersInfoCardMap.put("websiteUrl", getWebsiteUrl());
        dealersInfoCardMap.put("timeSlot", getTimeSlot());
        dealersInfoCardMap.put("daySlot", getDaySlot());
        dealersInfoCardMap.put("getDirectionsText", getDirectionsText());
        dealersInfoCardMap.put("getDirectionsNavLink", getDirectionsNavLink());
        dealersInfoCardMap.put("getDirectionsNewTab", isDirectionsNewTab());
        dealersInfoCardMap.put("loadMoreText", getLoadMoreText());
        dealersInfoCardMap.put("loadLessText", getLoadLessText());
        dealerInfoMap.put("dealersInfoCardConfig", dealersInfoCardMap);
        dealerInfoMap.put("dealersInfoTabContent", getDealersInfoTabContent());
        dealerInfoMap.put("dealersInfoTabText", getDealersInfoTabText());
        dealerInfoMap.put("dealersInfoTabTitle", getDealersInfoTabTitle());
        jsonMap.put("dealersInfoTabConfig", dealerInfoMap);
        jsonMap.put("dealersMapConfig", dealersMap);
        return CommonUtils.toJson(jsonMap);
    }
}
