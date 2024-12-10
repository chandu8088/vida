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
public class VariantFeatureFocusedViewBeanModel {

	/** The Constant log. */
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@ValueMapValue
	private String header;

	@ValueMapValue
	private String content;

	@ValueMapValue
	private String cardNumber;

	@ValueMapValue
	private String cardTextColor;

	@ValueMapValue
	private String cardBgColor;

	@ValueMapValue
	private String mobileImg;

	@ValueMapValue
	private String desktopImg;

	@ValueMapValue
	private String headLightImgDesktop;

	@ValueMapValue
	private String headLightImgMobile;

	@ValueMapValue
	private boolean isHeadLight;

	@ValueMapValue
	private String bikeImgAlt;

	@ValueMapValue
	private String bikeImgTitle;

	@SlingObject
	private ResourceResolver resolver;

	public String getHeader() {
		return header;
	}

	public String getContent() {
		return content;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCardTextColor() {
		return cardTextColor;
	}

	public String getCardBgColor() {
		return cardBgColor;
	}

	public String getMobileImg() {
		return mobileImg;
	}

	public String getDesktopImg() {
		return desktopImg;
	}

	public String getHeadLightImgDesktop() {
		return headLightImgDesktop;
	}

	public String getHeadLightImgMobile() {
		return headLightImgMobile;
	}

	public boolean getIsHeadLight() {
		return isHeadLight;
	}

	public String getBikeImgAlt() {
		return bikeImgAlt;
	}

	public String getBikeImgTitle() {
		return bikeImgTitle;
	}
}
