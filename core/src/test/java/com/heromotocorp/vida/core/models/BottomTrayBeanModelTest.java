/**
 *
 */
package com.heromotocorp.vida.core.models;

import com.day.cq.wcm.api.Page;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JUnit test class for FooterModelTest
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class BottomTrayBeanModelTest {

    private final AemContext context = new AemContext();

    private Page page;

    private Resource resource;


    private BottomTrayBeanModel bottomTrayBeanModel = new BottomTrayBeanModel();



    @BeforeEach
    void setUp() throws Exception {

        page = context.create().page("/content/vida");
        resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/vida-2.0/structure/footer",
                "title", "title", "icon",
                "icon", "bottomTrayBgImg", "bottomTrayBgImg"
                , "cardBgImg", "cardBgImg"
                , "navLink", "navLink"
                , "description", "description" ,"newTab",true);

        bottomTrayBeanModel = resource.adaptTo(BottomTrayBeanModel.class);

    }


    @Test
    void testGetIcon() {
        assertEquals("icon", bottomTrayBeanModel.getIcon());
    }

    @Test
    void testGetCardBgImg() {
        assertEquals("cardBgImg", bottomTrayBeanModel.getCardBgImg());
    }

    @Test
    void testGetNavLink() {
        assertEquals("navLink", bottomTrayBeanModel.getNavLink());
    }

    @Test
    void testGetDescription() {
        assertEquals("description", bottomTrayBeanModel.getDescription());
    }

    @Test
    void testGetTitle() {
        assertEquals("title", bottomTrayBeanModel.getTitle());
    }

    @Test
    void testGetNewTab() {
        assertEquals(true, bottomTrayBeanModel.getNewTab());
    }


}
