/**
 * 
 */
package com.heromotocorp.vida.core.models;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit test class for PrivacyPolicyContactInfoInnerBeanModel
 */
@ExtendWith({ AemContextExtension.class})
class PrivacyPolicyContactInfoInnerBeanModelTest {

	private final AemContext context = new AemContext();
	private static final String TEST_ROOT_PAGE = "/content";

	private PrivacyPolicyContactInfoInnerBeanModel privacyPolicyContactInfoInnerBeanModel ;

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		context.load().json("/components/privacypolicy/privacy-policy.json", TEST_ROOT_PAGE);
    Resource resource = context.currentResource("/content/sectionThree/contactInfo/item0");
    privacyPolicyContactInfoInnerBeanModel = Objects.requireNonNull(resource.adaptTo(PrivacyPolicyContactInfoInnerBeanModel.class));
	}

	@Test
	void testGetText() throws IOException {
		assertEquals("+91 57868 39822", privacyPolicyContactInfoInnerBeanModel.getText());
	}

	@Test
	void testGetIcon() throws IOException {
		assertEquals("/content/dam/vida2-0/global/explore_icon.png", privacyPolicyContactInfoInnerBeanModel.getIcon());
	}

}
