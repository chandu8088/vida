package com.heromotocorp.vida.core.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import javax.jcr.Binary;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFactory;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.servlets.post.JSONResponse;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.day.cq.dam.api.Rendition;
import com.day.cq.replication.ReplicationActionType;
import com.day.cq.replication.Replicator;
import com.day.cq.wcm.api.Page;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.UrlEscapers;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.models.ProductInfoBeanModel;
import com.heromotocorp.vida.core.models.ProductVariantInfoBeanModdel;

/**
 * The Class CommonUtils.
 */
public class CommonUtils {

	private static final String INCLINE_CAPACITY = "inclineCapacity";

	private static final String BATTERY_CAPACITY = "batteryCapacity";

	private static ObjectMapper mObjectMapper;

	private static boolean enableDMFeature = false;

	@Reference
	private static GlobalConfig globalConfig;

	/**
	 * Instantiates a new common utils.
	 */
	private CommonUtils() {
		throw new IllegalStateException("CommonUtils class");

	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
	private static final String DM_IS_IMAGE_PATH = "is/image/";

	/**
	 * Gets the property value.
	 *
	 * @param valueMap the value map
	 * @param property the property
	 * @return the corresponding property in the valuemap
	 */
	public static String getPropertyValue(ValueMap valueMap, String property) {
		return valueMap.containsKey(property) ? valueMap.get(property, String.class) : StringUtils.EMPTY;
	}

	/**
	 * Gets the resource resolver.
	 *
	 * @param resourceResolverFactory the resource resolver factory
	 * @param serviceUser             the service user
	 * @return the resource resolver
	 */
	public static ResourceResolver getResourceResolver(ResourceResolverFactory resourceResolverFactory,
			String serviceUser) {
		Map<String, Object> param = new HashMap<>();
		param.put(ResourceResolverFactory.SUBSERVICE, serviceUser);
		try {
			return resourceResolverFactory.getServiceResourceResolver(param);
		} catch (LoginException e) {
			LOGGER.error("LoginException  occured method: getResourceResolver cause : %s", e);
		}
		return null;
	}

	/**
	 * Method to Replicate Content.
	 *
	 * @param session         the session
	 * @param filePath the file path
	 * @param replicator      the replicator
	 */
	public static void replicateContent(Session session, String filePath, Replicator replicator) {
		try {
			replicator.replicate(session, ReplicationActionType.ACTIVATE, filePath);
			LOGGER.info("Replicated: {}", filePath);
		} catch (Exception e) {
			LOGGER.error("Exception cause : ", e);
		}
	}

	/**
	 * Creates JSON in AEM DAM.
	 *
	 * @param jsonString the json string
	 * @param resolver   the resolver
	 * @param jsonPath   the json path
	 */
	public static void createDamAsset(String jsonString, ResourceResolver resolver, String jsonPath) {

		InputStream is = new ByteArrayInputStream(jsonString.getBytes());
		AssetManager assetManager = resolver.adaptTo(AssetManager.class);

		Session session = resolver.adaptTo(Session.class);
		ValueFactory factory;
		try {
			factory = session.getValueFactory();
			Binary binary = factory.createBinary(is);
			assetManager.createOrUpdateAsset(jsonPath, binary, JSONResponse.RESPONSE_CONTENT_TYPE, true);
		} catch (RepositoryException e) {
			LOGGER.error("Unable to create DAM file ", e);
		}

		// assetManager.createAsset(jsonPath, is, JSONResponse.RESPONSE_CONTENT_TYPE,
		// true);

	}

	/**
	 * Method to get access token.
	 *
	 * @param globalConfig the global config
	 * @param clientConfig        the client config
	 * @return the token
	 */
	public static String getToken(GlobalConfig globalConfig, ClientConfig clientConfig) {
		HttpPost postRequst = new HttpPost(globalConfig.postRequestToken());

		List<org.apache.http.NameValuePair> nvps = new ArrayList<>();

		nvps.add(new BasicNameValuePair(Constants.GRANTTYPE, globalConfig.grantType()));
		nvps.add(new BasicNameValuePair(Constants.CLIENTID, globalConfig.clientId()));
		nvps.add(new BasicNameValuePair(Constants.CLIENTSECRET, globalConfig.clientSecret()));

		nvps.add(new BasicNameValuePair(Constants.USERNAME, globalConfig.username()));
		nvps.add(new BasicNameValuePair(Constants.PSWD, globalConfig.password()));

		postRequst.setHeader(new BasicHeader(Constants.CONTENTTYPE, globalConfig.contentType()));

		try {
			postRequst.setEntity(new UrlEncodedFormEntity(nvps));
			if (Objects.nonNull(clientConfig.client())) {
				CloseableHttpResponse response = clientConfig.client().execute(postRequst);
				String jsonString = EntityUtils.toString(response.getEntity());

				Gson gson = new Gson();
				JsonElement element = gson.fromJson(jsonString, JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();

				return new StringBuilder(Constants.BEARER).append(Constants.TAB)
						.append(jsonObject.get(Constants.ACCESSTOKEN).getAsString()).toString();
			}

		} catch (JsonSyntaxException | ParseException | IOException e) {

			LOGGER.error("Exception  occured method: getToken cause : %s", e);
		}
		return null;
	}

	/**
	 * Method to Read Record.
	 *
	 * @param line the line
	 * @return the record from line
	 */
	public static String[] getRecordFromLine(String line) {
		List<String> values = new ArrayList<>();
		try (Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(Constants.COMA);
			while (rowScanner.hasNext()) {
				values.add(rowScanner.next());
			}
		}
		return Arrays.copyOf(values.toArray(), values.toArray().length, String[].class);
	}

	/**
	 * Update url.
	 *
	 * @param resource the resource
	 * @param url      the url
	 * @return the string
	 */
	public static String updateUrl(Resource resource, String url) {

		String[] urlArray = resource.getPath().split(Constants.PATH_SEPEARTOR);
		String pramone = urlArray[3];
		String pramtwo = urlArray[4];

		String subStringOne = StringUtils.substringAfter(url, Constants.BRAND_NAME + Constants.PATH_SEPEARTOR);
		String country = StringUtils.substringBefore(subStringOne, Constants.PATH_SEPEARTOR);
		String subStringTwo = StringUtils.substringAfter(subStringOne, country + Constants.PATH_SEPEARTOR);
		String language = StringUtils.substringBefore(subStringTwo, Constants.PATH_SEPEARTOR);
		String finalString = StringUtils.substringAfter(subStringTwo, language);

		return new StringBuilder(Constants.ROOT_PATH).append(pramone).append(Constants.PATH_SEPEARTOR).append(pramtwo)
				.append(finalString).toString();

	}

	/**
	 * Gets the country code.
	 *
	 * @param currentPage the current page
	 * @return the country code
	 */
	public static String getCountryCode(final Page currentPage) {
		Page languagePage = getLanguagePage(currentPage);
		final String country = "us";
		if (Objects.nonNull(languagePage) && Objects.nonNull(languagePage.getParent())
				&& Objects.nonNull(languagePage.getParent().getName())) {
			return languagePage.getParent().getName();
		}
		return country;
	}

	/**
	 * Gets the language page.
	 *
	 * @param currentPage the current page
	 * @return the language page
	 */
	public static Page getLanguagePage(final Page currentPage) {
		if (Objects.nonNull(currentPage)) {
			return currentPage.getAbsoluteParent(3);
		}
		return null;
	}

	/**
	 * Gets the language code.
	 *
	 * @param currentPage the current page
	 * @return the language code
	 */
	public static String getLanguageCode(final Page currentPage) {
		if (Objects.nonNull(currentPage)) {
			return currentPage.getLanguage(Boolean.FALSE).getLanguage();
		}
		return StringUtils.EMPTY;
	}

	/**
	 * Gets the url starting string.
	 *
	 * @param url the url
	 * @return the true if url starting string is https
	 */
	public static boolean getIsExternalURL(String url) {
		if (Objects.nonNull(url)) {
			String lowerCase = url.toLowerCase();
			return lowerCase.startsWith("http")||lowerCase.startsWith("https");
		}
		return false;
	}

	/**
	 * Format city name.
	 *
	 * @param message the message
	 * @return the string
	 */
	public static String formatCityName(String message) {
		// stores each characters to a char array
		char[] charArray = message.toCharArray();
		boolean foundSpace = true;

		for (int i = 0; i < charArray.length; i++) {

			// if the array element is a letter
			if (Character.isLetter(charArray[i])) {

				// check space is present before the letter
				if (foundSpace) {

					// change the letter into uppercase
					charArray[i] = Character.toUpperCase(charArray[i]);
					foundSpace = false;
				}
			}

			else {
				// if the new character is not character
				foundSpace = true;
			}
		}

		// convert the char array to the string
		return String.valueOf(charArray);

	}

	/**
	 * Rupee format.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String rupeeFormat(String value) {
        return rupeeFormatWithoutDecimal(value)+ ".00";
    }

	/**
	 * Rupee format Without Decimal.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String rupeeFormatWithoutDecimal(String value) {
		try {
			value = Integer.toString(Math.round(Float.parseFloat(value))).replace(",", "");
		} catch (NumberFormatException e) {
			// Handle the exception
			LOGGER.error("Error converting value to integer: {}", value, e);
			return "Invalid input";
		}
	
		char lastDigit = value.charAt(value.length() - 1);
		String result = StringUtils.EMPTY;
		int length = value.length() - 1;
		int digitCount = 0;
	
		for (int i = length - 1; i >= 0; i--) {
			result = value.charAt(i) + result;
			digitCount++;
			if (((digitCount % 2) == 0) && (i > 0)) {
				result = "," + result;
			}
		}
	
		return result + lastDigit;
	}

	/**
	 * Writes specified object as string
	 *
	 * @param object object to write
	 * @return result json
	 * @throws IOException IOException Thrown
	 */
	public static String toJson(Object object) throws IOException {
		return getMapper().writeValueAsString(object);
	}

	/**
	 * Creates an {@link ObjectMapper} for mapping json objects. Mapper can be
	 * configured here
	 *
	 * @return created {@link ObjectMapper}
	 */
	public static ObjectMapper getMapper() {
    if(mObjectMapper == null){
		  mObjectMapper = new ObjectMapper();
		  mObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
		return mObjectMapper;
	}

		/**
	 * @return
	 */
	public static String getDMImagePathLink(String imageDamPath, ResourceResolver resolver) {
		if (StringUtils.isNotBlank(imageDamPath)&& StringUtils.contains(imageDamPath, "/content/dam")
				&& !StringUtils.contains(imageDamPath, ".gif") && !StringUtils.contains(imageDamPath, ".svg")) {
			Resource imageRes = resolver.getResource(imageDamPath
					+ "/jcr:content/metadata");
			if (Objects.nonNull(imageRes)) {
				ValueMap vMap = imageRes.getValueMap();
				String dmImagePath = CommonUtils.getPropertyValue(vMap,
						"dam:scene7File");
				if(StringUtils.isEmpty(dmImagePath)){
					return imageDamPath;
				}
				String dmPublishUrl = "https://media.vidaworld.com/";
				imageDamPath = StringUtils.EMPTY;
				if (!StringUtils.isBlank(dmImagePath)) {
					imageDamPath = UrlEscapers.urlFragmentEscaper().escape(dmPublishUrl + DM_IS_IMAGE_PATH + dmImagePath)+"?fmt=webp&fmt=webp-alpha";
				}
			} else {
				return imageDamPath; 
			}
		}
		return imageDamPath;
	}

	/**
	 * Generates a valid HTML link based on the provided input link. If the input
	 * link is not blank,
	 * starts with "/content," does not start with "http," and does not end with
	 * ".html," then
	 * appends ".html" to the link. Otherwise, returns the original link.
	 *
	 * @param link The input link to be processed.
	 * @param resolver
	 * @return A valid HTML link, potentially modified by appending ".html."
	 */
	public static String getLinkWithHTML(String link, ResourceResolver resolver) {
		if (StringUtils.isNotBlank(link) && link.startsWith("/content") && !link.startsWith(Constants.HTTP)
				&& !link.endsWith(".html") && resolver != null) {
			// Check if the resource resolver and page exist for the given link
			Resource resource = resolver.getResource(link);
			if (null != resource && null != resource.adaptTo(Page.class)) {
				return resolver.map(link) + ".html";
			}
		}
		return link;
	}

	public static String getDMVideoPathLink(String videoDamPath, ResourceResolver resolver) {
		String dmVideoBasePath = "is/content/";
		if (StringUtils.isNotBlank(videoDamPath)) {
			Resource videoRes = resolver.getResource(videoDamPath
					+ "/jcr:content/metadata");
			if (Objects.nonNull(videoRes)) {
				ValueMap vMap = videoRes.getValueMap();
				String dmVideoPath = CommonUtils.getPropertyValue(vMap,
						"dam:scene7File");
				if(StringUtils.isEmpty(dmVideoPath)){
					return videoDamPath;
				}
				
				String dmPublishUrl = CommonUtils.getPropertyValue(vMap,
						"dam:scene7Domain");
				videoDamPath = StringUtils.EMPTY;
				if (!StringUtils.isBlank(dmVideoPath)
						&& !StringUtils.isBlank(dmPublishUrl)) {
					videoDamPath = dmPublishUrl + dmVideoBasePath + dmVideoPath;
				}
			} else {
				return videoDamPath; 
			}
		}
		return videoDamPath;
	}

	/**
	 * Retrieves the product details with variant information based on the provided
	 * item name.
	 *
	 * @param resourceResolver The ResourceResolver for accessing resources.
	 * @param item_name        The name of the item for which product details are
	 *                         requested.
	 * @return ObjectNode representing the product details with variant information.
	 * @throws IOException If there is an issue reading the JSON content or closing
	 *                     the InputStream.
	 */
	public static JsonNode getProductDetailsWithVariant(ResourceResolver resourceResolver)
			throws IOException {
		String location = "/content/dam/vida/config/product-master.json";
		Resource assetResource = resourceResolver.getResource(location);

		if (assetResource == null) {
			LOGGER.error("AssetResource is null. Expected location: {}", location);
			return null;
		}

		Asset asset = assetResource.adaptTo(Asset.class);

		if (asset == null) {
			LOGGER.error("Asset is null. Location: {}", location);
			return null;
		}

		Rendition original = asset.getOriginal();

		if (original == null) {
			LOGGER.error("Original rendition is null. Location: {}", location);
			return null;
		}

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode items = null;

		try (InputStream content = original.adaptTo(InputStream.class)) {
			JsonNode jsonNode = objectMapper.readTree(content);
			

			// json array for items
			items = jsonNode.path(Constants.ITEMS);
		} catch (IOException e) {
			LOGGER.error("Error reading JSON content or closing InputStream", e);
		}

		return items;
	}

		/**
	 * Read product info json.
	 */
	public static List<ProductInfoBeanModel> readProductInfoJson(ResourceResolver resourceResolver) {
		Resource assetResource = resourceResolver.getResource(Constants.CONTEN_DAM_PRODUCT_MASTER_JSON);
		assert assetResource != null;
		Asset asset = assetResource.adaptTo(Asset.class);
		assert asset != null;
		Rendition original = asset.getOriginal();

		List<ProductInfoBeanModel> productMaster = new ArrayList<>();
		try {
			if (original != null) {
				InputStream content = original.adaptTo(InputStream.class);
				StringBuilder sb = new StringBuilder();
				String line;
				BufferedReader br = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				Gson gson = new Gson();
				JsonElement element = gson.fromJson(sb.toString(), JsonElement.class);
				JsonObject jsonObject = element.getAsJsonObject();
				if (Objects.nonNull(jsonObject) && Objects.nonNull(jsonObject.get(Constants.ITEMS))) {
					JsonArray jsonArray = jsonObject.get(Constants.ITEMS).getAsJsonArray();
					if (Objects.nonNull(jsonArray)) {
						for (JsonElement jsonElement : jsonArray) {
							JsonObject jsonItemsObject = jsonElement.getAsJsonObject();
							if (Objects.nonNull(jsonItemsObject)) {
								String nameObject = jsonItemsObject.get(Constants.NAMEFILED) != null
										? jsonItemsObject.get(Constants.NAMEFILED).getAsString()
										: StringUtils.EMPTY;
								String skuItem = jsonItemsObject.get(Constants.SKU) != null
										? jsonItemsObject.get(Constants.SKU).getAsString()
										: StringUtils.EMPTY;
								ProductInfoBeanModel productInfoBeanModel = new ProductInfoBeanModel();
								if (!Objects.equals(nameObject, StringUtils.EMPTY)) {
									productInfoBeanModel.setItem_name(nameObject);
								}
								if (!Objects.equals(skuItem, StringUtils.EMPTY)) {
									productInfoBeanModel.setVarSku(skuItem);
								}

								String inclineCapacity = jsonItemsObject.get(INCLINE_CAPACITY) != null ?
									jsonItemsObject.get(INCLINE_CAPACITY).getAsString() : StringUtils.EMPTY;
								String batteryCapacity = jsonItemsObject.get(BATTERY_CAPACITY) != null 
									? jsonItemsObject.get(BATTERY_CAPACITY).getAsString() : StringUtils.EMPTY;

								if (StringUtils.isNotBlank(batteryCapacity)) {
									String[] batteryValues = batteryCapacity.split(Constants.SPACE);
									String batteryValue = batteryValues[0];
									String batteryUnit = batteryValues.length > 1 ? batteryValues[1] : StringUtils.EMPTY;
									productInfoBeanModel.setBatteryCapacity(batteryValue);
									productInfoBeanModel.setBatteryCapacityUnit(batteryUnit);
								}
			
								if(StringUtils.isNotBlank(inclineCapacity)){
									productInfoBeanModel.setInclineCapacity(inclineCapacity);
								}

								StringBuilder imagePathValue = new StringBuilder(Constants.PRODUCTIMAGEPATH);
								imagePathValue.append(jsonItemsObject.get(Constants.SKU).getAsString());
								imagePathValue.append(Constants.PNGEXTENSION);

								String imagePath = imagePathValue.toString();
								if (!Objects.equals(imagePath, StringUtils.EMPTY)) {
									productInfoBeanModel.setItem_image(imagePath);
								}

								JsonArray itemsVarinatArray = jsonItemsObject.get(Constants.VARIANTS).getAsJsonArray();
								List<ProductVariantInfoBeanModdel> temp = new ArrayList<>();
								if (Objects.nonNull(itemsVarinatArray)) {
									for (JsonElement VarinatDataElement : itemsVarinatArray) {
										JsonObject itemsVarinatObject = VarinatDataElement.getAsJsonObject();
										ProductVariantInfoBeanModdel productVariantInfoBeanModdel = new ProductVariantInfoBeanModdel();

										String sku = itemsVarinatObject.get(Constants.SKU) != null
												? itemsVarinatObject.get(Constants.SKU).getAsString()
												: StringUtils.EMPTY;
										if (!Objects.equals(sku, StringUtils.EMPTY)) {
											productVariantInfoBeanModdel.setVariant_sku(sku);
										}

										String color = itemsVarinatObject.get(Constants.COLOR) != null
												? itemsVarinatObject.get(Constants.COLOR).getAsString()
												: StringUtils.EMPTY;
										if (!Objects.equals(color, StringUtils.EMPTY)) {
											productVariantInfoBeanModdel.setVarinat_color(color);
											productVariantInfoBeanModdel.setVariant_color_id(color);
										}

										for (String colorName : Constants.COLORGRADIANT.keySet()) {
											if (Objects.equals(colorName, color)) {
												String varinatColorGradiant = Constants.COLORGRADIANT.get(colorName);
												productVariantInfoBeanModdel
														.setVarinatColorGradiant(varinatColorGradiant);
											}
										}

										String range = itemsVarinatObject.get(Constants.RANGE) != null
												? itemsVarinatObject.get(Constants.RANGE).getAsString()
												: StringUtils.EMPTY;
										if (!range.equals(StringUtils.EMPTY)) {
											String rangeValue = "";
											String rangeUnit = StringUtils.EMPTY;
											if (range.contains(Constants.SPACE)) {
												String[] rangeValues = range.split(Constants.SPACE);

												rangeValue = rangeValues[0];
												rangeUnit = rangeValues[1];
											} else {
												rangeValue = range;
											}
											productVariantInfoBeanModdel.setVariant_range_value(rangeValue);
											productVariantInfoBeanModdel.setVariant_range_unit(rangeUnit);

										}

										String certifiedRange = itemsVarinatObject.get(Constants.CERTIFIEDRANGE) != null
												? itemsVarinatObject.get(Constants.CERTIFIEDRANGE).getAsString()
												: StringUtils.EMPTY;
											if (!certifiedRange.equals(StringUtils.EMPTY)) {
												String rangeValue = "";
												String rangeUnit = StringUtils.EMPTY;
												if (certifiedRange.contains(Constants.SPACE)) {
													String[] rangeValues = certifiedRange.split(Constants.SPACE);
													rangeValue = rangeValues[0];
													rangeUnit = rangeValues[1];
												} else {
													rangeValue = certifiedRange;
												}
												productVariantInfoBeanModdel.setVariant_certified_range_value(rangeValue);
												productVariantInfoBeanModdel.setVariant_certified_range_unit(rangeUnit);
										}

										String chargingTime = itemsVarinatObject.get(Constants.CHARGINGTIME) != null
												? itemsVarinatObject.get(Constants.CHARGINGTIME).getAsString()
												: StringUtils.EMPTY;
										if (!Objects.equals(chargingTime, StringUtils.EMPTY)) {
											String chargingTimeValue = "";
											String chargingTimeUnit = StringUtils.EMPTY;
											if (chargingTime.contains(Constants.SPACE)) {
												String[] chargingTimes = chargingTime.split(Constants.SPACE);
												chargingTimeValue = chargingTimes[0];
												chargingTimeUnit = chargingTimes[1];
											} else {
												chargingTimeValue = chargingTime;
											}

											productVariantInfoBeanModdel
													.setVariant_charging_time_value(chargingTimeValue);
											productVariantInfoBeanModdel
													.setVariant_charging_time_unit(chargingTimeUnit);
										}
										String fastChargingTime = itemsVarinatObject.get(Constants.FASTCHARGINGTIME) != null
												? itemsVarinatObject.get(Constants.FASTCHARGINGTIME).getAsString()
												: StringUtils.EMPTY;
										if (!Objects.equals(fastChargingTime, StringUtils.EMPTY)) {
											String fastChargingTimeValue = "";
											String fastChargingTimeUnit = StringUtils.EMPTY;
											if (fastChargingTime.contains(Constants.SPACE)) {
												String[] fastChargingTimes = fastChargingTime.split(Constants.SPACE);
												fastChargingTimeValue = fastChargingTimes[0];
												fastChargingTimeUnit = fastChargingTimes[1];
											} else {
												fastChargingTimeValue = chargingTime;
											}

											productVariantInfoBeanModdel
													.setVariant_fast_charging_time_value(fastChargingTimeValue);;
											productVariantInfoBeanModdel
													.setVariant_fast_charging_time_unit(fastChargingTimeUnit);
										}
										String acceleration = itemsVarinatObject.get(Constants.ACCELERATOR)
												.getAsString();
										if (!acceleration.equals(StringUtils.EMPTY)) {
											String accelerationsValue = "";
											String accelerationsUnit = StringUtils.EMPTY;
											if (acceleration.contains(Constants.SPACE)) {
												String[] accelerations = acceleration.split(Constants.SPACE);
												accelerationsValue = accelerations[0];
												accelerationsUnit = accelerations[1];
											} else {
												accelerationsValue = acceleration;
											}
											productVariantInfoBeanModdel
													.setVariant_accelerator_value(accelerationsValue);
											productVariantInfoBeanModdel.setVariant_accelerator_unit(accelerationsUnit);

										}
										String topSpeed = itemsVarinatObject.get(Constants.TOPSPEED).getAsString();
										if (!topSpeed.equals(StringUtils.EMPTY)) {
											String topSpeedsValue = "";
											String topSpeedsUnit = StringUtils.EMPTY;
											if (topSpeed.contains(Constants.SPACE)) {
												String[] topSpeeds = topSpeed.split(Constants.SPACE);
												topSpeedsValue = topSpeeds[0];
												topSpeedsUnit = topSpeeds[1];
											} else {
												topSpeedsValue = topSpeed;
											}
											productVariantInfoBeanModdel.setVariant_top_speed_value(topSpeedsValue);
											productVariantInfoBeanModdel.setVariant_top_speed_unit(topSpeedsUnit);
										}

										temp.add(productVariantInfoBeanModdel);
										productInfoBeanModel.setProductVariantInfoList(temp);
									}
									productMaster.add(productInfoBeanModel);
								}
							}
						}
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error("IOException  occured method: readProductInfoJson cause : %s", e);
		}
		return productMaster;
	}
	
}