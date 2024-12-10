package com.heromotocorp.vida.core.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import java.util.*;


/**
 * The type Foooter bean model.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterBeanModel {

    @ValueMapValue
    private String header;

    @ValueMapValue
    private String image;

    @ValueMapValue
    private String imageAltText;

    @ValueMapValue
    private String link;

    @ValueMapValue
    private String content;

    @ChildResource
    private List<GenericDataModel>contentItem;


    @ValueMapValue
    private String title;

    @ValueMapValue
    private boolean newTab;

    @SlingObject
    private ResourceResolver resolver;

    /**
     * Gets heading.
     *
     * @return the heading
     */
    public String getHeader() {
        return header;
    }

    /**
     * Gets socialmediaurl.
     *
     * @return the socialmediaurl
     */
    public String getLink() {
        return link;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getImageAltText() {
        return imageAltText;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<GenericDataModel> getContentItem() {
        return contentItem != null ? new ArrayList<>(contentItem) : Collections.emptyList();
    }

    public boolean getNewTab() {
        return newTab;
    }
}