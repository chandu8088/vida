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
class ServiceOfferCardModelTest {

	private final AemContext context = new AemContext();

	private ServiceOfferCardModel serviceOfferCardModel = new ServiceOfferCardModel();

	private Page page;

	private Resource resource;

	private ServiceOfferCardBeanModel serviceOfferCardBeanModel = new ServiceOfferCardBeanModel();

	private List<ServiceOfferCardBeanModel> listCarousel = new ArrayList<>();

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		listCarousel.add(serviceOfferCardBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/bannercarousel",
				"offerCardItems", listCarousel, "serviceItems", listCarousel, "variation", "variationone",
				"title", "title", "subheading", "subheading", "helperText",
				"helperText", "image", "image","darkTheme",true);

		serviceOfferCardModel = Objects.requireNonNull(resource.adaptTo(ServiceOfferCardModel.class));
	}


	@Test
	void testGetVariation() {
		assertEquals("variationone", serviceOfferCardModel.getVariation());
	}


	@Test
	void testGetTitle() {
		assertEquals("title", serviceOfferCardModel.getTitle());
	}

	@Test
	void testGetSubheading() {
		assertEquals("subheading", serviceOfferCardModel.getSubheading());
	}

	@Test
	void testGetHelperText() {
		assertEquals("helperText", serviceOfferCardModel.getHelperText());
	}


	@Test
	void testGetImage() {
		assertEquals("image", serviceOfferCardModel.getImage());
	}


	@Test
	void testGetServiceItems() {
		assertNotNull(serviceOfferCardModel.getServiceItems());
	}


	@Test
	void testGetDarkTheme() {
		assertEquals(true, serviceOfferCardModel.getDarkTheme());
	}

	@Test
	void testGetJson() throws IOException {
		assertNotNull( serviceOfferCardModel.getJson());
	}


	@Test
	void testGetServiceJson() throws IOException {
		assertNotNull( serviceOfferCardModel.getServiceJson());
	}

	@Test
	void testGetOfferCardItems() {
		assertNotNull(serviceOfferCardModel.getOfferCardItems());
	}

}
