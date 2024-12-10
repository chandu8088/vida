package com.heromotocorp.vida.core.models;

import java.util.ArrayList;
import java.util.Collections;

import com.heromotocorp.vida.core.service.ProductVariantPriceJsonService;
import com.heromotocorp.vida.core.utils.CommonUtils;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;

@Model(adaptables = {
        Resource.class }, resourceType = "vida/components/vida-2.0/content/variantswitcher", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class VariantSwitcherModel {

    private static final Logger log = LoggerFactory.getLogger(VariantSwitcherModel.class);

    /** The resource resolver. */
    @SlingObject
    private ResourceResolver resourceResolver;

    @ValueMapValue
    private String preTitle;

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String titleTag;

    @ValueMapValue
    private String preTitleTag;

    @ValueMapValue
    private String buttonLabel;

    @ValueMapValue
    private String defaultCity;

    @ValueMapValue
    private String removableBatteryTitle;

    @ValueMapValue
    private String chargingTimeTitle;

    @ValueMapValue
    private String rangeTitle;

    @ValueMapValue
    private String topSpeedTitle;

    @ValueMapValue
    private String buttonLabelLink;

    @ValueMapValue
    private boolean buttonNewtab;

    @ValueMapValue
    private String chargingTimeDesktopIcon;

    @ValueMapValue
    private boolean enableFastChargingTime;

    @ValueMapValue
    private String batteryDesktopIcon;

    @ValueMapValue
    private String batteryMobileIcon;

    @ValueMapValue
    private String chargingTimeMobileIcon;

    @ValueMapValue
    private String rangeDesktopIcon;

    @ValueMapValue
    private String rangeMobileIcon;

    @ValueMapValue
    private String topSpeedDesktopIcon;

    @ValueMapValue
    private String topSpeedMobileIcon;

    @ValueMapValue
    private String acceleratorDesktopIcon;

    @ValueMapValue
    private String acceleratorMobileIcon;

    @ValueMapValue
    private String exShowroomLabel;

    @ValueMapValue
    private String preRemovableBatteryTitle;

    @ValueMapValue
    private String dataPosition;

    @ValueMapValue
    private String certifiedRange;

    @ValueMapValue
    private boolean enableCertifiedRange;

    @ValueMapValue
    private String acceleratorTitle;

    @ValueMapValue
    private String batteryQuantity;

    @ValueMapValue
    private String removableBattery;

    @ChildResource
    private List<VariantSwitcherBeanModel> productInfo;

    @ChildResource
    private List<LearnMoreRedirectionBeanModel> learnMoreRedirectionList;

    @Inject
    ProductVariantPriceJsonService productVariantPriceJsonService;

    public String getPreTitle() {
        return preTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleTag() {
        return titleTag;
    }

    public String getPreTitleTag() {
        return preTitleTag;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public String getCertifiedRange() {
        return certifiedRange;
    }

    public boolean getEnableCertifiedRange() {
        return enableCertifiedRange;
    }

    public String getBatteryQuantity() {
        return batteryQuantity;
    }

    public String getRemovableBattery() {
        return removableBattery;
    }

    public String getRemovableBatteryTitle() {
        return removableBatteryTitle;
    }

    public String getChargingTimeTitle() {
        return chargingTimeTitle;
    }

    public String getRangeTitle() {
        return rangeTitle;
    }

    public String getTopSpeedTitle() {
        return topSpeedTitle;
    }

    public List<VariantSwitcherBeanModel> getSelectedProduct() {
        return productInfo != null ? new ArrayList<>(productInfo) : Collections.emptyList();
    }

    public String getButtonLabelLink() {
        return CommonUtils.getLinkWithHTML(buttonLabelLink, resourceResolver);
    }

    public boolean isButtonNewtab() {
        return buttonNewtab;
    }

    public String getTopSpeedDesktopIcon() {
        return CommonUtils.getDMImagePathLink(topSpeedDesktopIcon, resourceResolver);
    }

    public String getRangeDesktopIcon() {
        return CommonUtils.getDMImagePathLink(rangeDesktopIcon, resourceResolver);
    }

    public String getChargingTimeDesktopIcon() {
        return CommonUtils.getDMImagePathLink(chargingTimeDesktopIcon, resourceResolver);
    }

    public boolean isEnableFastChargingTime() {
        return enableFastChargingTime;
    }

    public String getBatteryDesktopIcon() {
        return CommonUtils.getDMImagePathLink(batteryDesktopIcon, resourceResolver);
    }

    public String getBatteryMobileIcon() {
        return CommonUtils.getDMImagePathLink(batteryMobileIcon, resourceResolver);
    }
    
    public String getTopSpeedMobileIcon() {
        return CommonUtils.getDMImagePathLink(topSpeedMobileIcon, resourceResolver);
    }

    public String getRangeMobileIcon() {
        return CommonUtils.getDMImagePathLink(rangeMobileIcon, resourceResolver);
    }

    public String getChargingTimeMobileIcon() {
        return CommonUtils.getDMImagePathLink(chargingTimeMobileIcon, resourceResolver);
    }

    public String getExShowroomLabel() {
        return exShowroomLabel;
    }

    public String getPreRemovableBatteryTitle() {
        return preRemovableBatteryTitle;
    }

    public String getDataPosition() {
        return dataPosition;
    }

    public String getAcceleratorDesktopIcon() {
        return acceleratorDesktopIcon;
    }

    public String getDefaultCity() {
        return defaultCity;
    }

    public String getAcceleratorMobileIcon() {
        return acceleratorMobileIcon;
    }

    public String getAcceleratorTitle() {
        return acceleratorTitle;
    }

    public List<LearnMoreRedirectionBeanModel> getLearnMoreRedirectionList() {
        return learnMoreRedirectionList != null ? new ArrayList<>(learnMoreRedirectionList) : Collections.emptyList();
    }

    /**
     * Retrieves the JSON data representing product details with variant
     * information.
     *
     * @param resolver The Sling Resource Resolver.
     * @return JsonNode containing product details with variant information.
     * @throws IOException if an error occurs while retrieving the data.
     */
    public List<ProductInfoBeanModel> getData(ResourceResolver resolver) throws IOException {
        List<ProductInfoBeanModel> productInfoList = CommonUtils.readProductInfoJson(resolver);
        if (null != productInfoList) {
            List<String> productList = productInfo.stream()
                    .map(VariantSwitcherBeanModel::getProductSku)
                    .collect(Collectors.toList());
            productInfoList.removeIf(item -> !(productList.contains(item.getVarSku())));
            return productInfoList;
        } else {
            log.error("Error retrieving product details with variant. The JsonNode object is null.");
        }
        return new ArrayList<>();
    }

    /**
     * Generates JSON data for consumption by React.
     *
     * @return JSON data as a String.
     * @throws IOException if an error occurs while generating JSON data.
     */
    public String getJson() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("dataPosition", getDataPosition());
        jsonMap.put("defaultCity",getDefaultCity());
        Map<String, Object> productDetailsMap = new HashMap<>();
        productDetailsMap.put("battery", getRemovableBatteryTitle());
        productDetailsMap.put("charging_time", getChargingTimeTitle());
        productDetailsMap.put("enableFastChargingTime", isEnableFastChargingTime());
        productDetailsMap.put("range", getRangeTitle());
        productDetailsMap.put("top_speed", getTopSpeedTitle());
        productDetailsMap.put("certifiedRange", getCertifiedRange());
        productDetailsMap.put("enableCertifiedRange", getEnableCertifiedRange());
        productDetailsMap.put("batteryQuantity", getBatteryQuantity());
        productDetailsMap.put("removableBattery", getRemovableBattery());
        productDetailsMap.put("accelerator",getAcceleratorTitle());
        jsonMap.put("productDetailsTitle", productDetailsMap);
        jsonMap.put("buttonLabel", getButtonLabel());
        jsonMap.put("buttonLabelLink", getButtonLabelLink());
        jsonMap.put("isButtonNewtab", isButtonNewtab());
        jsonMap.put("title", getTitle());
        jsonMap.put("preTitle", getPreTitle());
        jsonMap.put("titleTag", getTitleTag());
        jsonMap.put("preTitleTag", getPreTitleTag());
        jsonMap.put("items", getData(resourceResolver));
        jsonMap.put("selectedProduct", getSelectedProduct());
        jsonMap.put("chargingTimeDesktopIcon", getChargingTimeDesktopIcon());
        jsonMap.put("batteryDesktopIcon", getBatteryDesktopIcon());
        jsonMap.put("batteryMobileIcon", getBatteryMobileIcon());
        jsonMap.put("rangeDesktopIcon", getRangeDesktopIcon());
        jsonMap.put("topSpeedDesktopIcon", getTopSpeedDesktopIcon());
        jsonMap.put("chargingTimeMobileIcon", getChargingTimeMobileIcon());
        jsonMap.put("rangeMobileIcon", getRangeMobileIcon());
        jsonMap.put("topSpeedMobileIcon", getTopSpeedMobileIcon());
        jsonMap.put("exShowroomLabel", getExShowroomLabel());
        jsonMap.put("preRemovableBatteryTitle", getPreRemovableBatteryTitle());
        jsonMap.put("acceleratorDesktopIcon",getAcceleratorDesktopIcon());
        jsonMap.put("acceleratorMobileIcon",getAcceleratorMobileIcon());
        jsonMap.put("learnMoreRedirections",getLearnMoreRedirectionList());
        return CommonUtils.toJson(jsonMap);
    }
}
