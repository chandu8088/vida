package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EmiCalculatorModel {
    @ValueMapValue
    private String bgImageDesktop;
    @ValueMapValue
    private String bgImageMobile ;
    @ValueMapValue
    private String bgImageAltText;
    @ValueMapValue
    private String bgImageTitle;
    @ValueMapValue
    private String buyButtonLabel;
    @ValueMapValue
    private String buyButtonRedirectUrl;
    @ValueMapValue
    private boolean newTab;
    @ValueMapValue
    private String closeButtonLabel;
    @ValueMapValue
    private boolean showButton;
    @ValueMapValue
    private boolean showOfferCard;
    @SlingObject
    private ResourceResolver resolver;
    @Inject
    @Self
    private RedirectionCardModel redirectionCardModel;
    @Inject
    @Self
    private CalculatorCardModel calculatorCardModel;
    public String getBgImageDesktop(){
        return CommonUtils.getDMImagePathLink(bgImageDesktop,resolver);
    }
    public String getBgImageMobile(){
        return CommonUtils.getDMImagePathLink(bgImageMobile,resolver);
    }
    public String getBgImageAltText(){
        return bgImageAltText;
    }
    public String getBgImageTitle(){
        return bgImageTitle;
    }
    public String getBuyButtonLabel(){
        return buyButtonLabel;
    }
    public boolean isNewTab(){
        return newTab;
    }
    public String getCloseButtonLabel(){
        return closeButtonLabel;
    }
    public boolean isShowButton(){
        return showButton;
    }
    public String getBuyButtonRedirectUrl(){
        return buyButtonRedirectUrl;
    }
    public boolean isShowOfferCard(){
        return showOfferCard;
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("bgImageDesktop",getBgImageDesktop());
        jsonMap.put("bgImageMobile",getBgImageMobile());
        jsonMap.put("bgImageAltText",getBgImageAltText());
        jsonMap.put("bgImageTitle",getBgImageTitle());
        jsonMap.put("buyButtonLabel",getBuyButtonLabel());
        jsonMap.put("buyButtonRedirectUrl",getBuyButtonRedirectUrl());
        jsonMap.put("newTab",isNewTab());
        jsonMap.put("closeButtonLabel",getCloseButtonLabel());
        jsonMap.put("showButton",isShowButton());
        jsonMap.put("showOfferCard",isShowOfferCard());
        jsonMap.put("redirectionCard",redirectionCardModel.getComponentMap());
        jsonMap.put("emiSavingsCalculatorCardConfig",calculatorCardModel.getJson());
        return CommonUtils.toJson(jsonMap);
    }

}
