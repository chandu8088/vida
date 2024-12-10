package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is the main footer model
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ChargingGuideModel {
    /**
     * Taking the footer data in the below list.
     */

    @ChildResource
    private List<ChargingGuideBeanModel> ChargingGuideCardContent;

    @ValueMapValue
    private String header;


    private static final Logger LOG = LoggerFactory.getLogger(ChargingGuideModel.class);

    public List<ChargingGuideBeanModel> getChargingGuideCardContent() {
        return ChargingGuideCardContent != null ? new ArrayList<>(ChargingGuideCardContent) : Collections.emptyList();
    }

    public String getHeader() {
        return header;
    }

    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("ChargingGuideCardContent",getChargingGuideCardContent() );
        jsonMap.put("header",getHeader() );
        return CommonUtils.toJson(jsonMap);
    }

}
