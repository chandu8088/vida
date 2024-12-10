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
class CompareVidaModelTest {

	private final AemContext context = new AemContext();

	private Page page;

	private Resource resource;

	private CompareVidaModel compareVidaModel = new CompareVidaModel();

	private List<CompareVidaBeanModel> compareVidaBeanList = new ArrayList<>();

	private CompareVidaBeanModel compareVidaBeanModel = new CompareVidaBeanModel();

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		compareVidaBeanList.add(compareVidaBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType", "vida/components/vida-2.0/content/comparevidacomparevida",
				"selectedProduct", compareVidaBeanList, "productInfoList", compareVidaModel, "heading", "heading",
				"label", "label", "buttonLabel",
				"buttonLabel", "colorsAvailableText", "colorsAvailableText", "exShowRoomText", "exShowRoomText", "colorsText", "colorsText",
				"availableInText", "availableInText", "colorSwatchesIcon", "colorSwatchesIcon"
				,"learnMoreUrl","learnMoreUrl","showOnlyDifferenceText","showOnlyDifferenceText");

		compareVidaModel = Objects.requireNonNull(resource.adaptTo(CompareVidaModel.class));
	}

	@Test
	void testGetSelectedProduct() {
		assertNotNull(compareVidaModel.getSelectedProduct());
	}

	@Test
	void testGetProductInfoList() {
		assertNotNull(compareVidaModel.getProductInfoList());
	}

	@Test
	void testGetHeading() {
		assertEquals("heading",compareVidaModel.getHeading());
	}

	@Disabled
	@Test
	void testGetJson() throws IOException {
		assertNotNull( compareVidaModel.getJson());
	}

	@Test
	void testButtonLabel() {
		assertEquals("buttonLabel",compareVidaModel.getButtonLabel());
	}

	@Test
	void testGetLabel() {
		assertEquals("label",compareVidaModel.getLabel());
	}

	@Test
	void testGetColorsAvailableText() {
		assertEquals("colorsAvailableText",compareVidaModel.getColorsAvailableText());
	}

	@Test
	void testGetExShowRoomText() {
		assertEquals("exShowRoomText",compareVidaModel.getExShowRoomText());
	}

	@Test
	void testGetColorsText() {
		assertEquals("colorsText",compareVidaModel.getColorsText());
	}

	@Test
	void testGetAvailableInText() {
		assertEquals("availableInText",compareVidaModel.getAvailableInText());
	}

	@Test
	void testGetColorSwatchesIcon() {
		assertEquals("colorSwatchesIcon",compareVidaModel.getColorSwatchesIcon());
	}


	@Test
	void testGetShowOnlyDifferenceText() {
		assertEquals("showOnlyDifferenceText",compareVidaModel.getShowOnlyDifferenceText());
	}

}
