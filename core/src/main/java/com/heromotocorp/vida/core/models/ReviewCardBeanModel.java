package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ReviewCardBeanModel {

    @SlingObject
    private ResourceResolver resolver;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String rating;

    @ValueMapValue
    private String reviewMessage;

    @ValueMapValue
    private String imagePath;

    @ValueMapValue
    private String userName;

    @ValueMapValue
    private String userImage;

    @ValueMapValue
    private String userCity;

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public String getImagePath() {
        return CommonUtils.getDMImagePathLink(imagePath,resolver);
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImage() {
        return CommonUtils.getDMImagePathLink(userImage,resolver);
    }

    public String getUserCity() {
        return userCity;
    }
}
