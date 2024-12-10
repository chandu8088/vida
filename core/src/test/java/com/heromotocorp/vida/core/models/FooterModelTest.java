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
import com.google.gson.JsonArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JUnit test class for FooterModelTest
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class FooterModelTest {

    private final AemContext context = new AemContext();

    private FooterModel footerModel = new FooterModel();

    private Page page;

    private Resource resource;

    private Page page1;

    private Resource resource1;

    private FooterBeanModel footerBeanModel = new FooterBeanModel();

    private GenericDataModel genericDataModel = new GenericDataModel();

    private List<FooterBeanModel> footerBean = new ArrayList<>();

    private List<GenericDataModel> genericDataModels = new ArrayList<>();


    @BeforeEach
    void setUp() throws Exception {

        footerBean.add(footerBeanModel);
        genericDataModels.add(genericDataModel);
        page = context.create().page("/content/vida");
        resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/vida-2.0/structure/footer", "footeritems", footerBean, "socialmediaitems", footerBean, "footerDescription", footerBean, "footerLogo", "footerLogo", "faqheading", "faqheading", "faqContent", "faqContent", "readMoreData", "readMoreData", "footerAddress", "footerAddress", "footerEmail", "footerEmail", "footerPhone", "footerPhone", "termsAndSeriviceText", "termsAndSeriviceText", "followUsText", "followUsText", "disclaimerText", "disclaimerText", "copyright", "copyright", "PrivacyPolicyText", "PrivacyPolicyText");

        footerModel = Objects.requireNonNull(resource.adaptTo(FooterModel.class));

    }

    @Test
    void testGetFooteritems() throws IOException {
        footerModel.getFooteritems();
        assertNotNull(footerBean);
    }


    @Test
    void testGetSocialmediaitems() throws IOException {
        footerModel.getSocialmediaitems();
        assertNotNull(footerBean);
    }

    @Test
    void testGetFooterDescription() throws IOException {
        footerModel.getFooterDescription();
        assertNotNull(footerBean);
    }

    @Test
    void testGetTermsPolicyItem() throws IOException {
        footerModel.getTermsPolicyItem();
        assertNotNull(genericDataModels);
    }

    @Test
    void testGetFaqheading() {
        assertEquals("faqheading", footerModel.getFaqheading());
    }

    @Test
    void testGetFaqContent() {
        assertEquals("faqContent", footerModel.getFaqContent());
    }

    @Test
    void testGetReadMoreData() {
        assertEquals("readMoreData", footerModel.getReadMoreData());
    }

    @Test
    void testGetFooterAddress() {
        assertEquals("footerAddress", footerModel.getFooterAddress());
    }

    @Test
    void testGetFooterEmail() {
        assertEquals("footerEmail", footerModel.getFooterEmail());
    }

    @Test
    void testGetFooterPhone() {
        assertEquals("footerPhone", footerModel.getFooterPhone());
    }


    @Test
    void testGetFollowUsText() {
        assertEquals("followUsText", footerModel.getFollowUsText());
    }


    @Test
    void testGetCopyright() {
        assertEquals("copyright", footerModel.getCopyright());
    }

    @Test
    void testGetJson() throws IOException {
     assertNotNull( footerModel.getJson());
    }


}
