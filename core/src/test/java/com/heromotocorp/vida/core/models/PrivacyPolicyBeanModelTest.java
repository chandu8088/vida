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
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JUnit test class for PrivacyPolicyBeanModel
 */
@ExtendWith({ AemContextExtension.class})
class PrivacyPolicyBeanModelTest {

	private final AemContext context = new AemContext();
	private static final String TEST_ROOT_PAGE = "/content";

	private PrivacyPolicyBeanModel privacyPolicyBeanModel ;

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		context.load().json("/components/privacypolicy/privacy-policy.json", TEST_ROOT_PAGE);
    Resource resource = context.currentResource("/content/sectionOne");
    privacyPolicyBeanModel = Objects.requireNonNull(resource.adaptTo(PrivacyPolicyBeanModel.class));
	}

	@Test
	void testGetPrivacyPolicies() throws IOException {
		assertNotNull( privacyPolicyBeanModel.getPrivacyPolicies());
	}

	@Test
	void testGetPrivacyPolicyTitle() throws IOException {
		assertEquals("Privacy Policy", privacyPolicyBeanModel.getPrivacyPolicyTitle());
	}

	@Test
	void testGetPrivacyPolicyLastUpdatedDate() throws IOException {
		assertEquals("Updated on 20 Sep 2023", privacyPolicyBeanModel.getPrivacyPolicyLastUpdatedDate());
	}

}
