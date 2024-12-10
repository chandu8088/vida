package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

/**
 * The class Test ride model is to get the country codes and convert them into
 * an array of String.
 */
@Model(adaptables = { Resource.class, SlingHttpServletRequest.class },
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TestRideModel {

	/** The countrycode. */
	@Inject
	private String countrycode;
	
    /** The profilepageurl. */
    @Inject
    private String profilepageurl;
    
    /** The short term redirect URL. */
    @Inject
    private String shortTermRedirectURL;
    
    /** The long term redirect URL. */
    @Inject
    private String longTermRedirectURL;

	/** The country codes. */
	private String[] countryCodes;
	
	/** The resolver. */
	@ScriptVariable
	private ResourceResolver resolver;

	@ChildResource
	private List<CompareVidaBeanModel> randomNames;

	@ChildResource
	private List<CompareVidaBeanModel> tabList;

	@ValueMapValue
	private String tabListTitle;

	@ChildResource
	private List<TestRideCardInfoBeanModel> cardInfo;

	@Inject
	private String redirectionUrl;

	@Inject
	private String buyDateHeader;

	@ChildResource
	private List<TestRidePopupBeanModel> buyDateOptions;


	public String getPinCodeCard() throws IOException {
		if(cardInfo != null && cardInfo.size() >0) {
			for(TestRideCardInfoBeanModel testRideCardInfoBeanModel: cardInfo) {
				if(testRideCardInfoBeanModel.getCardDataType().equals("pinCodeCard")) {
					return testRideCardInfoBeanModel.getJson();
				}
			}
		}
			return null;
	} 

	public String getNearByCard() throws IOException {
		if(cardInfo != null && cardInfo.size() >0) {
			for(TestRideCardInfoBeanModel testRideCardInfoBeanModel: cardInfo) {
				if(testRideCardInfoBeanModel.getCardDataType().equals("nearByCard")) {
					return testRideCardInfoBeanModel.getJson();
				}
			}
		}
			return null;
	}


	public List<CompareVidaBeanModel> getTabList() {
		return tabList != null ? new ArrayList<>(tabList) : Collections.emptyList();
	}

	public String getTabListTitle() {
		return tabListTitle;
	}

	public List<CompareVidaBeanModel> getRandomNames() {
		return randomNames != null ? new ArrayList<>(randomNames) : Collections.emptyList();
	}

	public String getJson() throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("randomNameLabels", getRandomNames());
		return CommonUtils.toJson(jsonMap);
	}

	public String getTabListJson() throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("title", getTabListTitle());
		jsonMap.put("tabTitles", getTabList());
		return CommonUtils.toJson(jsonMap);
	}

	/**
	 * Init method is to split the countrycode String and store it in the array.
	 */
	@PostConstruct
	protected void init() {
		if (Objects.nonNull(countrycode)) {
			countryCodes = countrycode.split(Constants.COMA);
		} 
	}

	/**
	 * Get country codes string [ ].
	 *
	 * @return the string [ ]
	 */
	public String[] getCountryCodes() {
		return countryCodes != null ? countryCodes.clone() : new String[0] ;
	}
	
	/**
	 * Gets the profilepageurl.
	 *
	 * @return the profilepageurl
	 */
	public String getProfilepageurl() {
		return resolver.map(profilepageurl);
	}

	/**
	 * Gets the short term redirect URL.
	 *
	 * @return the short term redirect URL
	 */
	public String getShortTermRedirectURL() {
		return resolver.map(shortTermRedirectURL);
	}

	/**
	 * Gets the long term redirect URL.
	 *
	 * @return the long term redirect URL
	 */
	public String getLongTermRedirectURL() {
		return resolver.map(longTermRedirectURL);
	}

	/**
	 * Gets the Rescheduling redirect URL.
	 *
	 * @return the Rescheduling redirect URL
	 */
	public String getRedirectionUrl() {
		return CommonUtils.getLinkWithHTML(redirectionUrl,resolver);
	}

	public String getBuyDateHeader() {
		return buyDateHeader;
	}

	public List<TestRidePopupBeanModel> getBuyDateOptions() {
		return buyDateOptions != null ? new ArrayList<>(buyDateOptions) : Collections.emptyList();
	}

	public String getBuyDatePopupConfig() throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("buyDateHeader",getBuyDateHeader());
		jsonMap.put("buyDateOptions",getBuyDateOptions());
		return CommonUtils.toJson(jsonMap);
	}
}
