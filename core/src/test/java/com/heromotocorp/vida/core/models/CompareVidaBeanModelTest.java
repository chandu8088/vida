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
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for BannerCarouselBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class CompareVidaBeanModelTest {

	private final AemContext context = new AemContext();

	private Page page;

	private Resource resource;


	private CompareVidaBeanModel compareVidaBeanModel = new CompareVidaBeanModel();

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/vida-2.0/content/comparevidacomparevida",
				"productSku", "productSku","baseImg","/baseImg", "imageAlt", "imageAlt", "imageTitle", "imageTitle");

		compareVidaBeanModel = Objects.requireNonNull(resource.adaptTo(CompareVidaBeanModel.class));
	}

	@Test
	void testGetProductSku() {
		assertEquals("productSku",compareVidaBeanModel.getProductSku());
	}

	@Test
	void testGetImageAlt() {
		assertEquals("imageAlt",compareVidaBeanModel.getImageAlt());
	}

	@Test
	void testGetImageTitle() {
		assertEquals("imageTitle",compareVidaBeanModel.getImageTitle());
	}
	
}
