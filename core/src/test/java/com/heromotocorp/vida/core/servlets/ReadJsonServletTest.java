/**
 * 
 */
package com.heromotocorp.vida.core.servlets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.jcr.Session;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.day.cq.dam.api.Asset;
import com.day.cq.dam.api.AssetManager;
import com.day.cq.dam.api.Rendition;
import com.heromotocorp.vida.core.config.ClientConfig;
import com.heromotocorp.vida.core.config.GlobalConfig;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;
import junitx.util.PrivateAccessor;

/**
 * JUnit test class for ReadJsonServlet
 */
@ExtendWith({ AemContextExtension.class, MockitoExtension.class })
class ReadJsonServletTest {

	private final AemContext context = new AemContext();

	private ReadJsonServlet readJsonServlet = new ReadJsonServlet();

	private GlobalConfig globalConfig;

	private ClientConfig clientConfig;

	private SlingHttpServletRequest req;

	private SlingHttpServletResponse resp;

	private PrintWriter writer;

	@Mock
	private Resource assetResource;

	@Mock
	private Asset asset;

	@Mock
	private AssetManager assetManager;

	@Mock
	private Session session;

	@Mock
	private ResourceResolver resolver;

	@Mock
	private Rendition original;

	private String text = "[\r\n" + "{\r\n" + "\"item_sf_id\": \"a1Gp0000000FaIzEAK\",\r\n"
			+ "\"item_sku\": \"V1PRASBRCEL\",\r\n" + "\"variant_sf_id\": \"a1mp0000000EsOQAA0\",\r\n"
			+ "\"variant_name\": \"VIDA V1 PRO SC 8P RMV MAT PEARL WHITE\",\r\n" + "\"item_name\": \"V1 PRO\",\r\n"
			+ "\"city_state_id\": \"NEW DELHI~DELHI~INDIA\",\r\n" + "\"variant_sku\": \"V1PRASBRCELMWT\",\r\n"
			+ "\"onRoadPrice\": \"139900\",\r\n" + "\"exShowRoomPrice\": \"139900\",\r\n" + "\"price\": \"199000\",\r\n"
			+ "\"minLeaseEMI\": \"\",\r\n" + "\"minLeaseDownPayment\": \"\",\r\n" + "\"minLoanDonpayment\": \"\",\r\n"
			+ "\"minLoanEMI\": \"\",\r\n" + "\"leaseOfferRL\": \"\",\r\n" + "\"loanOfferURL\": \"\",\r\n"
			+ "\"effectivePrice\": \"119900\",\r\n" + "\"portablechargerPrice\": \"20000\",\r\n"
			+ "\"fame2IncentivePrice\": \"-60000\",\r\n" + "\"stateSubsidyPrice\": \"-20000\"\r\n" + "}]";

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		writer = Mockito.mock(PrintWriter.class);
		req = Mockito.mock(SlingHttpServletRequest.class);
		resp = Mockito.mock(SlingHttpServletResponse.class);

		globalConfig = context.getService(GlobalConfig.class);
		globalConfig = mock(GlobalConfig.class);
		PrivateAccessor.setField(readJsonServlet, "globalConfig", globalConfig);

		clientConfig = context.getService(ClientConfig.class);
		clientConfig = mock(ClientConfig.class);
		PrivateAccessor.setField(readJsonServlet, "clientConfig", clientConfig);

	}

	/**
	 * Test method for
	 * {@link com.heromotocorp.vida.core.servlets.ReadJsonServlet#doGet(org.apache.sling.api.SlingHttpServletRequest, org.apache.sling.api.SlingHttpServletResponse)}.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	@Test
	void testDoGetSlingHttpServletRequestSlingHttpServletResponse() throws ServletException, IOException {
		InputStream stream = new ByteArrayInputStream(text.getBytes());
		Mockito.when(globalConfig.productBranchesUrl()).thenReturn("productBranchesUrl");
		Mockito.when(globalConfig.productListUrl()).thenReturn("productListUrl");
		Mockito.when(globalConfig.productPriceUrl()).thenReturn("productPriceUrl");
		Mockito.when(globalConfig.storeDetailsUrl()).thenReturn("storeDetailsUrl");
		Mockito.when(req.getPathInfo()).thenReturn("/content/vida.city-master-json.json");
		Mockito.when(resp.getWriter()).thenReturn(writer);
		Mockito.when(req.getResourceResolver()).thenReturn(resolver);
		Mockito.when(resolver.getResource(Mockito.anyString())).thenReturn(assetResource);
		Mockito.when(assetResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getOriginal()).thenReturn(original);
		Mockito.when(original.adaptTo(InputStream.class)).thenReturn(stream);
		readJsonServlet.doGet(req, resp);

		Mockito.when(req.getPathInfo()).thenReturn("/content/vida.product-master-json.json");
		readJsonServlet.doGet(req, resp);

		Mockito.when(req.getPathInfo()).thenReturn("/content/vida.price-master-json.json");
		readJsonServlet.doGet(req, resp);

		Mockito.when(asset.getOriginal()).thenReturn(null);
		Mockito.when(req.getPathInfo()).thenReturn("/content/vida.map-json.json");
		readJsonServlet.doGet(req, resp);

		Mockito.when(req.getPathInfo()).thenReturn("/content/vida.nearbybranch.json");
		readJsonServlet.doGet(req, resp);

		assertNotNull(readJsonServlet);
	}

}
