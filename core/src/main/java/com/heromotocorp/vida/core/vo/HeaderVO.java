package com.heromotocorp.vida.core.vo;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class HeaderVO.
 */
public class HeaderVO {

    /** The link text. */
    private String name = StringUtils.EMPTY;

    /** The link URL. */
    private String navLink = StringUtils.EMPTY;

    private Boolean newTab = Boolean.FALSE;

    public Boolean isNewTab() {
        return newTab;
    }

    public void setNewTab(Boolean newTab) {
        this.newTab = newTab;
    }

    /**
     * Gets the link text.
     *
     * @return the link text
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the link text.
     *
     * @param linkText the new link text
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the link URL.
     *
     * @return the link URL
     */
    public String getNavLink() {
        return navLink;
    }

    /**
     * Sets the link URL.
     *
     * @param linkUrl the new link URL
     */
    public void setNavLink(String navLink) {
        this.navLink = navLink;
    }
}
