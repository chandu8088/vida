package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.heromotocorp.vida.core.utils.CommonUtils;

/**
 * Sling Model for Redirection Card component.
 */
@Model(adaptables = {
        Resource.class }, resourceType = "vida/components/vida-2.0/content/redirectioncard", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RedirectionCardModel {

    @ChildResource
    public List<Resource> imageCard;
    @ValueMapValue
    private String cardTitle;

    @ValueMapValue
    private String cardImageText;

    @ValueMapValue
    private String redirectImg;

    @ValueMapValue
    private String cardText;

    @ValueMapValue
    private String redirectLink;

    @ValueMapValue
    private String cardType;

    @ValueMapValue
    private boolean newtab;

    @ValueMapValue
    private String bgImgDesk;

    @ValueMapValue
    private String bgImgMob;

     @ValueMapValue
    private String cardBgColor;

     @ValueMapValue
    private String cardTextColor;

     @ValueMapValue
    private String dataPosition;

    @ValueMapValue
    private String fontSize;

    @ValueMapValue
    private String fontWeight ;

    @ValueMapValue
    private String imageAlt;

    @ValueMapValue
    private String imageTitle ;

    @ValueMapValue
    private String secondImageUrl;

    @SlingObject
    ResourceResolver resolver;

    /**
     * Get the list of image URLs from the provided list of resources.
     *
     * @param image    List of image resources
     * @param property Property to fetch from each resource
     * @return List of image URLs
     */
    public List<String> getImageList(List<Resource> image, String property) {
        List<String> imageList = new ArrayList<>();

        if (image != null) {
            for (Resource item : image) {
                if (item != null) {
                    String linkText = item.getValueMap().get(property, String.class);
                    imageList.add(CommonUtils.getDMImagePathLink(linkText, resolver));
                }
            }
        }
        return !imageList.isEmpty() ? new ArrayList<>(imageList) : Collections.emptyList();
    }

    public String getRedirectImage() {
        return redirectImg;
    }

    public String getSecondImageUrl() {
        return secondImageUrl;
    }
    public String getCardTitle(){
        return cardTitle;
    }

    public String getCardText() {
        return cardText;
    }

    public String getCardImageText() {
        return cardImageText;
    }

    public String getRedirectLink() {
        return redirectLink;
    }

    public String getCardType() {
        return cardType;
    }

    public String getBackgroundImgDesk() {
        return bgImgDesk;
    }

    public String getBackgroundImgMob() {
        return bgImgMob;
    }
    
    public boolean isNewTab() {
        return newtab;
    }

    public String getCardBgColor() {
        return cardBgColor;
    }

    public String getCardTextColor() {
        return cardTextColor;
    }

    public String getFontSize() {
        return fontSize;
    }

    public String getFontWeight() {
        return fontWeight;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getJson() throws IOException {
        return CommonUtils.toJson(getComponentMap());
    }
    /**
     * Return JSON data for React to consume.
     *
     * @return JSON representation of the Redirection Card data
     * @throws IOException If an I/O error occurs
     */
    public Map<String, Object> getComponentMap() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("title",getCardTitle());
        jsonMap.put("cardImgText", getCardImageText());
        jsonMap.put("cardType", getCardType());
        jsonMap.put("cardImg", getImageList(imageCard, "cardImg"));
        jsonMap.put("cardBgImgDesktop", CommonUtils.getDMImagePathLink(getBackgroundImgDesk(), resolver));
        jsonMap.put("cardBgImgMobile", CommonUtils.getDMImagePathLink(getBackgroundImgMob(), resolver));
        jsonMap.put("cardBgColor", getCardBgColor());
        jsonMap.put("cardTextColor", getCardTextColor());
        jsonMap.put("fontWeight ", getFontWeight());
        jsonMap.put("fontSize ", getFontSize());
        jsonMap.put("imageAlt", getImageAlt());
        jsonMap.put("imageTitle", getImageTitle());
        Map<String, Object> cardComponent = new HashMap<>();
        cardComponent.put("cardText", getCardText());
        cardComponent.put("cardTitle", getCardTitle());
        Map<String, Object> redirect = new HashMap<>();
        redirect.put("imageUrl", CommonUtils.getDMImagePathLink(getRedirectImage(), resolver));
        redirect.put("secondImageUrl", CommonUtils.getDMImagePathLink(getSecondImageUrl(), resolver));
        redirect.put("redirectLink", CommonUtils.getLinkWithHTML(getRedirectLink(), resolver));
        redirect.put("newTab", isNewTab());
        cardComponent.put("reDirect", redirect);
        jsonMap.put("reDirectionCardContents", cardComponent);
        return jsonMap;
    }
}
