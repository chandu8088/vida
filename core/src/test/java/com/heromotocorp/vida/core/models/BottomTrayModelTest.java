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
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JUnit test class for FooterModelTest
 */
@ExtendWith({AemContextExtension.class, MockitoExtension.class})
class BottomTrayModelTest {

    private final AemContext context = new AemContext();

    private BottomTrayModel bottomTrayModel = new BottomTrayModel();

    private Page page;

    private Resource resource;


    private BottomTrayBeanModel bottomTrayBeanModel = new BottomTrayBeanModel();

    private List<BottomTrayBeanModel> bottomTrayBean = new ArrayList<>();


    @BeforeEach
    void setUp() throws Exception {

        bottomTrayBean.add(bottomTrayBeanModel);
        page = context.create().page("/content/vida");
        resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/vida-2.0/structure/footer",
                "trayItems", bottomTrayBean, "variation",
                "variation", "bottomTrayBgImg", "bottomTrayBgImg",
                "json", "{\"bottomTrayCardContent\":[{\"title\":\"TRY\",\"description\":\"TRY dESCRIPTION\",\"icon\":\"/content/dam/vida/try_card_icon.png\",\"cardBgImg\":\"/content/dam/vida/bottom_tray_card_bg.png\",\"navLink\":\"#\"},{\"title\":\"lOVE\",\"description\":\"lOVE dESCRIPTION\",\"icon\":\"/content/dam/vida/love_card_icon.png\",\"cardBgImg\":\"/content/dam/vida/bottom_tray_card_bg.png\",\"navLink\":\"#\"},{\"title\":\"bUY\",\"description\":\"bUY dESCRIPTION\",\"icon\":\"/content/dam/vida/buy_card_icon.png\",\"cardBgImg\":\"/content/dam/vida/bottom_tray_card_bg.png\",\"navLink\":\"#\"}],\"bottomTrayBgImg\":\"/content/dam/vida/bottom_tray_bg.png\",\"isVariant1\":true}");

        bottomTrayModel = Objects.requireNonNull(resource.adaptTo(BottomTrayModel.class));

    }

    @Test
    void testGetTrayItems() throws IOException {
        bottomTrayModel.getTrayItems();
        assertNotNull(bottomTrayBean);
    }


    @Test
    void testGetBottomTrayBgImg() {
        assertEquals("bottomTrayBgImg", bottomTrayModel.getBottomTrayBgImg());
    }

    @Test
    void testGetVariation() {
        assertEquals("variation", bottomTrayModel.getVariation());
    }


    @Test
    void testGetJson() throws IOException {
      assertNotNull( bottomTrayModel.getJson());
    }


}
