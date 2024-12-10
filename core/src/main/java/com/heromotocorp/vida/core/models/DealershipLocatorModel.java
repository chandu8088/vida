package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

/**
 * This is the main footer model
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class DealershipLocatorModel {
    /**
     * Taking the footer data in the below list.
     */



    @ValueMapValue
    private String dealershipPrimaryText;

    @ValueMapValue
    private String dealershipSecondaryText;
    @ValueMapValue
    private String dealershipCardIcon;

    @ValueMapValue
    private String dealershipCardPrimaryText;

    @ValueMapValue
    private String dealershipCardSecondaryText;

    @ValueMapValue
    private String dealershipCardBtnText;

    @ValueMapValue
    private String dealershipBtnNavLink;

    @ValueMapValue
    private boolean newTab;

    @ValueMapValue
    private String dataPosition;

    @SlingObject
    private ResourceResolver resolver;

    private static final Logger LOG = LoggerFactory.getLogger(DealershipLocatorModel.class);

    public String getDealershipPrimaryText() {
        return dealershipPrimaryText;
    }

    public String getDealershipSecondaryText() {
        return dealershipSecondaryText;
    }

    public String getDealershipCardIcon() {
        return CommonUtils.getLinkWithHTML(dealershipCardIcon,resolver);
    }

    public String getDealershipCardPrimaryText() {
        return dealershipCardPrimaryText;
    }

    public String getDealershipCardSecondaryText() {
        return dealershipCardSecondaryText;
    }

    public String getDealershipCardBtnText() {
        return dealershipCardBtnText;
    }

    public String getDealershipBtnNavLink() {
        return CommonUtils.getLinkWithHTML(dealershipBtnNavLink,resolver);
    }

    public boolean isNewTab() {
        return newTab;
    }

    public String getDataPosition() {
        return dataPosition;
    }



    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("dealershipPrimaryText",getDealershipPrimaryText() );
        jsonMap.put("dealershipSecondaryText",getDealershipSecondaryText() );
        jsonMap.put("dealershipCardIcon",getDealershipCardIcon() );
        jsonMap.put("dealershipCardPrimaryText",getDealershipCardPrimaryText() );
        jsonMap.put("dealershipCardSecondaryText",getDealershipCardSecondaryText() );
        jsonMap.put("dealershipCardBtnText",getDealershipCardBtnText() );
        jsonMap.put("dealershipBtnNavLink",getDealershipBtnNavLink() );
        jsonMap.put("newTab",isNewTab() );


        return CommonUtils.toJson(jsonMap);
    }

}
