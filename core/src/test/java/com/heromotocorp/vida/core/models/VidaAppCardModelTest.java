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
class VidaAppCardModelTest {

    private static final String TEST_ROOT_PAGE = "/content";
    private final AemContext context = new AemContext();

    private VidaAppCardModel vidaAppCardModel;

    @BeforeEach
    void setUp() {
        context.load().json("/components/vidaappcard/vidaappcard-content.json", TEST_ROOT_PAGE);
        Resource resource = context.currentResource("/content/vidaappcard");
        vidaAppCardModel = Objects.requireNonNull(resource.adaptTo(VidaAppCardModel.class));
    }

    @Test
    void getJson() throws IOException {
        assertNotNull(vidaAppCardModel.getJson());
    }
}