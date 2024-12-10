package com.heromotocorp.vida.core.servlets;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import com.heromotocorp.vida.core.config.GlobalConfig;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.ServletResolverConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.util.EntityUtils;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

@SuppressWarnings("serial")
@Component(service = Servlet.class, immediate = true, property = {
        Constants.SERVICE_DESCRIPTION + "=Map My India servlet",
        ServletResolverConstants.SLING_SERVLET_RESOURCE_TYPES + "=" + ServletResolverConstants.DEFAULT_RESOURCE_TYPE,
        ServletResolverConstants.SLING_SERVLET_SELECTORS + "=getMMIDetails",
        ServletResolverConstants.SLING_SERVLET_METHODS + "=" + HttpConstants.METHOD_GET })
public class MapMyIndiaServlet extends SlingAllMethodsServlet {

    /**
     * Generated serialVersionUID
     */
    private static final long serialVersionUID = 4438376868274173005L;

    private static final Logger log = LoggerFactory.getLogger(MapMyIndiaServlet.class);

    ObjectMapper objectMapper;

    @Reference
    private transient GlobalConfig globalConfig;

    static String token;

	static LocalDateTime tokenExpiryTime;

    @Activate
    public void activate() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String> accessToken = null;
        try {
            accessToken = getAccessToken(); 
            // Create a simple JSON object with the token
            String jsonResponse = objectMapper.writeValueAsString(accessToken);

            // Set the response content type
            resp.setContentType("application/json");

            // Write the JSON response to the client
            resp.getWriter().write(jsonResponse);
        } catch (URISyntaxException e) {
            log.error("Exception in MapMyIndiaServlet {} ",e.getMessage(),e);
        }
    }

	public Map<String, String> getAccessToken() throws ParseException, IOException, URISyntaxException {
		// Get the current time
		LocalDateTime currentTime = LocalDateTime.now();
		// Check if a valid access token exists or if the existing token has expired
		if (getToken() == null || (getTokenExpiryTime() != null && currentTime.isAfter(getTokenExpiryTime()))) {
			log.info("Token expired and generating new token with last time generated is : {}", getTokenExpiryTime());
			// Fetch a new access token and update its associated timestamp
			setToken(getAPIAccessToken()); // Fetch a new access token
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", getToken());
        tokenMap.put("tokenTime", getTokenExpiryTime().toString());
		// Return the valid access token
		return tokenMap;
	}

	/**
	 * Retrieves the access token required for API authorization.
	 *
	 * @return The access token as a String.
	 * @throws ParseException          If a parsing error occurs.
	 * @throws ClientProtocolException If a protocol-level error occurs.
	 * @throws IOException             If an I/O error occurs.
	 * @throws URISyntaxException
	 */
	private String getAPIAccessToken() throws ParseException, IOException, URISyntaxException {

		CloseableHttpClient httpClient = createDefaultHttpClient();
		try {

			String urlParameters = "?grant_type=" + globalConfig.getMMIGrantType() + "&client_id="
					+ globalConfig.getMMIClientID() + "&client_secret="
					+ globalConfig.getMMIClientSecret();
			final HttpPost postReq = new HttpPost(
					globalConfig.getMMIEndPoint().concat(urlParameters));

			postReq.setHeader("accept", "application/json");
			postReq.setHeader("Content-Type", "application/json");
			CloseableHttpResponse response = httpClient.execute(postReq);
			String jsonResponse = EntityUtils.toString(response.getEntity());
			// Assuming you are using a library like Jackson for JSON parsing
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(jsonResponse);
			if(null != jsonNode.get("expires_in")) {
				Long seconds = jsonNode.get("expires_in").asLong();
				setTokenExpiryTime(LocalDateTime.now().plusSeconds(seconds)); // Update the timestamp for the new token
			}
			return jsonNode.get("access_token").asText();

		} finally {
			httpClient.close();
		}
	}


	public CloseableHttpClient createDefaultHttpClient() {
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(10000)
				.setSocketTimeout(10000)
				.build();
		return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).useSystemProperties().build();
	}

  public String getToken() {
		return token;
	}

	public void setToken(String token) {
		MapMyIndiaServlet.token = token;
	}

	public void setTokenExpiryTime(LocalDateTime time) {
		MapMyIndiaServlet.tokenExpiryTime = time;
	}

	public LocalDateTime getTokenExpiryTime() {
		return tokenExpiryTime;
	}
}