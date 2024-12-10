package com.heromotocorp.vida.core.models;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.Rendition;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.factory.ModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class VariantSwitcherModelTest {
    @InjectMocks
    private VariantSwitcherModel variantSwitcherModel;

    @Mock
    private ResourceResolver resourceResolver;

    @Mock
    private Resource assetResource;

    @Mock
    private Asset asset;

    @Mock
    private Rendition original;

    @Mock
    private ModelFactory modelFactory;

    @Mock
    private Resource page1;

    private List<VariantSwitcherBeanModel> productInfo = new ArrayList<>();

    VariantSwitcherBeanModel variantSwitcherBeanModel = new VariantSwitcherBeanModel();

    private final AemContext context = new AemContext();
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        context.currentResource(page1);
        context.registerService(ModelFactory.class, modelFactory);

    }

    private final String text = "{\r\n" + "	\"items\": [{\r\n" + "		\"name\": \"V1 PRO\",\r\n"
            + "		\"sku\": \"V1PRASBRCEL\",\r\n" + "		\"sf_id\": \"a1Gp0000000FaIzEAK\",\r\n"
            + "		\"__typename\": \"ConfigurableProduct\",\r\n" + "		\"variants\": [{\r\n"
            + "			\"__typename\": \"SimpleProduct\",\r\n"
            + "			\"name\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
            + "			\"sku\": \"V1PRASBRCELMWT\",\r\n" + "			\"range\": \"165 km\",\r\n"
            + "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.94 sec\",\r\n"
            + "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"White\",\r\n"
            + "			\"description\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n"
            + "			\"weight\": \"115.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
            + "			\"sf_id\": \"a1mp0000000EsOQAA0\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
            + "		}, {\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
            + "			\"name\": \"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\r\n"
            + "			\"sku\": \"V1PRASBRCELMSR\",\r\n" + "			\"range\": \"165 km\",\r\n"
            + "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.5 sec\",\r\n"
            + "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Red\",\r\n"
            + "			\"description\": \"VIDA V1 PRO SC 8P RMV MAT SPORTS RED\",\r\n"
            + "			\"weight\": \"275.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
            + "			\"sf_id\": \"a1mp0000000EsORAA0\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
            + "		}, {\r\n" + "			\"__typename\": \"SimpleProduct\",\r\n"
            + "			\"name\": \"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\r\n"
            + "			\"sku\": \"V1PRASBRCELMAO\",\r\n" + "			\"range\": \"165 km\",\r\n"
            + "			\"charging_time\": \"65 min\",\r\n" + "			\"accelerator\": \"3.5 sec\",\r\n"
            + "			\"top_speed\": \"80 kmph\",\r\n" + "			\"color\": \"Orange\",\r\n"
            + "			\"description\": \"VIDA V1 PRO SC 8P RMV MAT ABRAX ORANGE\",\r\n"
            + "			\"weight\": \"275.0\",\r\n" + "			\"ridingModes\": \"Eco, Ride, Sports, Custom\",\r\n"
            + "			\"sf_id\": \"a1mp0000000EsOTAA0\",\r\n" + "			\"seatingType\": \"3.94 kWh\"\r\n"
            + "		}]\r\n" + "	}]\r\n" + "}";

    @Test
    void testGetJson() throws IOException, NoSuchFieldException {
        InputStream stream = new ByteArrayInputStream(text.getBytes());
        lenient().when(resourceResolver.getResource("/content/dam/vida/config/product-master.json"))
                .thenReturn(assetResource);
        lenient().when(assetResource.adaptTo(Asset.class)).thenReturn(asset);

        lenient().when(asset.getOriginal()).thenReturn(original);
        lenient().when(original.adaptTo(InputStream.class)).thenReturn(stream);
        productInfo.add(variantSwitcherBeanModel);
        PrivateAccessor.setField(variantSwitcherModel, "productInfo", productInfo);
        assertNotNull(variantSwitcherModel.getJson());

    }

}