package com.heromotocorp.vida.core.models;

import com.heromotocorp.vida.core.config.GlobalConfig;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.Objects;

import org.apache.sling.api.resource.Resource;

@ExtendWith(AemContextExtension.class)
class HeaderModelTest {

    private static final String TEST_ROOT_PAGE = "/content";
    private final AemContext context = new AemContext();

    private HeaderModel headerModel;
    
    @BeforeEach
    void setUp() {
        context.load().json("/components/header/header-content.json", TEST_ROOT_PAGE);
        Resource resource = context.currentResource("/content/header");
        headerModel = Objects.requireNonNull(resource.adaptTo(HeaderModel.class));

    }

    @Test
    void testGetJson() throws IOException, NoSuchFieldException {
        context.getService(GlobalConfig.class);
        GlobalConfig globalConfig;
        globalConfig = mock(GlobalConfig.class);
        PrivateAccessor.setField(headerModel, "globalConfig", globalConfig);
        assertNotNull(headerModel.getJson());

    }
}
