package com.heromotocorp.vida.core.models;


import com.heromotocorp.vida.core.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import java.io.IOException;
import java.util.*;

/**
 * Model to get the multifield values of Login.
 *
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class },defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class LoginModel {


	/** The resolver. */
	@ScriptVariable
	private ResourceResolver resolver;

    /** Redirection URL. */

    @ValueMapValue
    @Default(values = StringUtils.EMPTY)
    private String redirectionUrl;

    /** Terms And Condition URL. */

    @ValueMapValue
    @Default(values = StringUtils.EMPTY)
    private String termsAndConditionUrl;

	@ChildResource
	private List<CompareVidaBeanModel> randomNames;

	@ChildResource
	private List<BikeVariantBeanModel> bikeVariantDetails;

	public List<CompareVidaBeanModel> getRandomNames() {
		return randomNames != null ? new ArrayList<>(randomNames) : Collections.emptyList();
	}

	public List<BikeVariantBeanModel> getBikeVariantDetails() {
		return bikeVariantDetails != null ? new ArrayList<>(bikeVariantDetails) : Collections.emptyList();
	}

	public String getJson() throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("randomNameLabels", getRandomNames());
		return CommonUtils.toJson(jsonMap);
	}

	public String getBikeJson() throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("bikeVariants", getBikeVariantDetails());
		return CommonUtils.toJson(jsonMap);
	}

	/**
	 * Gets the redirection url.
	 *
	 * @return the redirection url
	 */
	public String getRedirectionUrl() {
		return resolver.map(redirectionUrl);
	}

	/**
	 * Gets the terms and condition url.
	 *
	 * @return the terms and condition url
	 */
	public String getTermsAndConditionUrl() {
		return resolver.map(termsAndConditionUrl);
	}


}
