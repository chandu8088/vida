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
class AboutVidaInnerBeanModelTest {

	private final AemContext context = new AemContext();

	private Page page;

	private Resource resource;

	private AboutVidaInnerBeanModel aboutVidaInnerBeanModel = new AboutVidaInnerBeanModel();



	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/bannercarousel",
				"cardTitle", "cardTitle", "cardDescription", "cardDescription",
				"cardNavLink", "cardNavLink","cardDesktopImg", "cardDesktopImg"
				,"imagealttext", "imagealttext"
				,"cardMobileImg", "cardMobileImg"
				,"cardVideo", "cardVideo"
				,"itemType", "video"
				,"isVideo", true
				,"newTab", true
				,"imageTitle", "imageTitle");

		aboutVidaInnerBeanModel = resource.adaptTo(AboutVidaInnerBeanModel.class);
	}



	@Test
	void testGetCardTitle() {
		assertEquals("cardTitle", aboutVidaInnerBeanModel.getCardTitle());
	}


	@Test
	void testGetCardDescription() {
		assertEquals("cardDescription", aboutVidaInnerBeanModel.getCardDescription());
	}

	@Test
	void testGetIsVideo() {
		assertEquals(true, aboutVidaInnerBeanModel.getIsVideo());
	}

	@Test
	void testIsNewTab() {
		assertEquals(true, aboutVidaInnerBeanModel.isNewTab());
	}

	@Test
	void testGetCardDesktopImg() {
		assertEquals("cardDesktopImg", aboutVidaInnerBeanModel.getCardDesktopImg());
	}

	@Test
	void testGetImagealttext() {
		assertEquals("imagealttext", aboutVidaInnerBeanModel.getImagealttext());
	}

	@Test
	void testGetCardMobileImg() {
		assertEquals("cardMobileImg", aboutVidaInnerBeanModel.getCardMobileImg());
	}

	@Test
	void testGetCardVideo() {
		assertEquals("cardVideo", aboutVidaInnerBeanModel.getCardVideo());
	}

	@Test
	void testGetCardNavLink() {
		assertEquals("cardNavLink", aboutVidaInnerBeanModel.getCardNavLink());
	}

	@Test
	void testGetImageTitle() {
		assertEquals("imageTitle", aboutVidaInnerBeanModel.getImageTitle());
	}


}
