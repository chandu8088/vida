package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
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
import java.util.*;

/**
 * This is the main footer model
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ChargingLocatorChoosePlanModel {
    /**
     * Taking the footer data in the below list.
     */



    @ValueMapValue
    private String titleOne;

    @ValueMapValue
    private String titleTwo;

    @ValueMapValue
    private String customTitleTwoTag;

    @ValueMapValue
    private String headerContent;

    @ValueMapValue
    private String chargeLabel;

    @ValueMapValue
    private String chargeContent;

    @ValueMapValue
    private String btnLabel;

    @ValueMapValue
    private String dropDownTitle;

    @ValueMapValue
    private String planContentheader;

    @ValueMapValue
    private String pluginImgMobile;

    @ValueMapValue
    private String pluginImgDesktop;

    @ValueMapValue
    private String scrollImgMobile;

    @ValueMapValue
    private String scrollImgDesktop;

    @ValueMapValue
    private String dropDownArrowIcon;

    @ValueMapValue
    private String dropDownContent;

    @ValueMapValue
    private boolean newTab;

    @ValueMapValue
    private String navLink;

    @ValueMapValue
    private String cardMainBoldText;

    @ValueMapValue
    private String cardSubText;


    @ValueMapValue
    private String cardIcon;

    @ValueMapValue
    private String cardButtonLabel;


    @ValueMapValue
    private String cardButtonLink;

    @ValueMapValue
    private String myPlanTitle;

    @ValueMapValue
    private String confirmButton;

    @ValueMapValue
    private String cancelCtaLabel;

    @ValueMapValue
    private String confirmLink;

    @ValueMapValue
    private String dataPosition;

    @ChildResource
    private List<ChargingLocatorChoosePlanBeanModel> items;

    @SlingObject
    private ResourceResolver resolver;

    private static final Logger LOG = LoggerFactory.getLogger(ChargingLocatorChoosePlanModel.class);

    public String getTitleOne() {
        return titleOne;
    }

    public String getTitleTwo() {
        return titleTwo;
    }

    public String getHeaderContent() {
        return headerContent;
    }

    public String getChargeLabel() {
        return chargeLabel;
    }

    public String getChargeContent() {
        return chargeContent;
    }

    public String getBtnLabel() {
        return btnLabel;
    }

    public String getDropDownTitle() {
        return dropDownTitle;
    }

    public String getPlanContentheader() {
        return planContentheader;
    }

    public String getCustomTitleTwoTag() {
        return customTitleTwoTag;
    }

    public String getPluginImgMobile() {
        return CommonUtils.getDMImagePathLink(pluginImgMobile,resolver);
    }

    public String getPluginImgDesktop() {
        return  CommonUtils.getDMImagePathLink(pluginImgDesktop,resolver);
    }

    public String getScrollImgMobile() {
        return  CommonUtils.getDMImagePathLink(scrollImgMobile,resolver);
    }

    public String getScrollImgDesktop() {
        return  CommonUtils.getDMImagePathLink(scrollImgDesktop,resolver);
    }

    public String getDropDownArrowIcon() {
        return  CommonUtils.getDMImagePathLink(dropDownArrowIcon,resolver);
    }

    public String getDropDownContent() {
        return dropDownContent;
    }

    public boolean isNewTab() {
        return newTab;
    }

    public String getNavLink() {
        return  CommonUtils.getLinkWithHTML(navLink,resolver);
    }

    public List<ChargingLocatorChoosePlanBeanModel> getItems() {
        return items != null ? new ArrayList<>(items) : Collections.emptyList();
    }

    public String getCardMainBoldText() {
        return cardMainBoldText;
    }

    public String getCardSubText() {
        return cardSubText;
    }

    public String getCardIcon() {
        return CommonUtils.getDMImagePathLink(cardIcon,resolver);
    }

    public String getCardButtonLabel() {
        return cardButtonLabel;
    }

    public String getCardButtonLink() {
        return CommonUtils.getDMImagePathLink(cardButtonLink,resolver);
    }

    public String getMyPlanTitle() {
        return myPlanTitle;
    }

    public String getConfirmButton() {
        return confirmButton;
    }

    public String getCancelCtaLabel() {
        return cancelCtaLabel;
    }

    public String getConfirmLink() {
        return CommonUtils.getLinkWithHTML(confirmLink,resolver);
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("titleOne", getTitleOne());
        jsonMap.put("titleTwo", getTitleTwo());
        jsonMap.put("customTitleTwoTag", getCustomTitleTwoTag());
        jsonMap.put("pluginImgMobile", getPluginImgMobile());
        jsonMap.put("pluginImgDesktop", getPluginImgDesktop());
        jsonMap.put("chargeLabel", getChargeLabel());
        jsonMap.put("chargeContent", getChargeContent());
        jsonMap.put("btnLabel", getBtnLabel());
        jsonMap.put("navLink", getNavLink());
        jsonMap.put("newTab", isNewTab());
        jsonMap.put("scrollImgMobile", getScrollImgMobile());
        jsonMap.put("scrollImgDesktop", getScrollImgDesktop());
        jsonMap.put("dropDownTitle", getDropDownTitle());
        jsonMap.put("dropDownArrowIcon", getDropDownArrowIcon());
        jsonMap.put("dropDownContent", getDropDownContent());
        jsonMap.put("headerContent", getHeaderContent());
        Map<String, Object> planContentMap = new HashMap<>();
        planContentMap.put("header", getPlanContentheader());
        planContentMap.put("tabs", getItems());
        jsonMap.put("plansContent", planContentMap);
        Map<String, Object> cardConfig = new HashMap<>();
        cardConfig.put("cardMainBoldText", getCardMainBoldText());
        cardConfig.put("cardSubText", getCardSubText());
        cardConfig.put("cardIcon", getCardIcon());
        cardConfig.put("cardButtonLabel", getCardButtonLabel());
        cardConfig.put("cardButtonLink", getCardButtonLink());
        jsonMap.put("chargingLocatorCard", cardConfig);
        Map<String, Object> myPlanPopup = new HashMap<>();
        myPlanPopup.put("popupMsg", getMyPlanTitle());
        myPlanPopup.put("confirmButton", getConfirmButton());
        myPlanPopup.put("cancelButton", getCancelCtaLabel());
        myPlanPopup.put("confirmLink", getConfirmLink());
        jsonMap.put("popUpObj", myPlanPopup);
        return CommonUtils.toJson(jsonMap);
    }

}
