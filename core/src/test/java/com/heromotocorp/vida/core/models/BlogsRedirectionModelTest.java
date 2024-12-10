package com.heromotocorp.vida.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.apache.sling.api.resource.Resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.Objects;


@ExtendWith(AemContextExtension.class)
class BlogsRedirectionModelTest {

    private static final String TEST_ROOT_PAGE = "/content";
    private final AemContext context = new AemContext();

    private BlogsRedirectionModel blogsRedirectionModel;

    @BeforeEach
    void setUp() {
        context.load().json("/components/blogsredirection/blogsredirection-content.json", TEST_ROOT_PAGE);
        Resource resource = context.currentResource("/content/blogsredirection");
        blogsRedirectionModel = Objects.requireNonNull(resource.adaptTo(BlogsRedirectionModel.class));
    }

    @Test
    void getJson() throws IOException {
        assertNotNull(blogsRedirectionModel.getJson());
    }
}