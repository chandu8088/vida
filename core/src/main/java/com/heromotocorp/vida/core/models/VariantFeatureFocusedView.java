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
public class VariantFeatureFocusedView {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(VariantFeatureFocusedView.class);

	@ChildResource
	private List<VariantFeatureFocusedViewBeanModel> items;

	@ValueMapValue
	private String aboutText;


	@SlingObject
	private ResourceResolver resolver;

	public List<VariantFeatureFocusedViewBeanModel> getItems() {
		return items != null ? new ArrayList<>(items) : Collections.emptyList();
	}

	public String getAboutText() {
		return aboutText;
	}

	public String getJson() throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("VariantFocusedViewContent", getItems());
		jsonMap.put("aboutText", getAboutText());
		return CommonUtils.toJson(jsonMap);
	}
}
