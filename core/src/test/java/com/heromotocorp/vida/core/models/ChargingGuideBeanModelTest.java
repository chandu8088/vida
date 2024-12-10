/**
 * 
 */
package com.heromotocorp.vida.core.models;

import com.day.cq.wcm.api.Page;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for BannerCarouselBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ChargingGuideBeanModelTest {

	private final AemContext context = new AemContext();

	private Page page;

	private Resource resource;

	private ChargingGuideBeanModel ChargingGuideBeanModel = new ChargingGuideBeanModel();


	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/bannercarousel",
				"mobileImg", "mobileImg",
				"desktopImg", "desktopImg",
				"title", "title",
				"description", "description",
				"offerLabel", "offerLabel",
				"imagealttext", "imagealttext",
				"imageTitle", "imageTitle"
				);

		ChargingGuideBeanModel = resource.adaptTo(ChargingGuideBeanModel.class);
	}

	@Test
	void testGetMobileImg() {
		assertEquals("mobileImg",ChargingGuideBeanModel.getMobileImg());
	}

	@Test
	void testGetDesktopImg() {
		assertEquals("desktopImg", ChargingGuideBeanModel.getDesktopImg());
	}

	@Test
	void testGetTitle() {
		assertEquals("title", ChargingGuideBeanModel.getTitle());
	}

	@Test
	void testGetDescription() {
		assertEquals("description", ChargingGuideBeanModel.getDescription());
	}


	@Test
	void testGetOfferLabel() {
		assertEquals("offerLabel", ChargingGuideBeanModel.getOfferLabel());
	}


	@Test
	void testGetImagealttext() {
		assertEquals("imagealttext", ChargingGuideBeanModel.getImagealttext());
	}

	@Test
	void testGetImageTitle() {
		assertEquals("imageTitle", ChargingGuideBeanModel.getImageTitle());
	}


}
