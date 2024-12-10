package com.heromotocorp.vida.core.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;

/**
 * Class storing Generic Data.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = { Resource.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogsRedirectionBeansModel {

    @ValueMapValue
    private String blogCardDesktopImg;

    @ValueMapValue
    private String blogCardMobileImg;

    @ValueMapValue
    private boolean isVideo;

    @ValueMapValue
    private String blogCardVideo;

    @ValueMapValue
    private String blogCardDescription;

    @ValueMapValue
    private Date blogCardDate;

    @ValueMapValue
    private String cardNavLink;

    @ValueMapValue
    private boolean newTab;

    @ValueMapValue
    private String imageAlt;

    @ValueMapValue
    private String imageTitle;

    @SlingObject
    private ResourceResolver resolver;

   
    public String getBlogCardVideo() {
        return CommonUtils.getDMVideoPathLink(blogCardVideo, resolver);
    }

    public void setBlogCardVideo(final String blogCardVideo) {
        this.blogCardVideo = blogCardVideo;
    }
   
    public String getBlogCardDesktopImg() {
        return CommonUtils.getDMImagePathLink(blogCardDesktopImg, resolver);
    }

    public void setBlogCardDesktopImg(final String blogCardDesktopImg) {
        this.blogCardDesktopImg = blogCardDesktopImg;
    }
   
    public String getBlogCardMobileImg() {
        return CommonUtils.getDMImagePathLink(blogCardMobileImg, resolver);
    }

    public void setBlogCardMobileImg(final String blogCardMobileImg) {
        this.blogCardMobileImg = blogCardMobileImg;
    }
   
    public boolean getIsVideo() {
        return isVideo;
    }

    public void setIsVideo(final boolean isVideo) {
        this.isVideo = isVideo;
    }
   
    public boolean getNewTab() {
        return newTab;
    }

    public void setNewTab(final boolean newTab) {
        this.newTab = newTab;
    }
    
    public String getBlogCardDescription() {
        return blogCardDescription;
    }

    public void setBlogCardDescription(final String blogCardDescription) {
        this.blogCardDescription = blogCardDescription;
    }
    
    public String getCardNavLink() {
        return CommonUtils.getLinkWithHTML(cardNavLink, resolver);
    }
    
    public void setCardNavLink(final String cardNavLink) {
        this.cardNavLink = cardNavLink;
    }

    public String getBlogCardDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        String formattedDate = formatter.format(blogCardDate); 
        return formattedDate.toUpperCase();
    }
    
    public void setBlogCardDate(final Date blogCardDate) {
        if (blogCardDate != null) {
            this.blogCardDate = new Date(blogCardDate.getTime());
        } else {
            this.blogCardDate = new Date();
        }
    }

    public String getImageAlt() {
        return imageAlt;
    }

    public void setImageAlt(final String imageAlt) {
        this.imageAlt = imageAlt;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public void setImageTitle(final String imageTitle) {
        this.imageTitle = imageTitle;
    }
}
