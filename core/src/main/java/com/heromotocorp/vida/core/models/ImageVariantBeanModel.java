package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;

/**
 * Class storing Image Variant Data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ImageVariantBeanModel {

    @ValueMapValue
    private String sectionid;

    @ValueMapValue
    private String varinatMobileImage;

    @ValueMapValue
    private String varinatDesktopImage;

    @ValueMapValue
    private String altText;

    @ValueMapValue
    private String order;

    @ValueMapValue
    private String buyVariantImageDesktop;

    @ValueMapValue
    private String buyVariantImageMobile;

    @ValueMapValue
    private String imageTitleVariant;

    @ValueMapValue
    private String buyNowLink;

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @ValueMapValue
    private boolean buyNowNewtab;

    @ValueMapValue
    private String learnMoreLink;
    
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    @ValueMapValue
    private boolean learnMoreNewTab;

    @SlingObject
    private ResourceResolver resolver;

    public void setSectionid(final String sectionid) {
        this.sectionid = sectionid;
    }

    public String getSectionid() {
        return sectionid;
    }

    public void setVarinatMobileImage(final String varinatMobileImage) {
        this.varinatMobileImage = varinatMobileImage;
    }

    public String getVarinatMobileImage() {
        return CommonUtils.getDMImagePathLink(varinatMobileImage, resolver);
    }

    public void setVarinatDesktopImage(final String varinatDesktopImage) {
        this.varinatDesktopImage = varinatDesktopImage;
    }

    public String getVarinatDesktopImage() {
        return CommonUtils.getDMImagePathLink(varinatDesktopImage, resolver);
    }

    public void setAltText(final String altText) {
        this.altText = altText;
    }

    public String getAltText() {
        return altText;
    }

    public void setOrder(final String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }

    public void setBuyVariantImageMobile(final String buyVariantImageMobile) {
        this.buyVariantImageMobile = buyVariantImageMobile;
    }

    public String getBuyVariantImageMobile() {
        return buyVariantImageMobile;
    }

    public void setBuyVariantImageDesktop(final String buyVariantImageDesktop) {
        this.buyVariantImageDesktop = buyVariantImageDesktop;
    }

    public String getBuyVariantImageDesktop() {
        return buyVariantImageDesktop;
    }

    public void setImageTitleVariant(final String imageTitleVariant) {
        this.imageTitleVariant = imageTitleVariant;
    }

    public String getImageTitleVariant() {
        return imageTitleVariant;
    }

    public String getBuyNowLink() {
        return CommonUtils.getLinkWithHTML(buyNowLink, resolver);
    }

    public void setBuyNowLink(String buyNowLink) {
        this.buyNowLink = buyNowLink;
    }

    public boolean isBuyNowNewtab() {
        return buyNowNewtab;
    }

    public void setBuyNowNewtab(boolean buyNowNewtab) {
        this.buyNowNewtab = buyNowNewtab;
    }

    public String getLearnMoreLink() {
        return learnMoreLink;
    }

    public void setLearnMoreLink(String learnMoreLink) {
        this.learnMoreLink = learnMoreLink;
    }

    public boolean isLearnMoreNewTab() {
        return learnMoreNewTab;
    }

    public void setLearnMoreNewTab(boolean learnMoreNewTab) {
        this.learnMoreNewTab = learnMoreNewTab;
    }

}