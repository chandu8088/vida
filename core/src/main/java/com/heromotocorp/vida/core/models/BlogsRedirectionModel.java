package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import com.heromotocorp.vida.core.utils.CommonUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sling Model for BlogsRedirection Card component.
 */
@Model(adaptables = {
        Resource.class }, resourceType = "vida/components/vida-2.0/content/blogsredirection", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogsRedirectionModel {

    @ChildResource
    public List<BlogsRedirectionBeansModel> blogCardContent;

    @ValueMapValue
    private String blogTitle;

    @ValueMapValue
    private String redirectIcon;

    @ValueMapValue
    private String redirectLink;

    @ValueMapValue
    private boolean redirectNewTab;

    @ValueMapValue
    private String dataPosition;

    @SlingObject
    ResourceResolver resolver;

    public List<BlogsRedirectionBeansModel> getBlogCardContent() {
        return blogCardContent != null ? new ArrayList<>(blogCardContent) : Collections.emptyList();
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public String getRedirectIcon() {
        return redirectIcon;
    }

    public String getRedirectLink() {
        return redirectLink;
    }

    public boolean getRedirectNewTab() {
        return redirectNewTab;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    /**
     * Return JSON data for React to consume.
     *
     * @return JSON representation of the BlogsRedirection Card data
     * @throws IOException If an I/O error occurs
     */
    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("blogTitle", getBlogTitle());
        jsonMap.put("reDirectionIcon", CommonUtils.getDMImagePathLink(getRedirectIcon(), resolver));
        jsonMap.put("reDirectNavLink", CommonUtils.getLinkWithHTML(getRedirectLink(), resolver));
        jsonMap.put("reDirectNewTab", getRedirectNewTab());
        jsonMap.put("blogCardContent", getBlogCardContent());

        return CommonUtils.toJson(jsonMap);
    }
}
