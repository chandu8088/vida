package com.heromotocorp.vida.core.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CompareVidaBeanModel {

	/** The Constant log. */
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@ValueMapValue
	private String productSku;

	@ValueMapValue
	private String baseImg;

	@ValueMapValue
	private String randomNameContent;

	@ValueMapValue
	private String whatsIncludedData;

	@ValueMapValue
	private String addOnIcon;

	@ValueMapValue
	private String addOnTitle;

	@ValueMapValue
	private String addOnSubText;

	@ValueMapValue
	private String addOnBtnText;

	@ValueMapValue
	private String addOnBtnNavLink;

	@ValueMapValue
	private String tabHeaderLabel;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@ValueMapValue
	private boolean newTab;

	@SlingObject
	private ResourceResolver resolver;

	@ValueMapValue
	private String imageAlt;

	@ValueMapValue
	private String imageTitle;

	@ValueMapValue
	private String value;

	@ValueMapValue
	private String unit;

	@ValueMapValue
	private String title;

	@ValueMapValue
	private String icon;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@ValueMapValue
	private boolean buyNowNewTab;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	@ValueMapValue
	private boolean learnMoreNewTab;

	@ValueMapValue
	private String buyNowUrl;

	@ValueMapValue
	private String learnMoreUrl;


	public String getProductSku() {
		return productSku;
	}

	public String getBaseImg() {
		return CommonUtils.getDMImagePathLink(baseImg,resolver);
	}

	public String getRandomNameContent() {
		return randomNameContent;
	}

	public String getWhatsIncludedData() {
		return whatsIncludedData;
	}

	public String getAddOnIcon() {
		return CommonUtils.getDMImagePathLink(addOnIcon,resolver);
	}

	public String getAddOnTitle() {
		return addOnTitle;
	}

	public String getAddOnSubText() {
		return addOnSubText;
	}

	public String getAddOnBtnText() {
		return addOnBtnText;
	}

	public String getAddOnBtnNavLink() {
		return CommonUtils.getLinkWithHTML(addOnBtnNavLink,resolver);
	}

	public boolean isNewTab() {
		return newTab;
	}

	public String getTabHeaderLabel() {
		return tabHeaderLabel;
	}

	public String getImageAlt() {
		return imageAlt;
	}

	public String getImageTitle() {
		return imageTitle;
	}

	public String getValue() {
		return value;
	}

	public String getUnit() {
		return unit;
	}

	public String getTitle() {
		return title;
	}

	public String getIcon() {
		return icon;
	}

	public boolean isBuyNowNewTab() {
		return buyNowNewTab;
	}

	public boolean isLearnMoreNewTab() {
		return learnMoreNewTab;
	}

	public String getBuyNowUrl() {
		return CommonUtils.getLinkWithHTML(buyNowUrl,resolver);
	}

	public String getLearnMoreUrl() {
		return CommonUtils.getLinkWithHTML(learnMoreUrl,resolver);
	}
}
