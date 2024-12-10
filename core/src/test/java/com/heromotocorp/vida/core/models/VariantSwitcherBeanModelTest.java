package com.heromotocorp.vida.core.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.sling.api.resource.Resource;

@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class VariantSwitcherBeanModelTest {

    private final AemContext context = new AemContext();

    private VariantSwitcherBeanModel variantSwitcherBeanModel = new VariantSwitcherBeanModel();

    private Page page;

    private Resource resource;

    @BeforeEach
    void setUp() throws Exception {
        page = context.create().page("/content/samplevida");
        resource = context.create().resource(page, "sample vida", "sling:resourceType", "vida/components/variant",
                "baseImg", "/content/base", "label", "label", "batteryImg", "/content/battery", "labelLink", "#",
                "productSku", "12345", "isLabelNewtab", false, "imageAltBase", "altBase", "imageTitleBase", "titleBase",
                "imageAltBattery", "altBattery", "imageTitleBattery", "titleBattery");

        variantSwitcherBeanModel = resource.adaptTo(VariantSwitcherBeanModel.class);

    }

    @Test
    void getGenericData() {
        assertEquals("/content/base", variantSwitcherBeanModel.getBaseImg());
        assertEquals("label", variantSwitcherBeanModel.getLabel());
        assertEquals("/content/battery", variantSwitcherBeanModel.getBatteryImg());
        assertEquals("#", variantSwitcherBeanModel.getLabelLink());
        assertEquals("12345", variantSwitcherBeanModel.getProductSku());
        assertEquals(false, variantSwitcherBeanModel.getIsLabelNewtab());
        assertEquals("altBase", variantSwitcherBeanModel.getImageAltBase());
        assertEquals("titleBase", variantSwitcherBeanModel.getImageTitleBase());
        assertEquals("altBattery", variantSwitcherBeanModel.getImageAltBattery());
        assertEquals("titleBattery", variantSwitcherBeanModel.getImageTitleBattery());
    }

}