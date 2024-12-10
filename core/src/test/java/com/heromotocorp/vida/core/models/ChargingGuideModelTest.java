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
 * JUnit test class for BannerCarouselBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ChargingGuideModelTest {

	private final AemContext context = new AemContext();

	private Page page;

	private Resource resource;

	private ChargingGuideBeanModel chargingGuideBeanModel = new ChargingGuideBeanModel();

	private List<ChargingGuideBeanModel> chargingGuideBeanModels = new ArrayList<>();

	private ChargingGuideModel chargingGuideModel = new ChargingGuideModel();

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		chargingGuideBeanModels.add(chargingGuideBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/bannercarousel",
				"ChargingGuideCardContent", chargingGuideBeanModels,"header","header");

		chargingGuideModel = Objects.requireNonNull(resource.adaptTo(ChargingGuideModel.class));
	}

	@Test
	void testGetChargingGuideCardContent() {
		assertNotNull(chargingGuideModel.getChargingGuideCardContent());
	}

	@Test
	void testGetHeader() {
		assertEquals("header",chargingGuideModel.getHeader());
	}

	@Test
	void testGetJson() throws IOException {
		assertNotNull( chargingGuideModel.getJson());
	}

}
