package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.IOException;
import java.util.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FindChargingStationModel {

    @ChildResource
    private List<FindChargingStationBeanModel> chargingInfoContent;

    private static final Logger LOG = LoggerFactory.getLogger(FindChargingStationModel.class);

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<FindChargingStationBeanModel> getChargingInfoContent() {
        return chargingInfoContent != null ? new ArrayList<>(chargingInfoContent) : Collections.emptyList();
    }

    public String getJson() throws IOException {
        return CommonUtils.toJson(chargingInfoContent);
    }

}
