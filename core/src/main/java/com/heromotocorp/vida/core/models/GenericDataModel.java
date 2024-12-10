package com.heromotocorp.vida.core.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.vo.HeaderVO;

/**
 * Class storing Generic Data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class GenericDataModel {

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String navLink;

    @ValueMapValue
    private String name;

    @ValueMapValue
    private String label;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String secondarynavtext;

    @ValueMapValue
    private String secondarynavtextlink;

    @ValueMapValue
    private String mobImg;

    @ValueMapValue
    private String desktopImg;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @ValueMapValue
    private boolean newTab;

    @ValueMapValue
    private String subText;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String buttonLabel;

    @ValueMapValue
    private boolean isButton;

    @ValueMapValue
    private String cardType;

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String imagealttext;

    @ValueMapValue
    private String imageTitle;

    @SlingObject
    private ResourceResolver resolver;

    @OSGiService
    private GlobalConfig globalConfig;

    boolean enableDMFeature;

    @PostConstruct
    public void init() {
        enableDMFeature = globalConfig.enableDMFeature();
    }

    private List<HeaderVO> sitesOption;

    public void setLabel(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String getImage() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(image, resolver) : image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getNavLink() {
        return CommonUtils.getLinkWithHTML(navLink, resolver);
    }

    public void setNavLink(final String navLink) {
        this.navLink = navLink;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSitesOption(final List<HeaderVO> sitesOption) {
        this.sitesOption = new ArrayList<>(sitesOption);
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<HeaderVO> getSitesOption() {
        return sitesOption != null ? new ArrayList<>(sitesOption) : Collections.emptyList();
    }

    public void setSecondarynavtext(final String secondarynavtext) {
        this.secondarynavtext = secondarynavtext;
    }

    public void setSecondarynavtextlink(final String secondarynavtextlink) {
        this.secondarynavtextlink = secondarynavtextlink;
    }

    public String getSecondarynavtext() {
        return secondarynavtext;
    }

    public String getSecondarynavtextlink() {
        return CommonUtils.getLinkWithHTML(secondarynavtextlink, resolver);
    }

    public void setMobImg(final String mobImg) {
        this.mobImg = mobImg;
    }

    public String getMobImg() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(mobImg, resolver) : mobImg;
    }

    public void setDesktopImg(final String desktopImg) {
        this.desktopImg = desktopImg;
    }

    public String getDesktopImg() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(desktopImg, resolver) : desktopImg;
    }

    public boolean getNewTab() {
        return newTab;
    }

    public void setNewTab(final boolean newTab) {
        this.newTab = newTab;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public String getLink() {
        return CommonUtils.getLinkWithHTML(link, resolver);
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    public boolean getIsButton() {
        return isButton;
    }

    public void setIsButtton(boolean isButtton) {
        this.isButton = isButtton;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagealttext() {
        return imagealttext;
    }

    public void setImagealttext(String imagealttext) {
        this.imagealttext = imagealttext;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(String imageTitle) {
        this.imageTitle = imageTitle;
    }
}
