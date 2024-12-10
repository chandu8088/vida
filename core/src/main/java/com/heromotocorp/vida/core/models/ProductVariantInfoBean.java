package com.heromotocorp.vida.core.models;


import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * The type Product variant info bean.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariantInfoBean {

    /**
     * The Mobile Image path.
     */
    String buyVidaMobileImagePath;

    /**
     * Gets Mobile image path.
     *
     * @return the buyVida mobile image path
     */
    public String getBuyVidaMobileImagePath() {
        return buyVidaMobileImagePath;
    }

    /**
     * Sets Mobile image path.
     *
     * @param buyVidaMobileImagePath the image path
     */
    public void setBuyVidaMobileImagePath(String buyVidaMobileImagePath) {
        this.buyVidaMobileImagePath = buyVidaMobileImagePath;
    }

    /**
     * The Image path.
     */
    String buyVidaDesktopImagePath;

    /**
     * Gets image path.
     *
     * @return the buyVida mobile image path
     */
    public String getBuyVidaDesktopImagePath() {
        return buyVidaDesktopImagePath;
    }

    /**
     * Sets image path.
     *
     * @param buyVidaDesktopImagePath the image path
     */
    public void setBuyVidaDesktopImagePath(String buyVidaDesktopImagePath) {
        this.buyVidaDesktopImagePath = buyVidaDesktopImagePath;
    }

    /**
     * The Variant range.
     */
    String variant_range_value;

    /**
     * The Variant range.
     */
    String variant_range_unit;
    /**
     * The Variant charging time.
     */
    String variant_charging_time_value;
    /**
     * The Variant charging time.
     */
    String variant_charging_time_unit;
    /**
     * The Variant accelerator.
     */
    String variant_accelerator_unit;
    /**
     * The Variant top speed.
     */
    String variant_top_speed_value;
    /**
     * The Variant top speed.
     */
    String variant_top_speed_unit;


    /**
     * Gets variant range value.
     *
     * @return the variant range value
     */
    public String getVariant_range_value() {
        return variant_range_value;
    }

    /**
     * Sets variant range value.
     *
     * @param variant_range_value the variant range value
     */
    public void setVariant_range_value(String variant_range_value) {
        this.variant_range_value = variant_range_value;
    }

    /**
     * Gets variant range unit.
     *
     * @return the variant range unit
     */
    public String getVariant_range_unit() {
        return variant_range_unit;
    }

    /**
     * Sets variant range unit.
     *
     * @param variant_range_unit the variant range unit
     */
    public void setVariant_range_unit(String variant_range_unit) {
        this.variant_range_unit = variant_range_unit;
    }

    /**
     * Gets variant charging time value.
     *
     * @return the variant charging time value
     */
    public String getVariant_charging_time_value() {
        return variant_charging_time_value;
    }

    /**
     * Sets variant charging time value.
     *
     * @param variant_charging_time_value the variant charging time value
     */
    public void setVariant_charging_time_value(String variant_charging_time_value) {
        this.variant_charging_time_value = variant_charging_time_value;
    }

    /**
     * Gets variant charging time unit.
     *
     * @return the variant charging time unit
     */
    public String getVariant_charging_time_unit() {
        return variant_charging_time_unit;
    }

    /**
     * Sets variant charging time unit.
     *
     * @param variant_charging_time_unit the variant charging time unit
     */
    public void setVariant_charging_time_unit(String variant_charging_time_unit) {
        this.variant_charging_time_unit = variant_charging_time_unit;
    }

    /**
     * Gets variant accelerator unit.
     *
     * @return the variant accelerator unit
     */
    public String getVariant_accelerator_unit() {
        return variant_accelerator_unit;
    }

    /**
     * Sets variant accelerator unit.
     *
     * @param variant_accelerator_unit the variant accelerator unit
     */
    public void setVariant_accelerator_unit(String variant_accelerator_unit) {
        this.variant_accelerator_unit = variant_accelerator_unit;
    }

    /**
     * Gets variant top speed value.
     *
     * @return the variant top speed value
     */
    public String getVariant_top_speed_value() {
        return variant_top_speed_value;
    }

    /**
     * Sets variant top speed value.
     *
     * @param variant_top_speed_value the variant top speed value
     */
    public void setVariant_top_speed_value(String variant_top_speed_value) {
        this.variant_top_speed_value = variant_top_speed_value;
    }

    /**
     * Gets variant top speed unit.
     *
     * @return the variant top speed unit
     */
    public String getVariant_top_speed_unit() {
        return variant_top_speed_unit;
    }

    /**
     * Sets variant top speed unit.
     *
     * @param variant_top_speed_unit the variant top speed unit
     */
    public void setVariant_top_speed_unit(String variant_top_speed_unit) {
        this.variant_top_speed_unit = variant_top_speed_unit;
    }


    Map<String, String> priceMap;

    public void setCitypriceMap(Map<String, String> priceMap) {
        this.priceMap = priceMap;
    }


    public Map<String, String> getCitypriceMap() {
		return priceMap;
	}

    Map<String, String> accelerationMap;

    public void setAcceration(Map<String, String> accelerationMap) {
        this.accelerationMap = accelerationMap;
    }


    public Map<String, String> getAcceration() {
		return accelerationMap;
	}

    String altText;

    /**
     * Gets name.
     *
     * @return the varinat name
     */
    public String getAltText() {
        return altText;
    }

    /**
     * Sets name.
     *
     * @param altText
     */
    public void setAltText(String altText) {
        this.altText = altText;
    }

    String sectionId;

    /**
     * Gets name.
     *
     * @return the varinat name
     */
    public String getSectionid() {
        return sectionId;
    }

    /**
     * Sets sectionId.
     *
     * @param sectionId
     */
    public void setSectionid(String sectionId) {
        this.sectionId = sectionId;
    }

    String variant_name;

    /**
     * Gets name.
     *
     * @return the variant name
     */
    public String getVariant_name() {
        return variant_name;
    }

    /**
     * Sets variant name.
     *
     * @param variant_name
     */
    public void setVariant_name(String variant_name) {
        this.variant_name = variant_name;
    }

    String buyNowLink;

    public String getBuyNowLink() {
        return buyNowLink;
    }

    public void setBuyNowLink(String buyNowLink) {
        this.buyNowLink = buyNowLink;
    }
    
    boolean buyNowNewtab;

    public boolean isBuyNowNewtab() {
        return buyNowNewtab;
    }

    public void setBuyNowNewtab(boolean buyNowNewtab) {
        this.buyNowNewtab = buyNowNewtab;
    }

    String learnMoreLink;

    public String getLearnMoreLink() {
        return learnMoreLink;
    }

    public void setLearnMoreLink(String learnMoreLink) {
        this.learnMoreLink = learnMoreLink;
    }
    
    boolean learnMoreNewTab;

    public boolean isLearnMoreNewTab() {
        return learnMoreNewTab;
    }

    public void setLearnMoreNewTab(boolean learnMoreNewTab) {
        this.learnMoreNewTab = learnMoreNewTab;
    }

}