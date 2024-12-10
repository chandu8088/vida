package com.heromotocorp.vida.core.service;

import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;

public interface ProductVariantPriceJsonService {
    void productVariantPriceJson(ResourceResolver resourceResolver, Map<String, String> citypriceMap, String skuParam);

}