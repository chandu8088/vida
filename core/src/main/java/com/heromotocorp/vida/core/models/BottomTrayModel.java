package com.heromotocorp.vida.core.models;


import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.io.IOException;
import java.util.*;

/**
 * This is the main BottomTray model
 */
@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BottomTrayModel {
    /**
     * Taking the BottomTrayModel data in the below list.
     */

    @ChildResource
    private List<BottomTrayBeanModel> trayItems;

    @ValueMapValue
    private String variation;

    @ValueMapValue
    private String bottomTrayBgImg;

    public List<BottomTrayBeanModel> getTrayItems() {
        return trayItems!= null ? new ArrayList<>(trayItems) : Collections.emptyList();
    }


    public String getVariation() {
        return variation;
    }

    public String getBottomTrayBgImg() {
        return bottomTrayBgImg;
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("bottomTrayCardContent",getTrayItems() );
        jsonMap.put("bottomTrayBgImg",getBottomTrayBgImg() );
        jsonMap.put("isVariant1",getVariation().equals("variationOne")?true:false );
        return CommonUtils.toJson(jsonMap);
    }
}
