package com.heromotocorp.vida.core.models;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.heromotocorp.vida.core.service.ProductVariantPriceJsonService;
import com.heromotocorp.vida.core.utils.CommonUtils;
import com.heromotocorp.vida.core.utils.Constants;

import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = {
		Resource.class }, resourceType = "vida/components/vida-2.0/content/productinfobanner", defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PLPBannerModel extends Constants {

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(PLPBannerModel.class);

	/**
	 * Map to store variant specification details.
	 * This map may contain various specifications for a product variant.
	 * Key: Specification name, Value: Specification value in String format.
	 */
	Map<String, String> variantSpecificationDetails = new HashMap<>();

	/** The Constants **/
	private static final String BATTERY_VALUE = "batteryValue";

	private static final String BATTERY_UNIT = "batteryUnit";

	private static final String RANGE_VALUE = "rangeValue";

	private static final String BATTERY_PATH = "battery";

	private static final String RANGE_UNIT = "rangeUnit";

	private static final String SPECIFICATION_UNIT = "specificationUnit";

	private static final String SPECIFICATION_VALUE = "specificationValue";

	private static final String ORDER = "order";

	private static final String ACCERATION_UNIT = "accerationUnit";

	private static final String ACCELERATION_VALUE = "accelerationValue";

	private static final String SPECIFICATION_ICON = "specificationinIcon";

	private static final String SPECIFICATION_ICON_MOBILE = "specificationinIconMobile";

	private static final String SPECIFICATION_LABEL = "specificationLabel";

	private static final String TOP_SPEED_UNIT = "topSpeedUnit";

	private static final String TOP_SPEED_VALUE = "topSpeedsValue";

	private static final String ACCELERATION_ICON = "acceleratorIcon";

	private static final String ACCELERATION_ICON_ALT = "acceleratorIconAlt";

	private static final String SPECIFICATION_ICON_ALT = "specificationinIconAlt";

	private static final String ACCELERATION_LABEL = "accelerationlabel";

	private static final String INCLINE_CAPACITY = "inclineCapacity";

	private static final String BATTERY_CAPACITY = "batteryCapacity";

	private static final String THREE_PLUS = "3+";

	private static final String THREE = "3";

	/** The resource resolver. */
	@SlingObject
	private ResourceResolver resourceResolver;

	@ValueMapValue
	private String headerLabel;

	@ValueMapValue
	private String buyNowHeaderLabel;

	@ValueMapValue
	private String modelButtonLabel;

	@ValueMapValue
	private String exShowRoomLabel;

	@ValueMapValue
	private boolean isVariantTwo;

	@ValueMapValue
	private String buyNowButtonLabel;

	@ValueMapValue
	private String learnMoreLabel;

	@ValueMapValue
	private String mobileBannerBgPath;

	@ValueMapValue
	private String desktopBannerBgPath;

	@ValueMapValue
	private String itemName;

	@ValueMapValue
	private String city;

	@ValueMapValue
	private String buyNowButtonLabelLink;

	@ValueMapValue
	private String modelButtonUrl;

	@ValueMapValue
	private String learnMoreLabelLink;

	@ValueMapValue
	private boolean learnMoreNewTab;

	@ValueMapValue
	private boolean buyNowNewtab;

	@ValueMapValue
	private String accelerationIcon;

	@ValueMapValue
	private String accelerationIconMobile;

	@ValueMapValue
	private String accelerationIconAlt;

	@ValueMapValue
	private String accelerationLabel;

	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String accelerationRange;

	@ValueMapValue
	private String accelerationOrder;

	@ValueMapValue
	private String inclineIcon;

	@ValueMapValue
	private String inclineIconMobile;

	@ValueMapValue
	private String inclineIconAlt;

	@ValueMapValue
	private String inclineLabel;

	@ValueMapValue
	private String inclineOrder;

	@ValueMapValue
	private String batteriesIcon;

	@ValueMapValue
	private String batteriesIconMobile;

	@ValueMapValue
	private String batteriesIconAlt;

	@ValueMapValue
	private String batteriesLabel;

	@ValueMapValue
	private String batteriesOrder;

	@ValueMapValue
	private String speedIcon;

	@ValueMapValue
	private String speedIconMobile;

	@ValueMapValue
	private String speedIconAlt;

	@ValueMapValue
	private String speedLabel;

	@ValueMapValue
	private String speedOrder;

	@ValueMapValue
	private String rangeIcon;

	@ValueMapValue
	private String rangeIconMobile;

	@ValueMapValue
	private String rangeIconAlt;

	@ValueMapValue
	private String rangeLabel;

	@ValueMapValue
	private String rangeOrder;

	@ValueMapValue
	private String ridingModesIcon;

	@ValueMapValue
	private String ridingModesIconMobile;

	@ValueMapValue
	private String ridingModesIconAlt;

	@ValueMapValue
	private String ridingModesLabel;

	@ValueMapValue
	private String ridingModesOrder;

	@ValueMapValue
	private String preHeaderLabel;

	@ValueMapValue
	private String inclineUnit;

	@ValueMapValue
	private String dataPosition;

	@ValueMapValue
	private Boolean enableCertifiedRange;

	@ValueMapValue
	private String mainSpecItemName;

	@ValueMapValue
	@Default(values = StringUtils.EMPTY)
	private String batteryCapacityValuePrefix;

	@ChildResource
	private List<ImageVariantBeanModel> variantImageList;

	@Inject
	ProductVariantPriceJsonService productVariantPriceJsonService;

	public String getRidingModesOrder() {
		return ridingModesOrder;
	}

	public String getRidingModesLabel() {
		return ridingModesLabel;
	}

	public String getRidingModesIconAlt() {
		return ridingModesIconAlt;
	}

	public String getRidingModesIconMobile() {
		return ridingModesIconMobile;
	}

	public String getRidingModesIcon() {
		return ridingModesIcon;
	}

	public String getRangeOrder() {
		return rangeOrder;
	}

	public String getRangeLabel() {
		return rangeLabel;
	}

	public String getRangeIconAlt() {
		return rangeIconAlt;
	}

	public String getRangeIconMobile() {
		return rangeIconMobile;
	}

	public String getRangeIcon() {
		return rangeIcon;
	}

	public String getSpeedOrder() {
		return speedOrder;
	}

	public String getSpeedLabel() {
		return speedLabel;
	}

	public String getSpeedIconAlt() {
		return speedIconAlt;
	}

	public String getSpeedIconMobile() {
		return speedIconMobile;
	}

	public String getSpeedIcon() {
		return speedIcon;
	}

	public String getBatteriesOrder() {
		return batteriesOrder;
	}

	public String getBatteriesLabel() {
		return batteriesLabel;
	}

	public String getBatteriesIconAlt() {
		return batteriesIconAlt;
	}

	public String getBatteriesIconMobile() {
		return batteriesIconMobile;
	}

	public String getBatteriesIcon() {
		return batteriesIcon;
	}

	public String getInclineOrder() {
		return inclineOrder;
	}

	public String getInclineLabel() {
		return inclineLabel;
	}

	public String getInclineIconAlt() {
		return inclineIconAlt;
	}

	public String getInclineIconMobile() {
		return inclineIconMobile;
	}

	public String getInclineIcon() {
		return inclineIcon;
	}

	public String getAccelerationOrder() {
		return accelerationOrder;
	}

	public String getAccelerationLabel() {
		return accelerationLabel;
	}

	public String getAccelerationIconAlt() {
		return accelerationIconAlt;
	}

	public String getAccelerationIconMobile() {
		return accelerationIconMobile;
	}

	public String getAccelerationIcon() {
		return accelerationIcon;
	}

	public String getAccelerationRange() {
		return accelerationRange;
	}

	public String getItemName() {
		return itemName != null ? itemName : "V1 PRO";
	}

	public String getHeaderLabel() {
		return headerLabel;
	}

	public String getBuyNowHeaderLabel() {
		return buyNowHeaderLabel;
	}

	public String getModelButtonLabel() {
		return modelButtonLabel;
	}

	public String getExShowRoomLabel() {
		return exShowRoomLabel;
	}

	public String getBuyNowButtonLabel() {
		return buyNowButtonLabel;
	}

	public String getLearnMoreLabel() {
		return learnMoreLabel;
	}

	public String getMobileBannerBgPath() {
		return mobileBannerBgPath;
	}

	public String getDesktopBannerBgPath() {
		return desktopBannerBgPath;
	}

	public String getCity() {
		return city;
	}

	public String getBuyLabelLink() {
		return buyNowButtonLabelLink;
	}

	public String getModelButtonUrl() {
		return modelButtonUrl;
	}

	public String getLearnLabelLink() {
		return learnMoreLabelLink;
	}

	public boolean IsLearnNewtab() {
		return learnMoreNewTab;
	}

	public boolean IsBuyNewtab() {
		return buyNowNewtab;
	}

	public boolean getIsVariantTwo() {
		return isVariantTwo;
	}

	public String getPreHeaderLabel() {
		return preHeaderLabel;
	}

	public String getBatteryCapacityValuePrefix() {
		return batteryCapacityValuePrefix;
	}

	public String getInclineUnit() {
		return inclineUnit;
	}

	public String getDataPosition() {
		return dataPosition;
	}
	
	public String getMainSpecItemName() {
		return mainSpecItemName;
	}

	/**
	 * Retrieves the product details with variant information based on the provided
	 * item name.
	 *
	 * @param product_name The name of the item for which product details are
	 *                  requested.
	 * @return List of ProductInfoBean containing product and variant details.
	 */
	public List<ProductInfoBean> getProductDetailsWithVariant(String product_name) {
		String location = "/content/dam/vida/config/product-master.json";
		Resource assetResource = resourceResolver.getResource(location);
        assert assetResource != null;
        Asset asset = assetResource.adaptTo(Asset.class);
        assert asset != null;
        Rendition original = asset.getOriginal();
		ObjectMapper objectMapper = new ObjectMapper();
		List<ProductInfoBean> productVariant = new ArrayList<>();

		try {
			if (original != null) {
				InputStream content = original.adaptTo(InputStream.class);
				JsonNode jsonNode = objectMapper.readTree(content);

				// json array for items
				JsonNode items = jsonNode.path(Constants.ITEMS);

				String [] itemNames = product_name.split(",");
				for(String item_name : itemNames) {
				// Find the product by item name
				JsonNode product = findProduct(items, item_name);
				ObjectNode jsonItemsObject = (product != null) ? (ObjectNode) product : null;

				if (jsonItemsObject != null) {
					// Extract basic product information
					String nameObject = jsonItemsObject.path(Constants.NAMEFILED).asText();
					ProductInfoBean productInfoBean = new ProductInfoBean();

					if (null != getPreHeaderLabel()) {
						productInfoBean.setItem_name(getPreHeaderLabel());
					} else if (StringUtils.isNotBlank(nameObject)) {
						productInfoBean.setItem_name(nameObject);
					}

					// Build image path for the main product
					String imagePathValue = Constants.PRODUCTIMAGEPATH +
							jsonItemsObject.path(Constants.SKU).asText() +
							Constants.PNGEXTENSION;

					if (StringUtils.isNotBlank(imagePathValue)) {
						productInfoBean.setItem_image(imagePathValue);
					}

					String inclineCapacity = jsonItemsObject.path(INCLINE_CAPACITY).asText();
					if(StringUtils.isNotBlank(inclineCapacity) && null != mainSpecItemName && mainSpecItemName.equals(item_name)){
						variantSpecificationDetails.put(INCLINE_CAPACITY, inclineCapacity);
					}


					productInfoBean.setVariantImageList(
							variantImageList != null ? new ArrayList<>(variantImageList) : Collections.emptyList());

					// Process variants
					ArrayNode itemsVariantArray = (ArrayNode) jsonItemsObject.path(Constants.VARIANTS);
					List<ProductVariantInfoBean> variantList = new ArrayList<>();

					if (itemsVariantArray != null) {
						for (JsonNode variantDataNode : itemsVariantArray) {
							ObjectNode itemsVariantObject = (ObjectNode) variantDataNode;
							ProductVariantInfoBean productVariantInfoBean = new ProductVariantInfoBean();

							// Extract name and set altText
							String name = itemsVariantObject.path(Constants.NAMEFILED).asText();
							if (StringUtils.isNotBlank(name)) {
								productVariantInfoBean.setAltText(name);
							}

							String batteryCapacity = jsonItemsObject.path(BATTERY_CAPACITY).asText();// 3.94 kWh
							String batteryValue = itemsVariantObject.path(BATTERY_PATH).asText();

							// Extract SKU for the variant
							String sku = itemsVariantObject.path(Constants.SKU).asText();

							if (StringUtils.isNotBlank(sku)) {
								productVariantInfoBean.setSectionid(sku);

								/*
								 *
								 * Map to store city-price information.
								 * Key: City name, Value: Price in String format.
								 */
								Map<String, String> cityPriceMap = new HashMap<>();

								// Retrieve and set pricing information for the variant
								productVariantPriceJsonService.productVariantPriceJson(resourceResolver, cityPriceMap,
										sku);
								productVariantInfoBean.setCitypriceMap(cityPriceMap);
							}

							// Extract and set riding modes
							String ridingModes = itemsVariantObject.path(Constants.RIDING_MODES)
									.asText();


							// Extract range and set range value and range unit
							String range = (null != enableCertifiedRange && Boolean.TRUE.equals(enableCertifiedRange)) ? itemsVariantObject.path(Constants.CERTIFIEDRANGE).asText() : itemsVariantObject.path(Constants.RANGE).asText();
							String rangeValue = "";
							String rangeUnit = "";
							if (StringUtils.isNotBlank(range)) {
								String[] rangeValues = range.split(Constants.SPACE);
								rangeValue = rangeValues[0];
								rangeUnit = rangeValues.length > 1 ? rangeValues[1] : StringUtils.EMPTY;
							}

							// Extract acceleration and set accelerator value and accelerator unit
							String acceleration = itemsVariantObject.path(Constants.ACCELERATOR)
									.asText();
							String accelerationsValue = "";
							String accelerationsUnit = "";
							if (StringUtils.isNotBlank(acceleration)) {
								String[] accelerations = acceleration.split(Constants.SPACE);
								// Add null check for accelerations array
								if (accelerations.length > 0) {
									accelerationsValue = accelerations[0];
									accelerationsUnit = accelerations.length > 1 ? accelerations[1]
											: StringUtils.EMPTY;
									Map<String, String> accelerationMap = new HashMap<>();
									accelerationMap.put(ACCELERATION_ICON_ALT, getAccelerationIconAlt());
									accelerationMap.put(ACCELERATION_ICON, getAccelerationIcon());
									accelerationMap.put(ACCELERATION_LABEL, getAccelerationLabel());
									accelerationMap.put(ACCELERATION_VALUE, accelerationsValue);
									accelerationMap.put(ACCERATION_UNIT, accelerationsUnit + getAccelerationRange());
									productVariantInfoBean.setAcceration(accelerationMap);
								}
							}

							// Extract and set top speed
							String topSpeed = itemsVariantObject.path(Constants.TOPSPEED).asText();
							String topSpeedsValue = "";
							String topSpeedsUnit = "";
							if (StringUtils.isNotBlank(topSpeed)) {
								String[] topSpeeds = topSpeed.split(Constants.SPACE);
								topSpeedsValue = topSpeeds[0];
								topSpeedsUnit = topSpeeds.length > 1 ? topSpeeds[1] : StringUtils.EMPTY;
								
							}

							if (StringUtils.isNotBlank(nameObject)) {
								productVariantInfoBean.setVariant_name(nameObject);
							}

							for (ImageVariantBeanModel variant : variantImageList) {
								// Extract information from the current ImageVariantBeanModel
								String sectionId = variant.getSectionid();
								String buyVariantImageMobile = variant.getBuyVariantImageMobile();
								String buyVariantImageDesktop = variant.getBuyVariantImageDesktop();

								String  buyNowLink = variant.getBuyNowLink();
								boolean buyNowNewtab = variant.isBuyNowNewtab();
								String  learnMoreLink = variant.getLearnMoreLink();
								boolean learnMoreNewTab = variant.isLearnMoreNewTab();

								// Check if the sectionId is not blank and matches the sku
								if (StringUtils.isNotBlank(sectionId) && sku.equals(sectionId)) {
									if (StringUtils.isNotBlank(batteryCapacity) && StringUtils.isNotBlank(batteryValue) && null != mainSpecItemName && mainSpecItemName.equals(item_name)) {
										try {
											variantSpecificationDetails.put(BATTERY_VALUE,
													String.format("%02.0f", Double.parseDouble(batteryValue)));
										} catch (NumberFormatException e) {
											log.warn("Error: Unable to parse batteryValue as a number");
										}
		
										// Concatenate the batteryCapacity with a prefix and put it into the map
										variantSpecificationDetails.put(BATTERY_UNIT,
												getBatteryCapacityValuePrefix() + " " + batteryCapacity);
									}
									if (StringUtils.isNotBlank(ridingModes) && null != mainSpecItemName && mainSpecItemName.equals(item_name)) {
										// If ridingModes is not blank
		
										// Split ridingModes using COMA (comma) as a delimiter
										String[] ridingMode = ridingModes.split(Constants.COMA);
		
										// Check if the length of the resulting array is greater than 3
										if (ridingMode.length > 3) {
											// If the length is greater than 3, put "3+" in the map for key "ridingModes"
											variantSpecificationDetails.put(RIDING_MODES, THREE_PLUS);
										} else if (ridingMode.length == 3) {
											variantSpecificationDetails.put(RIDING_MODES, THREE);
										}
										else {
											// If the length is not greater than 3, put the original ridingModes in the map
											variantSpecificationDetails.put(RIDING_MODES, ridingModes);
										}
									}
									if(null != mainSpecItemName && mainSpecItemName.equals(item_name)) {
										variantSpecificationDetails.put(RANGE_VALUE, rangeValue);
										variantSpecificationDetails.put(RANGE_UNIT, rangeUnit);
									}
									if(null != mainSpecItemName && mainSpecItemName.equals(item_name)) {
										variantSpecificationDetails.put(ACCELERATION_VALUE, accelerationsValue);
										variantSpecificationDetails.put(ACCERATION_UNIT, accelerationsUnit + SPACE + getAccelerationRange());
									}
									if(null != mainSpecItemName && mainSpecItemName.equals(item_name)) {
										variantSpecificationDetails.put(TOP_SPEED_UNIT, topSpeedsUnit);
										variantSpecificationDetails.put(TOP_SPEED_VALUE, topSpeedsValue);
									}

									// Check if both desktop and mobile images are not blank
									if (StringUtils.isNotBlank(buyVariantImageDesktop)
											&& StringUtils.isNotBlank(buyVariantImageMobile)) {
										// Set the desktop and mobile image paths in a ProductVariantInfoBean
										productVariantInfoBean.setBuyVidaDesktopImagePath(buyVariantImageDesktop);
										productVariantInfoBean.setBuyVidaMobileImagePath(buyVariantImageMobile);
									}
									// Check if both buy now link and learn more link are not blank
									if (StringUtils.isNotBlank(buyNowLink)
											&& StringUtils.isNotBlank(learnMoreLink)) {
										// Set the buy now and learn more links in ProductVariantInfoBean
										productVariantInfoBean.setBuyNowLink(buyNowLink);
										productVariantInfoBean.setBuyNowNewtab(buyNowNewtab);
										productVariantInfoBean.setLearnMoreLink(learnMoreLink);
										productVariantInfoBean.setLearnMoreNewTab(learnMoreNewTab);

									}
									variantList.add(productVariantInfoBean);
								}
							}
							productInfoBean.setProductVariantInfoList(variantList);
						}
						productVariant.add(productInfoBean);
					}
				} else {
					log.warn("Product not found for item name: {}", item_name);
				}
			}
		}
	 } catch (IOException e) {
			log.error("Exception occurred: {}", e);
		}
		List<ProductVariantInfoBean> finalVariantList = new ArrayList<>();
		for(ProductInfoBean productInfoBean : productVariant) {
				for(ProductVariantInfoBean productVariantInfoBean:productInfoBean.getProductVariantInfoList()) {
						finalVariantList.add(productVariantInfoBean);
				}
		}
		if(productVariant.size() > 1) {
			productVariant = productVariant.subList(0, 1);
			productVariant.get(0).setProductVariantInfoList(finalVariantList);
		}
		return productVariant;
	}

	/**
	 * Finds a product in the given JSON structure based on its name.
	 *
	 * @param items     The JSON array containing products.
	 * @param item_name The name of the product to search for.
	 * @return The JSON node representing the found product, or null if not found.
	 */
	private JsonNode findProduct(JsonNode items, String item_name) {
		try {
			// Check if the JSON array is not null
			if (items != null) {
				// Iterate through each item in the array
				for (JsonNode item : items) {
					// Get the product node from the current item
					JsonNode product = item;

					// Get the product name from the product node
					JsonNode productName = product.path(Constants.NAMEFILED);

					// Check if the product name exists and matches the target name
					if (productName != null && !productName.isMissingNode() && item_name.equals(productName.asText())) {
						// Return the found product node
						return product;
					}
				}
			}
		} catch (Exception e) {
			// Log an error message if an exception occurs during the search
			log.error("Error occurred while finding product: {}", e);
		}

		// Return null if the product is not found
		return null;
	}

	/**
	 * Extracts details from a list of resources and creates a map with relevant
	 * information.
	 *
	 * @param specificationLabel      The label associated with the specification.
	 * @param specificationIconMobile The icon mobile associated with the
	 *                                specification.
	 * @param specificationIcon       The icon associated with the specification.
	 * @param specificationIconAlt    The alternative icon associated with the
	 *                                specification.
	 * @param order                   The order associated with the specification.
	 * @param specificationUnit       The unit associated with the specification.
	 * @param specificationValue      The value associated with the specification.
	 * @return A map containing extracted details, including specification value and
	 *         unit.
	 */
	public Map<String, String> getData(String specificationLabel, String specificationIconMobile,
			String specificationIcon, String specificationIconAlt, String order, String specificationUnit,
			String specificationValue) {
		// Create a map to store extracted details
		Map<String, String> detailMap = new HashMap<>();
    
		// Perform null checks and add to the map only if not null
		if (specificationLabel != null) {
			detailMap.put(SPECIFICATION_LABEL, specificationLabel);
		}
		if (specificationIconMobile != null) {
			detailMap.put(SPECIFICATION_ICON_MOBILE, specificationIconMobile);
		}
		if (specificationIcon != null) {
			detailMap.put(SPECIFICATION_ICON, specificationIcon);
		}
		if (specificationIconAlt != null) {
			detailMap.put(SPECIFICATION_ICON_ALT, specificationIconAlt);
		}
		if (order != null) {
			detailMap.put(ORDER, order);
		}
		if (specificationUnit != null) {
			detailMap.put(SPECIFICATION_UNIT, specificationUnit);
		}
		if (specificationValue != null) {
			detailMap.put(SPECIFICATION_VALUE, specificationValue);
		}

		// Return the map containing extracted details
		return detailMap;
	}

	/**
	 * Retrieves a list of variant specification details based on different
	 * specifications.
	 *
	 * @return List of maps, each containing specification details for a specific
	 *         variant.
	 */
	public List<Map<String, String>> getVariantSpecificationDetails() {
		// Initialize a list to store variant specification details
		List<Map<String, String>> variantSpec = new ArrayList<>();

		// Check and retrieve riding modes details
		Map<String, String> ridingModesMap = getData(getRidingModesLabel(), getRidingModesIconMobile(),
				getRidingModesIcon(), getRidingModesIconAlt(), getRidingModesOrder(), "",
				variantSpecificationDetails.get(RIDING_MODES));
		variantSpec.add(ridingModesMap);

		// Check and retrieve range details
		Map<String, String> rangeMap = getData(getRangeLabel(), getRangeIconMobile(), getRangeIcon(), getRangeIconAlt(),
				getRangeOrder(), variantSpecificationDetails.get(RANGE_UNIT),
				variantSpecificationDetails.get(RANGE_VALUE));
		variantSpec.add(rangeMap);

		// Check and retrieve speed details
		Map<String, String> speedMap = getData(getSpeedLabel(), getSpeedIconMobile(), getSpeedIcon(), getSpeedIconAlt(),
				getSpeedOrder(), variantSpecificationDetails.get(TOP_SPEED_UNIT),
				variantSpecificationDetails.get(TOP_SPEED_VALUE));
		variantSpec.add(speedMap);

		// Check and retrieve batteries details
		Map<String, String> batteriesMap = getData(getBatteriesLabel(), getBatteriesIconMobile(), getBatteriesIcon(),
				getBatteriesIconAlt(), getBatteriesOrder(), variantSpecificationDetails.get(BATTERY_UNIT),
				variantSpecificationDetails.get(BATTERY_VALUE));
		variantSpec.add(batteriesMap);

		// Check and retrieve incline details
		Map<String, String> inclinemap = getData(getInclineLabel(), getInclineIconMobile(), getInclineIcon(),
				getInclineIconAlt(), getInclineOrder(), getInclineUnit(), variantSpecificationDetails.get(INCLINE_CAPACITY));
		variantSpec.add(inclinemap);

		// Check and retrieve acceleration details
		Map<String, String> accelerMapVal = getData(getAccelerationLabel(), getAccelerationIconMobile(),
				getAccelerationIcon(), getAccelerationIconAlt(), getAccelerationOrder(),
				variantSpecificationDetails.get(ACCERATION_UNIT), variantSpecificationDetails.get(ACCELERATION_VALUE));
		variantSpec.add(accelerMapVal);

		// Return the list of variant specification details
		return variantSpec;
	}

	/**
	 * Generates a JSON representation of product and variant details.
	 *
	 * @return JSON string representing the product and variant details.
	 * @throws IOException If an I/O error occurs during JSON conversion.
	 */
	public String getJson() throws IOException {
		Map<String, Object> jsonMap = new HashMap<>();
		jsonMap.put("dataPosition", getDataPosition());
		jsonMap.put("productWithVariant", getProductDetailsWithVariant(getItemName()));
		jsonMap.put("variant_specification_details", getVariantSpecificationDetails());
		jsonMap.put("headerLabel", getHeaderLabel());
		jsonMap.put("isVariantTwo", getIsVariantTwo());
		jsonMap.put("buyNowHeaderLabel", getBuyNowHeaderLabel());
		jsonMap.put("modelButtonLabel", getModelButtonLabel());
		jsonMap.put("exShowRoomLabel", getExShowRoomLabel());
		jsonMap.put("buyNowButtonLabel", getBuyNowButtonLabel());
		jsonMap.put("learnMoreLabel", getLearnMoreLabel());
		jsonMap.put("mobileBannerBackgroundImagePath",
				CommonUtils.getDMImagePathLink(getMobileBannerBgPath(), resourceResolver));
		jsonMap.put("desktopBannerBackgroundImagePath",
				CommonUtils.getDMImagePathLink(getDesktopBannerBgPath(), resourceResolver));
		jsonMap.put("city", getCity());
		jsonMap.put("buyNowButtonLabelLink", CommonUtils.getLinkWithHTML(getBuyLabelLink(), resourceResolver));
		jsonMap.put("modelButtonUrl", CommonUtils.getLinkWithHTML(getModelButtonUrl(), resourceResolver));
		jsonMap.put("learnMoreLabelLink", CommonUtils.getLinkWithHTML(getLearnLabelLink(), resourceResolver));
		jsonMap.put("learnMoreNewTab", IsLearnNewtab());
		jsonMap.put("buyNowNewtab", IsBuyNewtab());
		return CommonUtils.toJson(jsonMap);
	}

}