package com.heromotocorp.vida.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.Date;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BlogCardsBeanModel {

    @Inject
    private String cardtitle;

    @Inject
    private String cardlink;

    @Inject
    private String cardimage;

    @Inject
    private String cardimagealttext;

    @Inject
    private String cardlinktext;

    @Inject
    private String carddesc;

    @Inject
    private String cardtimelinetext;

    @Inject
    private Date blogDate;


    public String getCardtitle() {
        return cardtitle;
    }

    public String getCardlink() {
        return cardlink;
    }

    public String getCardimage() {
        return cardimage;
    }

    public String getCardimagealttext() {
        return cardimagealttext;
    }

    public String getCardlinktext() {
        return cardlinktext;
    }

    public String getCarddesc() {
        return carddesc;
    }

    public String getCardtimelinetext() {
        return cardtimelinetext;
    }

    public Date getBlogDate() {
        if(blogDate != null){
            return new Date(blogDate.getTime());
        }
        return new Date();
    }
}
