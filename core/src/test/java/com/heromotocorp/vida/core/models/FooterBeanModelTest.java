/**
 *
 */
package com.heromotocorp.vida.core.models;

import com.day.cq.wcm.api.Page;
import com.heromotocorp.vida.core.utils.CommonUtils;
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
class FooterBeanModelTest {

    private final AemContext context = new AemContext();


    private Page page;

    private Resource resource;

    private FooterBeanModel footerBeanModel = new FooterBeanModel();

    private GenericDataModel genericDataModel = new GenericDataModel();

    private List<GenericDataModel> genericDataModels = new ArrayList<>();




    @BeforeEach
    void setUp() throws Exception {
        genericDataModels.add(genericDataModel);
        page = context.create().page("/content/vida");
        resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/vida-2.0/structure/footer",
                "header", "header", "image", "image",
                "link", "socialmediaurl", "content", "content",
                "title", "title","imageAltText","imageAltText","newTab",true);
        footerBeanModel = resource.adaptTo(FooterBeanModel.class);

    }

    @Test
    void testGetHeader() {
        assertEquals("header", footerBeanModel.getHeader());
    }

    @Test
    void testGetLink() {
        assertEquals("socialmediaurl", footerBeanModel.getLink());
    }

    @Test
    void testGetContent() {
        assertEquals("content", footerBeanModel.getContent());
    }

    @Test
    void testGetTitle() {
        assertEquals("title", footerBeanModel.getTitle());
    }

    @Test
    void testGetImage() {
        assertEquals("image", footerBeanModel.getImage());
    }

    @Test
    void testGetImageAltText() {
        assertEquals("imageAltText", footerBeanModel.getImageAltText());
    }

    @Test
    void testGetContentItem() throws IOException {
        footerBeanModel.getContentItem();
        assertNotNull(genericDataModels);
    }

    @Test
    void testGetNewTab() throws IOException {
        assertEquals(true, footerBeanModel.getNewTab());
    }


}
