package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SavingsCalculatorModel {
    @ValueMapValue
    public String bgImageDesktop;
    @ValueMapValue
    public String bgImageMobile;
    @ValueMapValue
    public String bgImgAlt;
    @ValueMapValue
    public String bgImgTitle;
    @ValueMapValue
    public String title;
    @ValueMapValue
    public String questionText;
    @ValueMapValue
    public String totalSavingsText;
    @ValueMapValue
    public String savingsCardTextMonthly;
    @ValueMapValue
    public String savingsCardTextYearly;
    @ValueMapValue
    public String cardBgImgMobile;
    @ValueMapValue
    public String cardBgImgDesktop;
    @ValueMapValue
    public String cardBgImgAlt;
    @ValueMapValue
    public String cardBgImgTitle;
    @ValueMapValue
    public String impactTitle;
    @ValueMapValue
    public String impactBgImageMob;
    @ValueMapValue
    public String impactBgImageAlt;
    @ValueMapValue
    public String impactBgImageTitle;
    @ValueMapValue
    public String impactCardTextOne;
    @ValueMapValue
    public String impactCardTextTwo;
    @ValueMapValue
    public String savingsComparisonText;
    @ValueMapValue
    public String evBenefitsText;
    @ValueMapValue
    public String fuelText;
    @ValueMapValue
    public String evText;
    @ValueMapValue
    public String carbonUnit;
    @ValueMapValue
    public String assumptionsContent;
    @ValueMapValue
    public String assumptionsTitle;
    @ValueMapValue
    public String arrowIconDesk;
    @ValueMapValue
    public String arrowIconMob;
    @ValueMapValue
    public String arrowIconAlt;
    @ValueMapValue
    public String arrowIconTitle;
    @ValueMapValue
    public String btnLabel;
    @ValueMapValue
    public String redirectUrl;
    @SlingObject
    private ResourceResolver resolver;

    public String getBgImageDesktop() {
        return CommonUtils.getDMImagePathLink(bgImageDesktop,resolver);
    }

    public String getBgImageMobile() {
        return CommonUtils.getDMImagePathLink(bgImageMobile,resolver);
    }

    public String getBgImgAlt() {
        return bgImgAlt;
    }

    public String getBgImgTitle() {
        return bgImgTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getTotalSavingsText() {
        return totalSavingsText;
    }

    public String getSavingsCardTextMonthly() {
        return savingsCardTextMonthly;
    }

    public String getSavingsCardTextYearly() {
        return savingsCardTextYearly;
    }

    public String getCardBgImgMobile() {
        return CommonUtils.getDMImagePathLink(cardBgImgMobile,resolver);
    }

    public String getCardBgImgDesktop() {
        return CommonUtils.getDMImagePathLink(cardBgImgDesktop,resolver);
    }

    public String getCardBgImgAlt() {
        return cardBgImgAlt;
    }

    public String getCardBgImgTitle() {
        return cardBgImgTitle;
    }

    public String getImpactTitle() {
        return impactTitle;
    }

    public String getImpactBgImageMob() {
        return CommonUtils.getDMImagePathLink(impactBgImageMob,resolver);
    }

    public String getImpactBgImageAlt() {
        return impactBgImageAlt;
    }

    public String getImpactBgImageTitle() {
        return impactBgImageTitle;
    }

    public String getImpactCardTextOne() {
        return impactCardTextOne;
    }

    public String getImpactCardTextTwo() {
        return impactCardTextTwo;
    }

    public String getSavingsComparisonText() {
        return savingsComparisonText;
    }

    public String getEvBenefitsText() {
        return evBenefitsText;
    }

    public String getFuelText() {
        return fuelText;
    }

    public String getEvText() {
        return evText;
    }

    public String getCarbonUnit() {
        return carbonUnit;
    }

    public String getAssumptionsContent() {
        return assumptionsContent;
    }

    public String getAssumptionsTitle() {
        return assumptionsTitle;
    }

    public String getArrowIconDesk() {
        return CommonUtils.getDMImagePathLink(arrowIconDesk,resolver);
    }

    public String getArrowIconMob() {
        return CommonUtils.getDMImagePathLink(arrowIconMob,resolver);
    }

    public String getArrowIconAlt() {
        return arrowIconAlt;
    }

    public String getArrowIconTitle() {
        return arrowIconTitle;
    }

    public String getBtnLabel() {
        return btnLabel;
    }

    public String getRedirectUrl() {
        return CommonUtils.getLinkWithHTML(redirectUrl,resolver);
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("bgImageDesktop", getBgImageDesktop());
        jsonMap.put("bgImageMobile",getBgImageMobile());
        jsonMap.put("bgImgAlt",getBgImgAlt());
        jsonMap.put("bgImgTitle",getBgImgTitle());
        jsonMap.put("title",getTitle());
        jsonMap.put("questionText",getQuestionText());
        jsonMap.put("totalSavingsText",getTotalSavingsText());
        jsonMap.put("savingsCardTextMonthly",getSavingsCardTextMonthly());
        jsonMap.put("savingsCardTextYearly",getSavingsCardTextYearly());
        jsonMap.put("cardBgImgMobile",getCardBgImgMobile());
        jsonMap.put("cardBgImgDesktop",getCardBgImgDesktop());
        jsonMap.put("cardBgImgAlt",getCardBgImgAlt());
        jsonMap.put("cardBgImgTitle",getCardBgImgTitle());
        jsonMap.put("impactTitle",getImpactTitle());
        jsonMap.put("impactBgImageMob",getImpactBgImageMob());
        jsonMap.put("impactBgImageAlt",getImpactBgImageAlt());
        jsonMap.put("impactBgImageTitle",getImpactBgImageTitle());
        jsonMap.put("impactCardText1",getImpactCardTextOne());
        jsonMap.put("impactCardText2", getImpactCardTextTwo());
        jsonMap.put("savingsComparisonText",getSavingsComparisonText());
        jsonMap.put("evBenefitsText",getEvBenefitsText());
        jsonMap.put("fuelText",getFuelText());
        jsonMap.put("evText",getEvText());
        jsonMap.put("carbonUnit",getCarbonUnit());
        jsonMap.put("assumptionsContent",getAssumptionsContent());
        jsonMap.put("assumptionsTitle",getAssumptionsTitle());
        jsonMap.put("arrowIconDesk",getArrowIconDesk());
        jsonMap.put("arrowIconMob",getArrowIconMob());
        jsonMap.put("arrowIconAlt",getArrowIconAlt());
        jsonMap.put("arrowIconTitle",getArrowIconTitle());
        jsonMap.put("btnLabel",getBtnLabel());
        jsonMap.put("redirectUrl",getRedirectUrl());
        
        return CommonUtils.toJson(jsonMap);
    }

}
