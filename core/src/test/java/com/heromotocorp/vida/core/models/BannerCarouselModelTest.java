/**
 * 
 */
package com.heromotocorp.vida.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.wcm.api.Page;
import com.google.gson.JsonArray;
import java.util.Objects;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

/**
 * JUnit test class for BannerCarouselModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class BannerCarouselModelTest {

	private final AemContext context = new AemContext();

	private BannerCarouselModel bannerCarouselModel = new BannerCarouselModel();

	private Page page;

	private Resource resource;

	private BannerCarouselModel bannerCarouselModel1 = new BannerCarouselModel();

	private Page page1;

	private Resource resource1;

	private BannerCarouselModel bannerCarouselModel2 = new BannerCarouselModel();

	private BannerCarouselBeanModel bannerCarouselBeanModel = new BannerCarouselBeanModel();
	
	private List<BannerCarouselBeanModel> listCarousel = new ArrayList<>();

	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		listCarousel.add(bannerCarouselBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/bannercarousel","exploreContentLabel", "exploreContentLabel",
				"items", listCarousel,"exploreContentUrl","exploreContentUrl","swipeContentLable","swipeContentLable");

		bannerCarouselModel = Objects.requireNonNull(resource.adaptTo(BannerCarouselModel.class));
		
		page1 = context.create().page("/content/samplevida");
		resource1 = context.create().resource(page1, "Sample vida", "sling:resourceType", "vida/components/bannercarousel",
				"items", null);

		bannerCarouselModel1 = Objects.requireNonNull(resource1.adaptTo(BannerCarouselModel.class));


	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BannerCarouselModel#getItems()}.
	 */
	@Test
	void testGetItems() {
		assertNotNull(bannerCarouselModel.getItems());
		
		bannerCarouselModel1.getItems();
	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.models.BannerCarouselModel#getItems()}.
	 *
	 */
	@Test
	void testGetExploreContentLabel() {
		assertEquals("exploreContentLabel", bannerCarouselModel.getExploreContentLabel());
	}

	@Test
	void testGetExploreContentUrl() {
		assertEquals("exploreContentUrl", bannerCarouselModel.getExploreContentUrl());
	}

	@Test
	void testGetSwipeContentLable() {
		assertEquals("swipeContentLable", bannerCarouselModel.getSwipeContentLable());
	}

	@Test
	void testGetJson() throws IOException {

		assertNotNull( bannerCarouselModel.getJson());
	}

}
