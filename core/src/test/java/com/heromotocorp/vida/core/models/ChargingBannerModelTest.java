package com.heromotocorp.vida.core.models;


import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.IOException;
import java.util.Objects;

import org.apache.sling.api.resource.Resource;


@ExtendWith(AemContextExtension.class)
@Disabled
class ChargingBannerModelTest {
    private static final String TEST_ROOT_PAGE = "/content";
    private final AemContext context = new AemContext();

    private ChargingBannerModel chargingBannerModel;

    @BeforeEach
    void setUp() {
        context.load().json("/components/chargingbanner/chargingbanner-content.json", TEST_ROOT_PAGE);
        Resource resource = context.currentResource("/content/chargingbanner");
        assert resource != null;
        chargingBannerModel = Objects.requireNonNull(resource.adaptTo(ChargingBannerModel.class));

    }

    @Test
    void testGetJson() throws IOException {
        assertNotNull(chargingBannerModel.getJson());

    }

}