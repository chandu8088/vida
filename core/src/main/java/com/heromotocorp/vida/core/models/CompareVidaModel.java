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
public class CompareVidaModel {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(CompareVidaModel.class);

	@ChildResource
	private List<CompareVidaBeanModel> selectedProduct;

	@ChildResource
	private List<CompareVidaBeanModel> productInfoList;

	@ChildResource
    private List<LearnMoreRedirectionBeanModel> learnMoreRedirectionList;

	@ValueMapValue
	private String buttonLabel;

	@ValueMapValue
	private String compareVariantText;

	@ValueMapValue
	private String label;

	@ValueMapValue
	private String heading;

	@ValueMapValue
	private String colorsText;

	@ValueMapValue
	private String dataPosition;

	@ValueMapValue
	private String exShowRoomText;

	@ValueMapValue
	private String colorsAvailableText;

	@ValueMapValue
	private String availableInText;

	@ValueMapValue
	private String colorSwatchesIcon;

	@ValueMapValue
	private String showOnlyDifferenceText;

	@ValueMapValue
	private String learnMoreLabel;


	@SlingObject
	private ResourceResolver resolver;

	public List<CompareVidaBeanModel> getSelectedProduct() {
		return selectedProduct != null ? new ArrayList<>(selectedProduct) : Collections.emptyList();
	}

	public String getButtonLabel() {
		return buttonLabel;
	}

	public String getLabel() {
		return label;
	}

	public String getHeading() {
		return heading;
	}

	public String getDataPosition() {
		return dataPosition;
	}

	public String getExShowRoomText() {
		return exShowRoomText;
	}

	public String getColorsAvailableText() {
		return colorsAvailableText;
	}

	public List<CompareVidaBeanModel> getProductInfoList() {
		return productInfoList != null ? new ArrayList<>(productInfoList) : Collections.emptyList();
	}

	public String getColorsText() {
		return colorsText;
	}

	public String getAvailableInText() {
		return availableInText;
	}

	public String getColorSwatchesIcon() {
		return colorSwatchesIcon;
	}

	public String getShowOnlyDifferenceText() {
		return showOnlyDifferenceText;
	}

	public String getLearnMoreLabel() {
		return learnMoreLabel;
	}

	public String getCompareVariantText() {
		return compareVariantText;
	}

	 public List<LearnMoreRedirectionBeanModel> getLearnMoreRedirectionList() {
        return learnMoreRedirectionList != null ? new ArrayList<>(learnMoreRedirectionList) : Collections.emptyList();
    }

	public String getJson() throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("dataPosition", getDataPosition());
		jsonMap.put("compareVariantText",getCompareVariantText());
		jsonMap.put("learnMoreRedirections",getLearnMoreRedirectionList());
		jsonMap.put("selectedProduct", getSelectedProduct());
		jsonMap.put("productInfoList", getProductInfoList());
		jsonMap.put("buttonLabel", getButtonLabel());
		jsonMap.put("exShowRoomText", getExShowRoomText());
		jsonMap.put("colorsAvailableText", getColorsAvailableText());
		jsonMap.put("showOnlyDifferenceText", getShowOnlyDifferenceText());
		jsonMap.put("heading", getHeading());
		jsonMap.put("colorsText", getColorsText());
		jsonMap.put("availableInText", getAvailableInText());
		jsonMap.put("colorSwatchesIcon", getColorSwatchesIcon());
		jsonMap.put("learnMoreLabel", getLearnMoreLabel());
		return CommonUtils.toJson(jsonMap);
	}
}
