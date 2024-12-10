/**
 * 
 */
package com.heromotocorp.vida.core.models;

import com.day.cq.wcm.api.Page;
import com.heromotocorp.vida.core.utils.CommonUtils;
import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JUnit test class for BannerCarouselBeanModel
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class CancelBookingModelTest {

	private final AemContext context = new AemContext();

	private Page page;

	private Resource resource;

	private CancelBookingModel cancelBookingModel = new CancelBookingModel();

	private List<CompareVidaBeanModel> compareVidaBeanModels = new ArrayList<>();

	private CompareVidaBeanModel compareVidaBeanModel = new CompareVidaBeanModel();

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		compareVidaBeanModels.add(compareVidaBeanModel);
		page = context.create().page("/content/vida");
		resource = context.create().resource(page, "vida", "sling:resourceType",
				"vida/components/vida-2.0/content/cancelbooking",
				"cancelBookingTitle", "cancelBookingTitle", "cancelBookingBannerImg",
				"cancelBookingBannerImg", "bikeInfoSubText" ,
				"bikeInfoSubText", "cancelBookingBikeImg" ,
				"cancelBookingBikeImg", "cancelBookingDeliveryText",
				"cancelBookingDeliveryText", "cancelBookingPayableText",
				"cancelBookingPayableText", "deliveryBikeIcon",
				"deliveryBikeIcon", "drawerTitle",
				"drawerTitle", "cancelBtnLabel",
				"cancelBtnLabel", "confirmBtnLabel",
				"confirmBtnLabel", "cancelNavLink",
				"cancelNavLink", "cancelNewTab",
				true, "confirmNavLink",
				"confirmNavLink", "confirmNewTab",true,"cancelBookingAddOnContent",compareVidaBeanModels);

		cancelBookingModel = Objects.requireNonNull(resource.adaptTo(CancelBookingModel.class));
	}



	@Test
	void testGetCancelBookingAddOnContent() {
		assertNotNull(cancelBookingModel.getCancelBookingAddOnContent());
	}

	@Test
	void testGetCancelBookingTitle() {
		assertEquals("cancelBookingTitle", cancelBookingModel.getCancelBookingTitle());
	}

	@Test
	void testGetCancelBookingBannerImg() {
		assertEquals("cancelBookingBannerImg", cancelBookingModel.getCancelBookingBannerImg());
	}

	@Test
	void testGetBikeInfoSubText() {
		assertEquals("bikeInfoSubText", cancelBookingModel.getBikeInfoSubText());
	}

	@Test
	void testGetCancelBookingDeliveryText() {
		assertEquals("cancelBookingDeliveryText", cancelBookingModel.getCancelBookingDeliveryText());
	}

	@Test
	void testGetCancelBookingPayableText() {
		assertEquals("cancelBookingPayableText", cancelBookingModel.getCancelBookingPayableText());
	}

	@Test
	void testGetDeliveryBikeIcon() {
		assertEquals("deliveryBikeIcon", cancelBookingModel.getDeliveryBikeIcon());
	}

	@Test
	void testGetDrawerTitle() {
		assertEquals("drawerTitle", cancelBookingModel.getDrawerTitle());
	}

	@Test
	void testGetCancelBtnLabel() {
		assertEquals("cancelBtnLabel", cancelBookingModel.getCancelBtnLabel());
	}

	@Test
	void testGetConfirmBtnLabel() {
		assertEquals("confirmBtnLabel", cancelBookingModel.getConfirmBtnLabel());
	}

	@Test
	void testGetCancelNavLink() {
		assertEquals("cancelNavLink", cancelBookingModel.getCancelNavLink());
	}

	@Test
	void testIsCancelNewTab() {
		assertEquals(true, cancelBookingModel.isCancelNewTab());
	}

	@Test
	void testGetConfirmNavLink() {
		assertEquals("confirmNavLink", cancelBookingModel.getConfirmNavLink());
	}

	@Test
	void testIsConfirmNewTab() {
		assertEquals(true, cancelBookingModel.isConfirmNewTab());
	}

	@Test
	void testGetCancelBookingBikeImg() {
		assertEquals("cancelBookingBikeImg", cancelBookingModel.getCancelBookingBikeImg());
	}

	@Test
	void testGetJson() throws IOException {
		assertNotNull( cancelBookingModel.getJson());
	}

}
