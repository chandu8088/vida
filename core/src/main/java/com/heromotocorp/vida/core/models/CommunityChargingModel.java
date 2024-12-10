package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CommunityChargingModel extends RedirectionCardModel{

    @ValueMapValue
    private String pretitle;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String desktopimage;

    @ValueMapValue
    private String mobileimage;

    @ValueMapValue
    private String title2;

    @ValueMapValue
    private String description2;

    @ValueMapValue
    private String ctatext;

    @ValueMapValue
    private String url;

    @ValueMapValue
    private String checkbox;

    @ValueMapValue
    private String titleTag;

    @ValueMapValue
    private String titleTag2;

    @ValueMapValue
    private String titleTag3;

    @ValueMapValue
    private String imageTitle;

    @ValueMapValue
    private String imageAlt;

    @ValueMapValue
    private String desPositionDesktop;

    @ValueMapValue
    private Boolean inclueCitySearch;

    @ValueMapValue
    private Boolean includeRedirectionCard;

    @ValueMapValue
    private Boolean includeChargingStationBanner;

    @ValueMapValue
    private String cardText;

    @ValueMapValue
    private String cardTextColor;

    @ValueMapValue
    private String cardBgColor;

    @ValueMapValue
    private String redirectImage;

    @ValueMapValue
    private String redirectLink;

    @ValueMapValue
    private Boolean newtab;

    @ValueMapValue
    private String bannerTitle;

    @ValueMapValue
    private String plusIcon;

    @ValueMapValue
    private String plusIconAlt;

    @ValueMapValue
    private String emojiImg;

    @ValueMapValue
    private String emojiImgAlt;

    @ValueMapValue
    private boolean isVideo;

    @ValueMapValue
    private String videoPath;

    @ValueMapValue
    private String videoDesktopImg;

    @ValueMapValue
    private String videoMobileImg;

    @ValueMapValue
    private String videoPreTitle;

    @ValueMapValue
    private String videoTitle;

    @ValueMapValue
    private String ytLink;

    @ValueMapValue
    private String playButton;

    @ValueMapValue
    private String videoDesktopImgAlt;

    @ValueMapValue
    private String videoMobileImgAlt;

    @ValueMapValue
    private String playButtonAlt;

    @ValueMapValue
    private String searchCityText;

    @ValueMapValue
    private String searchIcon;

    @ValueMapValue
    private String searchIconAlt;

    @ValueMapValue
    private String dropDownIcon;

    @ValueMapValue
    private String dropDownIconAlt;

    @ValueMapValue
    private String bannerDesktopPosition;

    @ValueMapValue
    private String dataPosition;

    @ValueMapValue
    private String chargingCountLabel;

    @ValueMapValue
    private String chargingStationNotAvailable;

    @OSGiService
    private GlobalConfig globalConfig;

    @ValueMapValue
    private String ctaLabel;

    @ValueMapValue
    private boolean showCta;

    @ValueMapValue
    private String ctaUrl;

    boolean enableDMFeature;

    @ChildResource
    private List<CommunityChargingBeanModel> multivalues;

    @ChildResource
    private List<CommunityChargingBeanModel> cardImg;

    @PostConstruct
    public void init() {
        enableDMFeature = globalConfig.enableDMFeature();
    }

    public List<CommunityChargingBeanModel> getMultivalues() {
        return multivalues != null ? new ArrayList<>(multivalues) : Collections.emptyList();
    }

    public List<CommunityChargingBeanModel> getCardImg() {
        return cardImg != null ? new ArrayList<>(cardImg) : Collections.emptyList();
    }

    public String getPretitle() {
        return pretitle;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDesktopimage() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(desktopimage, resolver) : desktopimage;
    }

    public String getMobileimage() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(mobileimage, resolver) : mobileimage;
    }

    public String getTitle2() {
        return title2;
    }

    public String getDescription2() {
        return description2;
    }

    public String getCtatext() {
        return ctatext;
    }

    public String getUrl() {
        return CommonUtils.getLinkWithHTML(url,resolver);
    }

    public boolean getCheckbox() {
        return checkbox != null && checkbox.equalsIgnoreCase("true");
    }

    public String getTitleTag() {
        return titleTag;
    }

    public String getTitleTag2() {
        return titleTag2;
    }

    public String getTitleTag3() {
        return titleTag3;
    }

    public String getDesPositionDesktop() {
        return desPositionDesktop;
    }

    public Boolean getInclueCitySearch() {
        return inclueCitySearch;
    }

    public Boolean getIncludeRedirectionCard() {
        return includeRedirectionCard;
    }

    public Boolean getIncludeChargingStationBanner() {
        return includeChargingStationBanner;
    }

    public Boolean getNewtab() {
        return newtab;
    }

    public String getBannerTitle() {
        return bannerTitle;
    }

    public String getPlusIcon() {
        return plusIcon;
    }

    public String getPlusIconAlt() {
        return plusIconAlt;
    }

    public String getEmojiImg() {
        return emojiImg;
    }

    public String getEmojiImgAlt() {
        return emojiImgAlt;
    }

    public boolean getIsVideo() {
        return isVideo;
    }

    public String getVideoPath() {
        return enableDMFeature ? CommonUtils.getDMVideoPathLink(videoPath, resolver) : videoPath;
    }

    public String getVideoDesktopImg() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(videoDesktopImg, resolver) : videoDesktopImg;
    }

    public String getVideoMobileImg() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(videoMobileImg, resolver) : videoMobileImg;
    }

    public String getVideoPreTitle() {
        return videoPreTitle;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public String getYtLink() {
        return ytLink;
    }

    public String getPlayButton() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(playButton, resolver) : playButton;
    }

    public String getVideoDesktopImgAlt() {
        return videoDesktopImgAlt;
    }

    public String getVideoMobileImgAlt() {
        return videoMobileImgAlt;
    }

    public String getPlayButtonAlt() {
        return playButtonAlt;
    }

    public String getSearchCityText() {
        return searchCityText;
    }

    public String getSearchIcon() {
        return searchIcon;
    }

    public String getSearchIconAlt() {
        return searchIconAlt;
    }

    public String getDropDownIcon() {
        return dropDownIcon;
    }

    public String getDropDownIconAlt() {
        return dropDownIconAlt;
    }

    public String getBannerDesktopPosition() {
        return bannerDesktopPosition;
    }
    
    public String getChargingCountLabel() {
        return chargingCountLabel;
    }

    public String getChargingStationNotAvailable() {
        return chargingStationNotAvailable;
    }

    public String getCtaLabel() {
        return ctaLabel;
    }

    public boolean  getShowCta() {
        return showCta;
    }

    public String getCtaUrl() {
        return ctaUrl;
    }

    public String getChargingJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("chargingInformation", getMultivalues());
        jsonMap.put("preTitle", getPretitle());
        jsonMap.put("description", getDescription());
        jsonMap.put("ctaLabel", getCtaLabel());
        jsonMap.put("ctaUrl", getCtaUrl());
        jsonMap.put("showCta", getShowCta());
        jsonMap.put("titleTag3", getTitleTag3());
        if(StringUtils.isNotBlank(getTitle())){
            jsonMap.put("title", getTitle());
            jsonMap.put("titleTag", getTitleTag());
        }
        if(StringUtils.isNotBlank(getTitle2()) || StringUtils.isNotBlank(getDescription2())){
            jsonMap.put("title2", getTitle2());
            jsonMap.put("description2", getDescription2());
            jsonMap.put("titleTag2", getTitleTag2());
        }
        jsonMap.put("ctaText", getCtatext());
        if(StringUtils.isNotBlank(getUrl())){
            jsonMap.put("URL", getUrl());
            jsonMap.put("newTab", getCheckbox());
        }
        
        jsonMap.put("descPositionDesktop", getDesPositionDesktop());
        jsonMap.put("isVideo", getIsVideo());
        if (Boolean.TRUE.equals(getIsVideo())) {
            Map<String, Object> videoMap = new HashMap<>();
            videoMap.put("video", getVideoPath());
            videoMap.put("videoDesktopImg",getVideoDesktopImg());
            videoMap.put("videoMobileImg",getVideoMobileImg());
            videoMap.put("videoPreTitle",getVideoPreTitle());
            videoMap.put("videoTitle",getVideoTitle());
            videoMap.put("ytLink",getYtLink());
            videoMap.put("playButton",getPlayButton());
            videoMap.put("videoDesktopImgAlt",getVideoDesktopImgAlt());
            videoMap.put("videoMobileImgAlt",getVideoMobileImgAlt());
            videoMap.put("playButtonAlt",getPlayButtonAlt());
            jsonMap.put("videoContent", videoMap);
        } else {
            jsonMap.put("desktopImage", getDesktopimage());
            jsonMap.put("mobileImage", getMobileimage());
            jsonMap.put("imageTitle", getImageTitle());
            jsonMap.put("imageAlt", getImageAlt());
        }
        jsonMap.put("includeCitySearch", getInclueCitySearch());
        if (Boolean.TRUE.equals(getInclueCitySearch())) {
            Map<String, Object> cityMap = new HashMap<>();
            cityMap.put("searchCityText", getSearchCityText());
            cityMap.put("searchIcon", getSearchIcon());
            cityMap.put("searchIconAlt", getSearchIconAlt());
            cityMap.put("dropDownIcon", getDropDownIcon());
            cityMap.put("dropDownIconAlt", getDropDownIconAlt());
            cityMap.put("chargingCountLabel", getChargingCountLabel());
            cityMap.put("chargingStationNotAvailable", getChargingStationNotAvailable());
            jsonMap.put("cityConfig", cityMap);
        }
        jsonMap.put("includeRedirectionCard", getIncludeRedirectionCard());
        if (Boolean.TRUE.equals(getIncludeRedirectionCard())) {
            jsonMap.put("redirectionCard", getComponentMap());
        }
        jsonMap.put("includeChargingStationBanner", getIncludeChargingStationBanner());
        if (Boolean.TRUE.equals(getIncludeChargingStationBanner())) {
            Map<String, Object> chargingMap = new HashMap<>();
            chargingMap.put("bannerTitle", getBannerTitle());
            chargingMap.put("plusIcon", getPlusIcon());
            chargingMap.put("plusIconAlt", getPlusIconAlt());
            chargingMap.put("emojiImg", getEmojiImg());
            chargingMap.put("emojiImgAlt", getEmojiImgAlt());
            chargingMap.put("chargingStationBannerDesktopPosition", getBannerDesktopPosition());
            jsonMap.put("chargingStationBanner", chargingMap);
        }
        return CommonUtils.toJson(jsonMap);
    }
}