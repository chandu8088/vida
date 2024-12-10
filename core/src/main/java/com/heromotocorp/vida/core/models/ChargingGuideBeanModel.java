package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ChargingGuideBeanModel {

    @ValueMapValue
    private String mobileImg;

    @ValueMapValue
    private String desktopImg;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String description;

    @ValueMapValue
    private String offerLabel;

    @ValueMapValue
    private String imagealttext;

    @ValueMapValue
    private String imageTitle;

    @SlingObject
    private ResourceResolver resolver;


    private static final Logger LOG = LoggerFactory.getLogger(ChargingGuideBeanModel.class);

    public String getMobileImg() {
        return CommonUtils.getDMImagePathLink(mobileImg,resolver);
    }


    public String getDesktopImg() {
        return CommonUtils.getDMImagePathLink(desktopImg,resolver);
    }


    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }


    public String getOfferLabel() {
        return offerLabel;
    }

    public String getImagealttext() {
        return imagealttext;
    }

    public String getImageTitle() {
        return imageTitle;
    }
}
