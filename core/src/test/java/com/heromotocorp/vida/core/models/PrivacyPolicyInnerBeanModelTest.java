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
 * JUnit test class for PrivacyPolicyBeanModel
 */
@ExtendWith({ AemContextExtension.class})
class PrivacyPolicyInnerBeanModelTest {

	private final AemContext context = new AemContext();
	private static final String TEST_ROOT_PAGE = "/content";

	private PrivacyPolicyInnerBeanModel privacyPolicyInnerBeanModel ;

	/**
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		context.load().json("/components/privacypolicy/privacy-policy.json", TEST_ROOT_PAGE);
    Resource resource = context.currentResource("/content/sectionOne/privacyPolicies/item0");
    privacyPolicyInnerBeanModel = Objects.requireNonNull(resource.adaptTo(PrivacyPolicyInnerBeanModel.class));
	}

	@Test
	void testGetContentTitle() throws IOException {
		assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In porta magna ex quis fringilla risus urna, sed sollicitudin sapien facilisis vel.", privacyPolicyInnerBeanModel.getContentTitle());
	}

	@Test
	void testGetContentDescription() throws IOException {
		assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit. In porta magna ex, sed sollicitudin sapien facilisis vel. Sed efficitur molestie ante, rutrum laoreet libero molestie vitae. Maecenas eleifend, purus ac fringilla eleifend, magna tellus elementum sapien, sit amet dapibus felis massa eget nisl. Ut sagittis, nibh quis sagittis mollis, erat ligula tempus tortor, quis fringilla risus urna vel lectus. Aenean auctor, massa id fringilla egestas, orci nisi iaculis tortor, sit amet tempus velit ligula nec ipsum. Pellentesque nisi metus, congue quis libero sit amet, hendrerit tempus neque. Morbi sodales eros id blandit volutpat. Sed in elit eu elit molestie venenatis. Ut sed hendrerit elit, feugiat gravida justo. Pellentesque varius tellus massa, et sollicitudin diam ultricies id. Sed elementum viverra felis sed varius. Nunc non congue mauris. Morbi sodales eros id blandit volutpat. Duis a pharetra eros.", privacyPolicyInnerBeanModel.getContentDescription());
	}

}
