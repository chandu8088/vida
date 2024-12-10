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
 * JUnit test class for BannerCarouselBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class AboutVidaBeanModelTest {

	private final AemContext context = new AemContext();

	private Page page;

	private Resource resource;

	private AboutVidaInnerBeanModel aboutVidaInnerBeanModel = new AboutVidaInnerBeanModel();

	private List<AboutVidaInnerBeanModel> listCarousel = new ArrayList<>();

	private AboutVidaBeanModel aboutVidaBeanModel = new AboutVidaBeanModel();

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		listCarousel.add(aboutVidaInnerBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/bannercarousel",
				"tabCardContent", listCarousel, "tabContentTitle", "tabContentTitle",
				"tabContentDescription", "tabContentDescription","tabTitle", "tabTitle");

		aboutVidaBeanModel = resource.adaptTo(AboutVidaBeanModel.class);
	}



	@Test
	void testGetTabContentTitle() {
		assertEquals("tabContentTitle", aboutVidaBeanModel.getTabContentTitle());
	}


	@Test
	void testGetTabCardContent() {
		assertNotNull(aboutVidaBeanModel.getTabCardContent());
	}

	@Test
	void testGetTabContentDescription() {
		assertEquals("tabContentDescription", aboutVidaBeanModel.getTabContentDescription());
	}

	@Test
	void testGetTabTitle() {
		assertEquals("tabTitle", aboutVidaBeanModel.getTabTitle());
	}


}
