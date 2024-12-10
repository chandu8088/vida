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
 * JUnit test class for PrivacyPolicyContactInfoBeanModel
 */
@ExtendWith({ AemContextExtension.class})
class PrivacyPolicyContactInfoBeanModelTest {

	private final AemContext context = new AemContext();
	private static final String TEST_ROOT_PAGE = "/content";

	private PrivacyPolicyContactInfoBeanModel privacyPolicyContactInfoBeanModel ;

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		context.load().json("/components/privacypolicy/privacy-policy.json", TEST_ROOT_PAGE);
    Resource resource = context.currentResource("/content/sectionThree");
    privacyPolicyContactInfoBeanModel = Objects.requireNonNull(resource.adaptTo(PrivacyPolicyContactInfoBeanModel.class));
	}

	@Test
	void testGetContactInfo() throws IOException {
		assertNotNull( privacyPolicyContactInfoBeanModel.getContactInfo());
	}

	@Test
	void testGetTitle() throws IOException {
		assertEquals("Any Questions or Concerns?", privacyPolicyContactInfoBeanModel.getTitle());
	}

	@Test
	void testGetDescription() throws IOException {
		assertEquals("Our team is at your side with words and deeds.", privacyPolicyContactInfoBeanModel.getDescription());
	}

}
