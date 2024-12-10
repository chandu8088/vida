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
class AboutVidaModelTest {

	private final AemContext context = new AemContext();

	private Page page;

	private Resource resource;

	private AboutVidaModel aboutVidaModel = new AboutVidaModel();

	private List<AboutVidaBeanModel> listCarousel = new ArrayList<>();

	private AboutVidaBeanModel aboutVidaBeanModel = new AboutVidaBeanModel();

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		listCarousel.add(aboutVidaBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/bannercarousel",
				"aboutVidaTabContent", listCarousel, "aboutVidaTitle", "aboutVidaTitle",
				"aboutVidaText", "aboutVidaText");

		aboutVidaModel = Objects.requireNonNull(resource.adaptTo(AboutVidaModel.class));
	}



	@Test
	void testGetAboutVidaTitle() {
		assertEquals("aboutVidaTitle", aboutVidaModel.getAboutVidaTitle());
	}


	@Test
	void testGetAboutVidaTabContent() {
		assertNotNull(aboutVidaModel.getAboutVidaTabContent());
	}


	@Test
	void testGetAboutVidaText() {
		assertEquals("aboutVidaText", aboutVidaModel.getAboutVidaText());
	}

	@Test
	void testGetJson() throws IOException {
		assertNotNull( aboutVidaModel.getJson());
	}

}
