package com.heromotocorp.vida.core.models;

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

@Model(adaptables = {
        Resource.class }, resourceType = "vida/components/vida-2.0/content/vidaappcard", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VidaAppCardModel {

    @ValueMapValue
    private String playStoreLink;

    @ValueMapValue
    private String appStoreLink;

    @ValueMapValue
    private String mobileDownloadImage;

    @ValueMapValue
    private String downloadText;

    @ValueMapValue
    private String cardBgImgDesktop;

    @ValueMapValue
    private String cardBgImgMob;

    @ValueMapValue
    private String stayConnectTitle;

    @ValueMapValue
    private String desktopDownloadImage;

    @ValueMapValue
    private String dataPosition;

    @ValueMapValue
    private String cardImageAlt;

    @ValueMapValue
    private String cardImageTitle;

    @ValueMapValue
    private String downloadImageAlt;

    @ValueMapValue
    private String downloadImageTitle;

    @SlingObject
    ResourceResolver resolver;

    public String getPlayStoreLink() {
        return playStoreLink;
    }

    public String getAppStoreLink() {
        return appStoreLink;
    }

    public String getMobileDownloadImage() {
        return mobileDownloadImage;
    }

    public String getDownloadText() {
        return downloadText;
    }

    public String getCardBgImgDesktop() {
        return cardBgImgDesktop;
    }

    public String getCardBgImgMob() {
        return cardBgImgMob;
    }

    public String getStayConnectTitle() {
        return stayConnectTitle;
    }

    public String getDesktopDownloadImage() {
        return desktopDownloadImage;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getCardImageAlt() {
        return cardImageAlt;
    }

    public String getCardImageTitle() {
        return cardImageTitle;
    }

    public String getDownloadImageAlt() {
        return downloadImageAlt;
    }

    public String getDownloadImageTitle() {
        return downloadImageTitle;
    }

    public Map<String, Object> getData(String imageUrl) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("imageUrl", CommonUtils.getDMImagePathLink(imageUrl,resolver));
        return dataMap;
    }

    /**
     * Return JSON data for react to consume
     * 
     * @return
     * @throws IOException
     */
    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("cardBgImgMob", CommonUtils.getDMImagePathLink(getCardBgImgMob(),resolver));
        jsonMap.put("cardBgImgDesktop", CommonUtils.getDMImagePathLink(getCardBgImgDesktop(),resolver));
        jsonMap.put("stayConnectTitle", getStayConnectTitle());
        jsonMap.put("downloadText", getDownloadText());
        jsonMap.put("mobileDownloadImage", getData(getMobileDownloadImage()));
        jsonMap.put("desktopDownloadImage", getData(getDesktopDownloadImage()));
        jsonMap.put("cardBgImgAlt", getCardImageAlt());
        jsonMap.put("cardBgImgTitle", getCardImageTitle());
        jsonMap.put("downloadImageAlt", getDownloadImageAlt());
        jsonMap.put("downloadImageTitle", getDownloadImageTitle());
        Map<String, String> linkMap = new HashMap<>();
        linkMap.put("playStoreLink", getPlayStoreLink());
        linkMap.put("appStoreLink", getAppStoreLink());
        jsonMap.put("downloadAppLinks", linkMap);
        return CommonUtils.toJson(jsonMap);
    }

}
