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
class ServiceOfferCardBeanModelTest {

	private final AemContext context = new AemContext();

	private ServiceOfferCardBeanModel ServiceOfferCardBeanModel = new ServiceOfferCardBeanModel();

	private Page page;

	private Resource resource;


	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/bannercarousel",
				"title", "title", "itemType", "itemType", "label", "label",
				"imagepath", "imagepath", "imagealttext", "imagealttext", "knowMoreLink",
				"knowMoreLink", "videopath", "videopath","newTab",true,"desktopImage","desktopImage","mobileImage",
				"mobileImage","knowMoreLabel","knowMoreLabel", "imageTitle", "imageTitle");

		ServiceOfferCardBeanModel = resource.adaptTo(ServiceOfferCardBeanModel.class);
	}


	@Test
	void testGetItemType() {
		assertEquals("itemType", ServiceOfferCardBeanModel.getItemType());
	}


	@Test
	void testGetTitle() {
		assertEquals("title", ServiceOfferCardBeanModel.getTitle());
	}

	@Test
	void testGetLabel() {
		assertEquals("label", ServiceOfferCardBeanModel.getLabel());
	}

	@Test
	void testGetImagepath() {
		assertEquals("imagepath", ServiceOfferCardBeanModel.getImagepath());
	}


	@Test
	void testGetImagealttext() {
		assertEquals("imagealttext", ServiceOfferCardBeanModel.getImagealttext());
	}


	@Test
	void testGetKnowMoreLink() {
		assertEquals("knowMoreLink",ServiceOfferCardBeanModel.getKnowMoreLink());
	}


	@Test
	void testGetNewTab() {
		assertEquals(true, ServiceOfferCardBeanModel.getNewTab());
	}


	@Test
	void testGetVideopath() {
		assertEquals("videopath",ServiceOfferCardBeanModel.getVideopath());
	}

	@Test
	void testGetDesktopImage() {
		assertEquals("desktopImage",ServiceOfferCardBeanModel.getDesktopImage());
	}

	@Test
	void testGetMobileImage() {
		assertEquals("mobileImage",ServiceOfferCardBeanModel.getMobileImage());
	}

	@Test
	void testGetKnowMoreLabel() {
		assertEquals("knowMoreLabel",ServiceOfferCardBeanModel.getKnowMoreLabel());
	}

	@Test
	void testGetImageTitle() {
		assertEquals("imageTitle",ServiceOfferCardBeanModel.getImageTitle());
	}

}
