package com.heromotocorp.vida.core.vo;


public class SitemapBean implements Comparable<SitemapBean>{

    private String loc;
    private  String lastmod;
    private String changeFreq;
    private double priority;
    private boolean isActivated;
    private String robotsTags;
    private boolean disableSitemap;

    public SitemapBean(String loc, String lastmod, double priority, boolean isActivated, String robotsTags, boolean disableSitemap) {
        this.loc = loc;
        this.lastmod = lastmod;
        this.priority = priority;
        this.isActivated = isActivated;
        this.robotsTags = robotsTags;
        this.changeFreq = "daily";
        this.disableSitemap = disableSitemap;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getLastmod() {
        return lastmod;
    }

    public void setLastmod( String lastmod) {
        this.lastmod = lastmod;
    }

    public String getChangeFreq() {
        return changeFreq;
    }

    public void setChangeFreq(String changeFreq) {
        this.changeFreq = changeFreq;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean isActivated) {
        this.isActivated = isActivated;
    }

    public String getRobotsTags() {
        return robotsTags;
    }

    public void setRobotsTags(String robotsTags) {
        this.robotsTags = robotsTags;
    }


    public boolean isChildOf(SitemapBean sb){
        return sb.getLoc().contains(this.getLoc());
    }

    public boolean isDisableSitemap() {
        return disableSitemap;
    }

    public void setDisableSitemap(boolean disableSitemap) {
        this.disableSitemap = disableSitemap;
    }

    @Override
    public int compareTo(SitemapBean sb) {
        return this.getLoc().compareTo(sb.getLoc());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((loc == null) ? 0 : loc.hashCode());
        result = prime * result + ((lastmod == null) ? 0 : lastmod.hashCode());
        result = prime * result + ((changeFreq == null) ? 0 : changeFreq.hashCode());
        long temp;
        temp = Double.doubleToLongBits(priority);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (isActivated ? 1231 : 1237);
        result = prime * result + ((robotsTags == null) ? 0 : robotsTags.hashCode());
        result = prime * result + (disableSitemap ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SitemapBean other = (SitemapBean) obj;
        if (loc == null) {
            if (other.loc != null)
                return false;
        } else if (!loc.equals(other.loc))
            return false;
        if (lastmod == null) {
            if (other.lastmod != null)
                return false;
        } else if (!lastmod.equals(other.lastmod))
            return false;
        if (changeFreq == null) {
            if (other.changeFreq != null)
                return false;
        } else if (!changeFreq.equals(other.changeFreq))
            return false;
        if (Double.doubleToLongBits(priority) != Double.doubleToLongBits(other.priority))
            return false;
        if (isActivated != other.isActivated)
            return false;
        if (robotsTags == null) {
            if (other.robotsTags != null)
                return false;
        } else if (!robotsTags.equals(other.robotsTags))
            return false;
        if (disableSitemap != other.disableSitemap)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SitemapBean [loc=" + loc + ", lastmod=" + lastmod + ", changeFreq=" + changeFreq + ", priority="
                + priority + ", isActivated=" + isActivated + ", robotsTags=" + robotsTags + ", disableSitemap="
                + disableSitemap + "]";
    }
}

