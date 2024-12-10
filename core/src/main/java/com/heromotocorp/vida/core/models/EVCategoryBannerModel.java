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

/**
 * Sling Model class for the EV category banner content.
 */
@Model(adaptables = { Resource.class }, resourceType = "vida/components/vida-2.0/content/evcategorybanner",
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EVCategoryBannerModel {

    @SlingObject
    private ResourceResolver resolver;

    @ValueMapValue
    private String imageDesk;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String variations;

    @ValueMapValue
    private String imageAlt;

    @ValueMapValue
    private String imageMob;

    @ValueMapValue
    private String videoDesk;

    @ValueMapValue
    private String videoMob;

    @ValueMapValue
    private String imageTitle;

    @ValueMapValue
    private String howVidaLink;


    /**
     * Gets the image path associated with the banner.
     * 
     * @return The image path.
     */
    public String getImageDesktop() {
        return imageDesk;
    }

    /**
     * Gets the title of the banner.
     * 
     * @return The banner title.
     */
    public String getTitle() {
        return title;
    }

    public String getVariations() {
        return variations;
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public String getImageMobile() {
        return imageMob;
    }

    public String getVideoDesktop() {
        return videoDesk;
    }

    public String getVideoMobile() {
        return videoMob;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getHowVidaLink() {
        return CommonUtils.getLinkWithHTML(howVidaLink,resolver);
    }

    /**
     * Returns JSON data for consumption by React.
     * 
     * @return The JSON data representing the banner content.
     * @throws IOException If an I/O error occurs.
     */
    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("title", getTitle());
        jsonMap.put("assetType", getVariations());
        if(getVariations().equals("image")){
            Map<String, Object> imgMap = new HashMap<>();
            imgMap.put("imageDesktop",CommonUtils.getDMImagePathLink(getImageDesktop(), resolver));
            imgMap.put("imageMobile",CommonUtils.getDMImagePathLink(getImageMobile(), resolver));
            imgMap.put("altText", getImageAlt());
            imgMap.put("imageTitle", getImageTitle());
            jsonMap.put("image", imgMap);
        }
        else{
            Map<String, Object> videoMap = new HashMap<>();
            videoMap.put("videoDesktop", CommonUtils.getDMVideoPathLink(getVideoDesktop(), resolver));
            videoMap.put("videoMobile", CommonUtils.getDMVideoPathLink(getVideoMobile(), resolver));
            jsonMap.put("video", videoMap);
        }
        return CommonUtils.toJson(jsonMap);
    }

}
