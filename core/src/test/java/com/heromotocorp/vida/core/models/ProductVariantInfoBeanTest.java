package com.heromotocorp.vida.core.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
class ProductVariantInfoBeanTest {

    @InjectMocks
    ProductVariantInfoBean productVariantInfoBean;

    Map<String, String> mapTest = new HashMap<>();


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapTest.put("test","test");
        productVariantInfoBean.setSectionid("1");
        productVariantInfoBean.setBuyVidaMobileImagePath("/mobilePath");
        productVariantInfoBean.setBuyVidaDesktopImagePath("/deskpath");
        productVariantInfoBean.setVariant_range_value("2");
        productVariantInfoBean.setVariant_range_unit("kwh");
        productVariantInfoBean.setVariant_charging_time_value("02");
        productVariantInfoBean.setVariant_charging_time_unit("kw");
        productVariantInfoBean.setVariant_accelerator_unit("sec");
        productVariantInfoBean.setVariant_top_speed_value("2");
        productVariantInfoBean.setVariant_top_speed_unit("kms");
        productVariantInfoBean.setCitypriceMap(mapTest);
        productVariantInfoBean.setAcceration(mapTest);
        productVariantInfoBean.setAltText("alt");
        productVariantInfoBean.setVariant_name("vn");
    }

    @Test
    void getProductVariantData() {
        assertEquals("1",productVariantInfoBean.getSectionid());
        assertEquals("/mobilePath",productVariantInfoBean.getBuyVidaMobileImagePath());
        assertEquals("/deskpath",productVariantInfoBean.getBuyVidaDesktopImagePath());
        assertEquals("2",productVariantInfoBean.getVariant_range_value());
        assertEquals("kwh",productVariantInfoBean.getVariant_range_unit());
        assertEquals("02",productVariantInfoBean.getVariant_charging_time_value());
        assertEquals("kw",productVariantInfoBean.getVariant_charging_time_unit());
        assertEquals("sec",productVariantInfoBean.getVariant_accelerator_unit());
        assertEquals("2",productVariantInfoBean.getVariant_top_speed_value());
        assertEquals("kms",productVariantInfoBean.getVariant_top_speed_unit());
        assertEquals("alt",productVariantInfoBean.getAltText());
        assertEquals("vn",productVariantInfoBean.getVariant_name());
        assertEquals(mapTest,productVariantInfoBean.getCitypriceMap());
        assertEquals(mapTest,productVariantInfoBean.getAcceration());
    }

}