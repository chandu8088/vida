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
 * JUnit test class for PrivacyPolicyModel
 */
@ExtendWith({ AemContextExtension.class})
class PrivacyPolicyModelTest {

	private final AemContext context = new AemContext();
	private static final String TEST_ROOT_PAGE = "/content";

	private PrivacyPolicyModel privacyPolicyModel ;

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		context.load().json("/components/privacypolicy/privacy-policy.json", TEST_ROOT_PAGE);
    Resource resource = context.currentResource("/content");
    privacyPolicyModel = Objects.requireNonNull(resource.adaptTo(PrivacyPolicyModel.class));
	}

	@Test
	void testGetJson() throws IOException {
		assertNotNull( privacyPolicyModel.getJson());
	}

	@Test
	void testIsTermsAndConditions() throws IOException {
		assertEquals( true,privacyPolicyModel.isTermsAndConditions());
	}

}
