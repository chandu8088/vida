package com.heromotocorp.vida.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AemContextExtension.class)
class ChargingLocatorFeatureDetailModelTest {

    private static final String TEST_ROOT_PAGE = "/content";
    private final AemContext context = new AemContext();

    private ChargingLocatorFeatureDetailModel chargingLocatorFeatureDetailModel;

    @BeforeEach
    void setUp() {
        context.load().json("/components/charginglocatorfeaturedetail/charginglocatorfeaturedetail-content.json", TEST_ROOT_PAGE);
        Resource resource = context.currentResource("/content/charginglocatorfeatu");
        assert resource != null;
        chargingLocatorFeatureDetailModel = Objects.requireNonNull(resource.adaptTo(ChargingLocatorFeatureDetailModel.class));

    }

    @Test
    void testGetJson() throws IOException {
        assertNotNull(chargingLocatorFeatureDetailModel.getJson());

    }

}