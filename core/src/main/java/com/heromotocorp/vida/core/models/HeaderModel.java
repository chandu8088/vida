package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.heromotocorp.vida.core.vo.HeaderVO;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sling Model class representing the header component in the Vida application.
 * This model is responsible for adapting the Resource to a structured
 * HeaderModel,
 * extracting information to render the header section of the website.
 */
@Model(adaptables = {
        Resource.class }, resourceType = "vida/components/vida-2.0/structure/header", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {

    private static final String TRY_TITLE = "tryTitle";

    private static final String BUY_TITLE = "buyTitle";

    private static final String EXPLORE_TITLE = "exploreTitle";

    private static final String LOVE_TITLE = "loveTitle";

    private static final String LINK_TEXT = "name";

    private static final String NAV_LINK = "navLink";

    @Self
    private Resource resource;

    @ChildResource
    private List<GenericDataModel> addLabels;

    @ChildResource
    private List<GenericDataModel> secondarynavigation;

    @ChildResource
    public List<Resource> tryOptionContent;

    @ChildResource
    public List<Resource> buyOptionContent;

    @ChildResource
    public List<Resource> exploreOptionContent;

    @ChildResource
    public List<Resource> loveOptionContent;

    /** The resolver. */
    @SlingObject
    private ResourceResolver resolver;

    @ChildResource
    private List<GenericDataModel> hamburgerBgImg;

    @ChildResource
    private List<GenericDataModel> hamburgerBg1Img;

    @ValueMapValue
    private String headerlogo;

    @ValueMapValue
    private String alttext;

    @ValueMapValue
    private String mobileheaderlogo;

    @ValueMapValue
    private String logourl;

    @ValueMapValue
    private boolean isLogoNewTab;

    @ValueMapValue
    private String headerHeroLogo;

    @ValueMapValue
    private String heroLogoNavLink;

    @ValueMapValue
    private String headerHeroLogoAlt;

    @ValueMapValue
    private String headerHeroLogoTitle;

    @ValueMapValue
    private boolean headerHeroLogoNewTab;

    @ValueMapValue
    private String logoutlabel;

    @ValueMapValue
    private String myAccountText;

    @ValueMapValue
    private String preLoginText;

    @ValueMapValue
    private String profileLink;

    @ValueMapValue
    private String preLoginNavLink;

    @ValueMapValue
    private String signUpText;

    @ValueMapValue
    private String signUpNavLink;

    @ValueMapValue
    private String dataPosition;

    @ValueMapValue
    private String headerVariation;

    @ValueMapValue
    private String vidaHeaderTitle;

    @ValueMapValue
    private String vidaHeaderBackIcon;

    @ValueMapValue
    private String vidaHeaderBackNavLink;

    @ValueMapValue
    private boolean headerBacknNewTab;

    @ValueMapValue
    private String vidaCustomerCareIcon;

    @ValueMapValue
    private String vidaCustomerCareNavLink;

    @ValueMapValue
    private boolean customerCareNewTab;

    @ValueMapValue
    private String logoTitle;

    @OSGiService
    private GlobalConfig globalConfig;

    /**
     * Retrieves the primary labels to display within the header.
     *
     * @return A list of GenericDataModel instances containing primary labels.
     */
    public List<GenericDataModel> getPrimaryLabels() {
        return addLabels != null ? new ArrayList<>(addLabels) : Collections.emptyList();
    }

    /**
     * Retrieves the secondary navigation items for the header.
     *
     * @return A list of GenericDataModel instances representing secondary
     *         navigation items.
     */
    public List<GenericDataModel> getSecondaryNavigation() {
        return secondarynavigation != null ? new ArrayList<>(secondarynavigation) : Collections.emptyList();
    }

    /**
     * Retrieves the structured representation of primary navigation items with
     * child paths.
     *
     * @param titleKey   The key used to fetch the title from the resource.
     * @param resources  The list of resources representing primary navigation items.
     * @return A list of GenericDataModel instances representing primary navigation
     *         items with child paths.
     */
    public GenericDataModel getPrimaryNavigation(String titleKey, List<Resource> resources) {
        GenericDataModel genericDataModel = new GenericDataModel();

        String title = resource.getValueMap().get(titleKey, String.class);
        genericDataModel.setTitle(title);

        List<HeaderVO> primaryNavList = new ArrayList<>();

        for (Resource menuItem : resources) {
            if (menuItem != null) {
                // Extracting label and link for each menu item
                String linkText = menuItem.getValueMap().get(LINK_TEXT, String.class);
                String linkUrl = menuItem.getValueMap().get(NAV_LINK, String.class);
                Boolean isNewTab = menuItem.getValueMap().get("newTab", Boolean.class);
                HeaderVO headerVO = new HeaderVO();
                headerVO.setName(linkText);
                headerVO.setNavLink(CommonUtils.getLinkWithHTML(linkUrl, resolver));
                headerVO.setNewTab(isNewTab);
                primaryNavList.add(headerVO);
            }
        }
        genericDataModel.setSitesOption(primaryNavList);
        return genericDataModel;
    }

    public List<GenericDataModel> getHamburgerBgImg() {
        return hamburgerBgImg != null ? new ArrayList<>(hamburgerBgImg) : Collections.emptyList();
    }

    public List<GenericDataModel> getHamburgerBg1Img() {
        return hamburgerBg1Img != null ? new ArrayList<>(hamburgerBg1Img) : Collections.emptyList();
    }

    public String getHeaderlogo() {
        return headerlogo;
    }

    public String getHeaderHeroLogo() {
        return headerHeroLogo;
    }

    public String getHeroLogoNavLink() {
        return heroLogoNavLink;
    }

    public String getHeaderHeroLogoAlt() {
        return headerHeroLogoAlt;
    }

    public String getHeaderHeroLogoTitle() {
        return headerHeroLogoTitle;
    }

    public boolean isHeaderHeroLogoNewTab() {
        return headerHeroLogoNewTab;
    }

    public String getAlttext() {
        return alttext;
    }

    public String getMobileheaderlogo() {
        return mobileheaderlogo;
    }

    public String getLogoutlabel() {
        return logoutlabel;
    }

    public String getMyAccountText() {
        return myAccountText;
    }

    public String getPreLoginText() {
        return preLoginText;
    }

    public String getProfileLink() {
        return profileLink;
    }

    public String getPreLoginNavLink() {
        return preLoginNavLink;
    }

    public String getSignUpText() {
        return signUpText;
    }

    public String getSignUpNavLink() {
        return signUpNavLink;
    }

    public boolean isLogoNewTab() {
        return isLogoNewTab;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getLogoTitle() {
        return logoTitle;
    }
    
    /**
     * Retrieves the first background image from the given list.
     *
     * @param imageList The list of background images.
     * @return The first GenericDataModel from the list, or an empty model if the list is null or empty.
     */
    private GenericDataModel getFirstHamburgerBgImage(List<GenericDataModel> imageList) {
        if (imageList != null && !imageList.isEmpty()) {
            return imageList.get(0);
        }
        return new GenericDataModel();
    }

    public String getLogourl() {
        return logourl;
    }

    public String getHeaderVariation() {
        return headerVariation;
    }

    public String getVidaHeaderTitle() {
        return vidaHeaderTitle;
    }

    public String getVidaHeaderBackIcon() {
        return globalConfig.enableDMFeature() ? CommonUtils.getDMImagePathLink(vidaHeaderBackIcon, resolver)
                : vidaHeaderBackIcon;
    }

    public String getVidaHeaderBackNavLink() {
        return CommonUtils.getLinkWithHTML(vidaHeaderBackNavLink,resolver);
    }

    public String getVidaCustomerCareIcon() {
        return globalConfig.enableDMFeature() ? CommonUtils.getDMImagePathLink(vidaCustomerCareIcon, resolver)
                : vidaCustomerCareIcon;
    }

    public String getVidaCustomerCareNavLink() {
        return CommonUtils.getLinkWithHTML(vidaCustomerCareNavLink,resolver);
    }

    public boolean isHeaderBacknNewTab() {
        return headerBacknNewTab;
    }

    public boolean isCustomerCareNewTab() {
        return customerCareNewTab;
    }


    /**
     * Retrieves content related to the user's account.
     *
     */
    public Map<String, Object> getMyAccountContent() {
        Map<String, Object> myAccountContent = new HashMap<>();
        myAccountContent.put("myAccountText", getMyAccountText());
        myAccountContent.put("preLoginText", getPreLoginText());
        myAccountContent.put("profileLink", CommonUtils.getLinkWithHTML(getProfileLink(), resolver));
        myAccountContent.put("preLoginNavLink", CommonUtils.getLinkWithHTML(getPreLoginNavLink(), resolver));
        myAccountContent.put("signUpText", getSignUpText());
        myAccountContent.put("signUpNavLink", CommonUtils.getLinkWithHTML(getSignUpNavLink(), resolver));
        return myAccountContent;
    }

    /**
     * Return JSON data for react to consume
     * 
     * @return JSON representation of header data.
     * @throws IOException If an I/O error occurs during JSON generation.
     */
    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        if (tryOptionContent != null) {
            jsonMap.put("tryOptionContent", getPrimaryNavigation(TRY_TITLE, tryOptionContent));
        }
        if (buyOptionContent != null) {
            jsonMap.put("buyOptionContent", getPrimaryNavigation(BUY_TITLE, buyOptionContent));
        }
        if (exploreOptionContent != null) {
            jsonMap.put("exploreOptionContent", getPrimaryNavigation(EXPLORE_TITLE, exploreOptionContent));
        }
        if (loveOptionContent != null) {
            jsonMap.put("loveOptionContent", getPrimaryNavigation(LOVE_TITLE, loveOptionContent));
        }
        jsonMap.put("navbarOptions", getPrimaryLabels());
        jsonMap.put("termsAndPrivacyText", getSecondaryNavigation());
        jsonMap.put("hamburgerBgImg", getFirstHamburgerBgImage(getHamburgerBgImg()));
        jsonMap.put("hamburgerBg1Img", getFirstHamburgerBgImage(getHamburgerBg1Img()));
        jsonMap.put("headerVidaLogo",
                globalConfig.enableDMFeature() ? CommonUtils.getDMImagePathLink(getHeaderlogo(), resolver)
                        : getHeaderlogo());
        jsonMap.put("vidaLogoNavLink", CommonUtils.getLinkWithHTML(getLogourl(), resolver));
        jsonMap.put("hamburgerVidaLogo",
                globalConfig.enableDMFeature() ? CommonUtils.getDMImagePathLink(getMobileheaderlogo(), resolver)
                        : getMobileheaderlogo());
        jsonMap.put("isLogoNewTab", isLogoNewTab());
        jsonMap.put("headerHeroLogo",
                globalConfig.enableDMFeature() ? CommonUtils.getDMImagePathLink(getHeaderHeroLogo(),resolver)
                        : getHeaderHeroLogo());
        jsonMap.put("heroLogoNavLink",getHeroLogoNavLink());
        jsonMap.put("headerHeroLogoAlt",getHeaderHeroLogoAlt());
        jsonMap.put("headerHeroLogoTitle",getHeaderHeroLogoTitle());
        jsonMap.put("headerLogoNewTab",isHeaderHeroLogoNewTab());
        jsonMap.put("logoutlabel", getLogoutlabel());
        jsonMap.put("myAccountContent", getMyAccountContent());
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("headerVariation", getHeaderVariation());
        jsonMap.put("vidaHeaderTitle", getVidaHeaderTitle());
        jsonMap.put("vidaHeaderBackIcon", getVidaHeaderBackIcon());
        jsonMap.put("vidaHeaderBackNavLink", getVidaHeaderBackNavLink());
        jsonMap.put("headerBacknNewTab", isHeaderBacknNewTab());
        jsonMap.put("vidaCustomerCareIcon", getVidaCustomerCareIcon());
        jsonMap.put("vidaCustomerCareNavLink", getVidaCustomerCareNavLink());
        jsonMap.put("customerCareNewTab", isCustomerCareNewTab());
        jsonMap.put("headerLogoAlt", getAlttext());
        jsonMap.put("headerLogoTitle", getLogoTitle());
        return CommonUtils.toJson(jsonMap);
    }

}
