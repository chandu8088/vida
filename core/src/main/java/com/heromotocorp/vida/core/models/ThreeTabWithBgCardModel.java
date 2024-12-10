package com.heromotocorp.vida.core.models;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.heromotocorp.vida.core.utils.CommonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Sling Model for Three Tab With Background Card Component
 */
@Model(adaptables = {
        Resource.class }, resourceType = "vida/components/vida-2.0/content/threetabwithbackgroundcard", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ThreeTabWithBgCardModel {

    private static final String TAB_NAME = "tabName";

    private static final String BACKGROUND_DESKTOP_IMAGE = "bgDesktopImg";

    private static final String BACKGROUND_MOBILE_IMAGE = "bgMobileImg";

    private static final String DESCRIPTION = "description";

    private static final String CTA_LABEL = "ctaLabel";

    private static final String CTA_LINK = "ctaLink";

    private static final String IMAGE_ALT_TEXT = "imageAlt";

    private static final String IMAGE_ALT_TITLE = "imageTitle";

    /** The resource resolver. */
    @SlingObject
    ResourceResolver resolver;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String titleTab1;

    @ValueMapValue
    private String bgDesktopImgTab1;

    @ValueMapValue
    private String bgMobileImgTab1;

    @ValueMapValue
    private String descriptionTab1;

    @ValueMapValue
    private String ctaLabelTab1;

    @ValueMapValue
    private String ctaLinkTab1;

    @ValueMapValue
    private String titleTab2;

    @ValueMapValue
    private String bgDesktopImgTab2;

    @ValueMapValue
    private String bgMobileImgTab2;

    @ValueMapValue
    private String descriptionTab2;

    @ValueMapValue
    private String ctaLabelTab2;

    @ValueMapValue
    private String ctaLinkTab2;

    @ValueMapValue
    private String titleTab3;

    @ValueMapValue
    private String bgDesktopImgTab3;

    @ValueMapValue
    private String bgMobileImgTab3;

    @ValueMapValue
    private String descriptionTab3;

    @ValueMapValue
    private String ctaLabelTab3;

    @ValueMapValue
    private String ctaLinkTab3;

    @ValueMapValue
    private boolean variantOne;

    @ValueMapValue
    private String dataPosition;

    @ValueMapValue
    private String bgImageAltTab1;

    @ValueMapValue
    private String bgImageAltTab2;

    @ValueMapValue
    private String bgImageAltTab3;

    @ValueMapValue
    private String imageTitleTab1;

    @ValueMapValue
    private String imageTitleTab2;

    @ValueMapValue
    private String imageTitleTab3;

    public String getTitle() {
        return title;
    }

    public String getTitleTab1() {
        return titleTab1;
    }

    public String getBgDesktopImgTab1() {
        return bgDesktopImgTab1;
    }

    public String getBgMobileImgTab1() {
        return bgMobileImgTab1;
    }

    public String getDescriptionTab1() {
        return descriptionTab1;
    }

    public String getCTALabelTab1() {
        return ctaLabelTab1;
    }

    public String getCTALinkTab1() {
        return ctaLinkTab1;
    }

    public String getTitleTab2() {
        return titleTab2;
    }

    public String getBgDesktopImgTab2() {
        return bgDesktopImgTab2;
    }

    public String getBgMobileImgTab2() {
        return bgMobileImgTab2;
    }

    public String getDescriptionTab2() {
        return descriptionTab2;
    }

    public String getCTALabelTab2() {
        return ctaLabelTab2;
    }

    public String getCTALinkTab2() {
        return ctaLinkTab2;
    }

    public String getTitleTab3() {
        return titleTab3;
    }

    public String getBgDesktopImgTab3() {
        return bgDesktopImgTab3;
    }

    public String getBgMobileImgTab3() {
        return bgMobileImgTab3;
    }

    public String getDescriptionTab3() {
        return descriptionTab3;
    }

    public String getCTALabelTab3() {
        return ctaLabelTab3;
    }

    public String getCTALinkTab3() {
        return ctaLinkTab3;
    }

    public boolean isVariantOne() {
        return variantOne;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getBgImageAltTab1() {
        return bgImageAltTab1;
    }

    public String getBgImageAltTab2() {
        return bgImageAltTab2;
    }

    public String getBgImageAltTab3() {
        return bgImageAltTab3;
    }

    public String getImageTitleTab1() {
        return imageTitleTab1;
    }

    public String getImageTitleTab2() {
        return imageTitleTab2;
    }

    public String getImageTitleTab3() {
        return imageTitleTab3;
    }

    /**
     * Creates a map for tab with relevant information.
     *
     * @param tabName      The name of the tab.
     * @param bgDesktopImg The background image for desktop device.
     * @param bgMobileImg  The background image for mobile device.
     * @param description  The description associated with the tab.
     * @param ctaLabel     The CTA label associated with the tab.
     * @param ctaLink      The CAT link associated with the CTA label.
     * @return A map containing tab details
     */
    public Map<String, String> getTabDetails(String tabName, String bgDesktopImg, String bgMobileImg,
            String description, String ctaLabel, String ctaLink, String imgAltText, String imageTitle) {
        // Create a map to store tab details
        Map<String, String> tabMap = new HashMap<>();

        // Add value to map only if it is not blank
        if (StringUtils.isNotBlank(tabName)) {
            tabMap.put(TAB_NAME, tabName);
        }
        if (StringUtils.isNotBlank(bgDesktopImg)) {
            tabMap.put(BACKGROUND_DESKTOP_IMAGE, CommonUtils.getDMImagePathLink(bgDesktopImg, resolver));
        }
        if (StringUtils.isNotBlank(bgMobileImg)) {
            tabMap.put(BACKGROUND_MOBILE_IMAGE, CommonUtils.getDMImagePathLink(bgMobileImg, resolver));
        }
        if (StringUtils.isNotBlank(description)) {
            tabMap.put(DESCRIPTION, description);
        }
        if (StringUtils.isNotBlank(ctaLabel)) {
            tabMap.put(CTA_LABEL, ctaLabel);
        }
        if (StringUtils.isNotBlank(ctaLink)) {
            tabMap.put(CTA_LINK, CommonUtils.getLinkWithHTML(ctaLink, resolver));
        }
        if (StringUtils.isNotBlank(imgAltText)) {
            tabMap.put(IMAGE_ALT_TEXT, imgAltText);
        }
        if (StringUtils.isNotBlank(imageTitle)) {
            tabMap.put(IMAGE_ALT_TITLE, imageTitle);
        }

        // Return the map containing details of tab
        return tabMap;
    }

    /**
     * Return JSON data for React to consume.
     *
     * @return JSON representation of the Three Tab With Background Card Component
     *         data
     * @throws IOException If an I/O error occurs
     */
    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("title", getTitle());
        jsonMap.put("isVariantOne", isVariantOne());
        jsonMap.put("tab1", getTabDetails(getTitleTab1(), getBgDesktopImgTab1(), getBgMobileImgTab1(),
                getDescriptionTab1(), getCTALabelTab1(), getCTALinkTab1(), getBgImageAltTab1(), getImageTitleTab1()));
        jsonMap.put("tab2", getTabDetails(getTitleTab2(), getBgDesktopImgTab2(), getBgMobileImgTab2(),
                getDescriptionTab2(), getCTALabelTab2(), getCTALinkTab2(), getBgImageAltTab2(), getImageTitleTab2()));
        jsonMap.put("tab3", getTabDetails(getTitleTab3(), getBgDesktopImgTab3(), getBgMobileImgTab3(),
                getDescriptionTab3(), getCTALabelTab3(), getCTALinkTab3(), getBgImageAltTab3(), getImageTitleTab3()));
        return CommonUtils.toJson(jsonMap);
    }
}
