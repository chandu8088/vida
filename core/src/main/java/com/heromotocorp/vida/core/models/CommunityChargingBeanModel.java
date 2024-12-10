package com.heromotocorp.vida.core.models;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.utils.CommonUtils;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CommunityChargingBeanModel {

    @SlingObject
    private ResourceResolver resolver;

    @ValueMapValue
    private String icon;

    @ValueMapValue
    private String order;

    @ValueMapValue
    private String labeltext;

    @ValueMapValue
    private  String labelvalue;

    @ValueMapValue
    private String iconAlt;

    @ValueMapValue
    private String cardImage;

    @ValueMapValue
    private String cardImageAlt;

    @OSGiService
    private GlobalConfig globalConfig;

    boolean enableDMFeature;

    @PostConstruct
    public void init() {
        enableDMFeature = globalConfig.enableDMFeature();
    }

    public String getLabeltext(){
        return  labeltext;
    }
    public String getLabelvalue(){
        return labelvalue;
    }

    public String getOrder() {
        return order;
    }

    public String getIcon() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(icon, resolver) : icon;
    }

    public String getIconAlt() {
        return iconAlt;
    }

    public String getCardImage() {
        return enableDMFeature ? CommonUtils.getDMImagePathLink(cardImage, resolver) : cardImage;
    }

    public String getCardImageAlt() {
        return cardImageAlt;
    }
}