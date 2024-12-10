package com.heromotocorp.vida.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Objects;

import org.apache.sling.api.resource.Resource;

@ExtendWith(AemContextExtension.class)
class EVCategoryBannerModelTest {

    private static final String TEST_ROOT_PAGE = "/content";
    private final AemContext context = new AemContext();

    private EVCategoryBannerModel evCategoryBannerModel;

    @BeforeEach
    void setUp() {
        context.load().json("/components/evcategorybanner/evcategorybanner-content.json", TEST_ROOT_PAGE);
    }

    @Test
    void getJsonImage() throws IOException {
        Resource resource = context.currentResource("/content/evcategorybanner/image");
        evCategoryBannerModel = Objects.requireNonNull(resource.adaptTo(EVCategoryBannerModel.class));
        assertNotNull(evCategoryBannerModel.getJson());
    }

    @Test
    void getJsonVideo() throws IOException {
        Resource resource = context.currentResource("/content/evcategorybanner/video");
        evCategoryBannerModel = Objects.requireNonNull(resource.adaptTo(EVCategoryBannerModel.class));
        assertNotNull(evCategoryBannerModel.getJson());
    }
}