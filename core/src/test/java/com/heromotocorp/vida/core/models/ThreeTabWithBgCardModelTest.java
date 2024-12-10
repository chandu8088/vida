package com.heromotocorp.vida.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.sling.api.resource.Resource;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Objects;


@ExtendWith(AemContextExtension.class)
class ThreeTabWithBgCardModelTest {

    private static final String TEST_ROOT_PAGE = "/content";
    private final AemContext context = new AemContext();

    private ThreeTabWithBgCardModel threeTabWithBgCardModel;

    @BeforeEach
    void setUp() {
        context.load().json("/components/threetabwithbackgroundcard/threetabwithbackgroundcard-content.json", TEST_ROOT_PAGE);
        Resource resource = context.currentResource("/content/threetabwithbackgroundcard");
        threeTabWithBgCardModel = Objects.requireNonNull(resource.adaptTo(ThreeTabWithBgCardModel.class));
    }

    @Test
    void getJson() throws IOException {
        assertNotNull(threeTabWithBgCardModel.getJson());
    }
}