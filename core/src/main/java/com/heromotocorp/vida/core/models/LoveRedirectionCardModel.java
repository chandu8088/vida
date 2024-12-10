package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.io.IOException;
import java.util.*;

@Model(adaptables = Resource.class,
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LoveRedirectionCardModel {

    @ChildResource
    private List<LoveRedirectionCardBeanModel> redirectionCardList;

    @ValueMapValue
    private String dataPosition;

    public List<LoveRedirectionCardBeanModel> getRedirectionCardList() {
        return Collections.unmodifiableList(redirectionCardList);
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getJsonString() throws IOException {
        List<Object> jsonArrray = new ArrayList<>();
        for (LoveRedirectionCardBeanModel loveRedirectionCardBeanModel : redirectionCardList) {
            Map<String, Object> json = new HashMap<>();
            Map<String, Object> redirectionContentMap = getStringObjectMap(loveRedirectionCardBeanModel);
            json.put("reDirectionCardContents", redirectionContentMap);
            json.put("cardTextColor", loveRedirectionCardBeanModel.getCardTextColor());
            json.put("cardImg", loveRedirectionCardBeanModel.getCardImg());
            json.put("cardType", loveRedirectionCardBeanModel.getCardType());
            json.put("cardBgImgMobile", loveRedirectionCardBeanModel.getCardBgImgMobile());
            json.put("cardBgColor", loveRedirectionCardBeanModel.getCardBgColor());
            json.put("cardBgImgDesktop", loveRedirectionCardBeanModel.getCardBgImgDesktop());
            json.put("cardImgText", loveRedirectionCardBeanModel.getCardImgText());
            json.put("dataPosition", getDataPosition());
            jsonArrray.add(json);
        }
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(jsonArrray);
    }

    private Map<String, Object> getStringObjectMap(LoveRedirectionCardBeanModel loveRedirectionCardBeanModel) {
        Map<String, String> redirectMap = new HashMap<>();
        Map<String, Object> redirectionContentMap = new HashMap<>();
        redirectMap.put("imageUrl", loveRedirectionCardBeanModel.getRedirectImagePath());
        redirectMap.put("newTab", loveRedirectionCardBeanModel.getNewTab());
        redirectMap.put("redirectLink", loveRedirectionCardBeanModel.getRedirectionPath());
        redirectionContentMap.put("reDirect", redirectMap);
        redirectionContentMap.put("cardText", loveRedirectionCardBeanModel.getCardText());
        return redirectionContentMap;
    }
}
