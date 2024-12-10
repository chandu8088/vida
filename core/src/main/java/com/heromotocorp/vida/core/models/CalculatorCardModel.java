package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import java.io.IOException;
import java.util.*;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CalculatorCardModel {
    @ValueMapValue
    private String title;
    @ValueMapValue
    private String titleTag;
    @ValueMapValue
    private String subTitleTag;
    @ValueMapValue
    private String preTitleTag;
    @ValueMapValue
    private String secondTitle;
    @ValueMapValue
    private String showingCityLabel;
    @ValueMapValue
    private String selectCityLabel;
    @ValueMapValue
    private String scooterLabel;
    @ValueMapValue
    private String effectivePriceLabel;
    @ValueMapValue
    private String defaultCityState;
    @ValueMapValue
    private String locationErrorMsg;
    @ValueMapValue
    private  String scooterBgImageMobile;
    @ValueMapValue
    private String scooterBgImageLeftDesktop;
    @ValueMapValue
    private String scooterBgImageRightDesktop;
    @ValueMapValue
    private String scooterBgImageAltText;
    @ValueMapValue
    private String scooterBgImageTitle;
    @ValueMapValue
    private String selectCityIcon;
    @ValueMapValue
    private String selectCityAltText;
    @ValueMapValue
    private String selectCityAltTitle;
    @ValueMapValue
    private String selectedCityIcon;
    @ValueMapValue
    private String bikeImgMobile;
    @ValueMapValue
    private String bikeImgDesktop;
    @ValueMapValue
    private String bikeImgAltText;
    @ValueMapValue
    private String bikeImgTitle;
    @SlingObject
    private ResourceResolver resolver;

    public String getTitle(){
        return title;
    }
    public String getTitleTag(){
        return titleTag;
    }
    public String getSubTitleTag(){
        return subTitleTag;
    }
    public String getPreTitleTag(){
        return preTitleTag;
    }
    public String getSecondTitle(){
        return secondTitle;
    }
    public String getShowingCityLabel(){
        return showingCityLabel;
    }
    public String getSelectCityLabel(){
        return selectCityLabel;
    }
    public String getScooterLabel(){
        return scooterLabel;
    }
    public String getEffectivePriceLabel(){
        return effectivePriceLabel;
    }
    public String getDefaultCityState(){
        return defaultCityState;
    }
    public String getLocationErrorMsg(){
        return locationErrorMsg;
    }
    public String getScooterBgImageMobile(){
        return CommonUtils.getDMImagePathLink(scooterBgImageMobile,resolver);
    }
    public String getScooterBgImageLeftDesktop(){
        return CommonUtils.getDMImagePathLink(scooterBgImageLeftDesktop,resolver);
    }
    public String getScooterBgImageRightDesktop(){
        return CommonUtils.getDMImagePathLink(scooterBgImageRightDesktop,resolver);
    }
    public String getScooterBgImageAltText(){
        return scooterBgImageAltText;
    }
    public String getScooterBgImageTitle(){
        return scooterBgImageTitle;
    }
    public String getSelectCityIcon(){
        return CommonUtils.getDMImagePathLink(selectCityIcon,resolver);
    }
    public String getSelectCityAltText(){
        return selectCityAltText;
    }
    public String getSelectCityAltTitle(){
        return selectCityAltTitle;
    }
    public String getSelectedCityIcon(){
        return CommonUtils.getDMImagePathLink(selectedCityIcon,resolver);
    }
    public String getBikeImgMobile(){
        return CommonUtils.getDMImagePathLink(bikeImgMobile,resolver);
    }
    public String getBikeImgDesktop(){
        return CommonUtils.getDMImagePathLink(bikeImgDesktop,resolver);
    }
    public String getBikeImgAltText(){
        return bikeImgAltText;
    }
    public String getBikeImgTitle(){
        return bikeImgTitle;
    }
    public Map<String, Object> getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("title", getTitle());
        jsonMap.put("titleTag",getTitleTag());
        jsonMap.put("subTitleTag",getSubTitleTag());
        jsonMap.put("preTitleTag",getPreTitleTag());
        jsonMap.put("secondTitle",getSecondTitle());
        jsonMap.put("showingCityLabel",getShowingCityLabel());
        jsonMap.put("selectCityLabel",getSelectCityLabel());
        jsonMap.put("scooterLabel",getScooterLabel());
        jsonMap.put("effectivePriceLabel",getEffectivePriceLabel());
        jsonMap.put("defaultCityState",getDefaultCityState());
        Map<String, Object> genericConfig = new HashMap<>();
        genericConfig.put("locationErrorMsg",getLocationErrorMsg());
        jsonMap.put("genericConfig",genericConfig);
        jsonMap.put("scooterBgImageMobile",getScooterBgImageMobile());
        jsonMap.put("scooterBgImageLeftDesktop",getScooterBgImageLeftDesktop());
        jsonMap.put("scooterBgImageRightDesktop",getScooterBgImageRightDesktop());
        jsonMap.put("scooterBgImageAltText",getScooterBgImageAltText());
        jsonMap.put("scooterBgImageTitle",getScooterBgImageTitle());
        jsonMap.put("selectCityIcon",getSelectCityIcon());
        jsonMap.put("selectCityAltText",getSelectCityAltText());
        jsonMap.put("selectCityAltTitle",getSelectCityAltTitle());
        jsonMap.put("selectedCityIcon",getSelectedCityIcon());
        jsonMap.put("bikeImgMobile",getBikeImgMobile());
        jsonMap.put("bikeImgDesktop",getBikeImgDesktop());
        jsonMap.put("bikeImgAltText",getBikeImgAltText());
        jsonMap.put("bikeImgTitle",getBikeImgTitle());
        return jsonMap;
    }
}
