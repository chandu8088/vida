package com.heromotocorp.vida.core.models;

import com.adobe.xfa.ut.StringUtils;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsCardBeanModel {

    @SlingObject
    private ResourceResolver resolver;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String imagePath;

    @ValueMapValue
    private String imageAltText;

    @ValueMapValue
    private String imageTitleText;

    @ValueMapValue
    private String loadMoreLabel;

    @ValueMapValue
    private String newsRedirection;

    @ValueMapValue
    private boolean newTab;

    public boolean isNewTab() {
        return newTab;
    }

    public String getTitle() {
        return title;
    }

    public String getImagePath() {
        return CommonUtils.getDMImagePathLink(imagePath, resolver);
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public String getImageTitleText() {
        return imageTitleText;
    }

    public String getLoadMoreLabel() {
        return loadMoreLabel;
    }

    public String getNewsRedirection() {
        if (!StringUtils.isEmpty(newsRedirection)) {
            return resolver.map(newsRedirection);
        }
        return null;
    }
}
