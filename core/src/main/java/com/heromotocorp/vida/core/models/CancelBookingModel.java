package com.heromotocorp.vida.core.models;


import com.fasterxml.jackson.annotation.JsonInclude;
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
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CancelBookingModel {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(CancelBookingModel.class);

	@ChildResource
	private List<CompareVidaBeanModel> cancelBookingAddOnContent;

	@ValueMapValue
	private String cancelBookingTitle;

	@ValueMapValue
	private String cancelBookingBannerImg;

	@ValueMapValue
	private String bikeInfoSubText;

	@ValueMapValue
	private String cancelBookingBikeImg;

	@ValueMapValue
	private String cancelBookingDeliveryText;

	@ValueMapValue
	private String cancelBookingPayableText;

	@ValueMapValue
	private String deliveryBikeIcon;

	@ValueMapValue
	private String drawerTitle;

	@ValueMapValue
	private String cancelBtnLabel;

	@ValueMapValue
	private String confirmBtnLabel;

	@ValueMapValue
	private String cancelNavLink;

	@ValueMapValue
	private boolean cancelNewTab;

	@ValueMapValue
	private String confirmNavLink;

	@ValueMapValue
	private boolean confirmNewTab;

	@ValueMapValue
	private String deliveryChargesHandlingText;

	@ValueMapValue
	private String bikePriceWithoutTax;

	@ValueMapValue
	private String bikePriceWithTax;

	@ValueMapValue
	private String cancelBookingBgDesktop;

	@ValueMapValue
	private String cancelBookingBgMobile;

	@ValueMapValue
	private String cancelBookingVidaText;

	@ValueMapValue
	private String bannerImgAlt;

	@ValueMapValue
	private String bannerImgTitle;

	@ValueMapValue
	private String bikeImgAlt;

	@ValueMapValue
	private String bikeImgTitle;

	@SlingObject
	private ResourceResolver resolver;

	public List<CompareVidaBeanModel> getCancelBookingAddOnContent() {
		return cancelBookingAddOnContent != null ? new ArrayList<>(cancelBookingAddOnContent) : Collections.emptyList();
	}

	public String getCancelBookingTitle() {
		return cancelBookingTitle;
	}

	public String getCancelBookingBannerImg() {
		return CommonUtils.getDMImagePathLink(cancelBookingBannerImg,resolver);
	}

	public String getBikeInfoSubText() {
		return bikeInfoSubText;
	}

	public String getCancelBookingDeliveryText() {
		return cancelBookingDeliveryText;
	}

	public String getCancelBookingPayableText() {
		return cancelBookingPayableText;
	}

	public String getDeliveryBikeIcon() {
		return CommonUtils.getDMImagePathLink(deliveryBikeIcon,resolver);
	}

	public String getDrawerTitle() {
		return drawerTitle;
	}

	public String getCancelBtnLabel() {
		return cancelBtnLabel;
	}

	public String getConfirmBtnLabel() {
		return confirmBtnLabel;
	}

	public String getCancelNavLink() {
		return CommonUtils.getLinkWithHTML(cancelNavLink,resolver);
	}

	public boolean isCancelNewTab() {
		return cancelNewTab;
	}

	public String getConfirmNavLink() {
		return CommonUtils.getLinkWithHTML(confirmNavLink,resolver);
	}

	public boolean isConfirmNewTab() {
		return confirmNewTab;
	}

	public String getCancelBookingBikeImg() {
		return CommonUtils.getDMImagePathLink(cancelBookingBikeImg,resolver);
	}

	public String getDeliveryChargesHandlingText() {
		return deliveryChargesHandlingText;
	}

	public String getBikePriceWithoutTax() {
		return bikePriceWithoutTax;
	}

	public String getBikePriceWithTax() {
		return bikePriceWithTax;
	}

	public String getCancelBookingBgDesktop() {
		return cancelBookingBgDesktop;
	}

	public String getCancelBookingBgMobile() {
		return cancelBookingBgMobile;
	}

	public String getCancelBookingVidaText() {
		return cancelBookingVidaText;
	}

	public String getBannerImgAlt() {
		return bannerImgAlt;
	}

	public String getBannerImgTitle() {
		return bannerImgTitle;
	}

	public String getBikeImgAlt() {
		return bikeImgAlt;
	}

	public String getBikeImgTitle() {
		return bikeImgTitle;
	}

	public String getJson() throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("cancelBookingAddOnContent", getCancelBookingAddOnContent());
		jsonMap.put("cancelBookingTitle",getCancelBookingTitle());
		jsonMap.put("cancelBookingBannerImg", getCancelBookingBannerImg());
		jsonMap.put("bikeInfoSubText", getBikeInfoSubText());
		jsonMap.put("cancelBookingBikeImg", getCancelBookingBikeImg());
		jsonMap.put("cancelBookingDeliveryText", getCancelBookingDeliveryText());
		jsonMap.put("cancelBookingPayableText", getCancelBookingPayableText());
		jsonMap.put("deliveryBikeIcon", getDeliveryBikeIcon());
		jsonMap.put("drawerTitle", getDrawerTitle());
		jsonMap.put("cancelBtnLabel", getCancelBtnLabel());
		jsonMap.put("confirmBtnLabel", getConfirmBtnLabel());
		jsonMap.put("cancelNavLink", getCancelNavLink());
		jsonMap.put("confirmNavLink", getConfirmNavLink());
		jsonMap.put("cancelNewTab", isCancelNewTab());
		jsonMap.put("confirmNewTab", isConfirmNewTab());
		jsonMap.put("cancelBookingBgDesktop", getCancelBookingBgDesktop());
		jsonMap.put("cancelBookingBgMobile", getCancelBookingBgMobile());
		jsonMap.put("deliveryChargesHandlingText", getDeliveryChargesHandlingText());
		jsonMap.put("bikePriceWithoutTax", getBikePriceWithoutTax());
		jsonMap.put("cancelBookingVidaText", getCancelBookingVidaText());
		jsonMap.put("bannerImgAlt", getBannerImgAlt());
		jsonMap.put("bannerImgTitle", getBannerImgTitle());
		jsonMap.put("bikeImgAlt", getBikeImgAlt());
		jsonMap.put("bikeImgTitle", getBikeImgTitle());
		return CommonUtils.toJson(jsonMap);
	}
}
