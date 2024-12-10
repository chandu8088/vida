package com.heromotocorp.vida.core.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.util.EntityUtils;
import com.heromotocorp.vida.core.config.GlobalConfig;
import com.heromotocorp.vida.core.service.MapMyIndiaService;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.propertytypes.ServiceDescription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = MapMyIndiaService.class, immediate = true)
@ServiceDescription("MapMyIndia Api Service for access token and token expiry time")
public class MapMyIndiaServiceImpl implements MapMyIndiaService {

	@Reference
	private transient GlobalConfig globalConfig;

	private static final Logger log = LoggerFactory.getLogger(MapMyIndiaServiceImpl.class);

	String token;

	LocalDateTime time;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setTokenTime(LocalDateTime time) {
		this.time = time;
	}

	public LocalDateTime getTokenTime() {
		return time;
	}

	/**
	 * Retrieves and manages the access token for API authorization based on the
	 * current time.
	 *
	 * @return The access token as a String.
	 * @throws ParseException          If a parsing error occurs.
	 * @throws ClientProtocolException If a protocol-level error occurs.
	 * @throws IOException             If an I/O error occurs.
	 */
	@Override
	public Map<String, String> getAccessToken() throws ParseException, IOException, URISyntaxException {
		// Get the current time
		LocalDateTime currentTime = LocalDateTime.now();
		String tokenTimeout = globalConfig.getMMIAccessTokenTimeOut();
		long tokenTimeoutHours = 0;
		LocalDateTime timeBeforeTokenExpiry = LocalDateTime.now();
		// Calculate the time before the access token expires based on the configured expiry time
		try {
			tokenTimeoutHours = Long.parseLong(tokenTimeout);
			timeBeforeTokenExpiry = currentTime.minusHours(tokenTimeoutHours);
		} catch(Exception e){
			log.error("Exception while parsing the time ", e);
		}
		log.info("Token time : {} tokenTimeoutHours : {} ", getTokenTime(), tokenTimeoutHours);
		// Check if a valid access token exists or if the existing token has expired
		if (getToken() == null || (getTokenTime() != null && !getTokenTime().isAfter(timeBeforeTokenExpiry))) {
			log.info("Token expired and generating new token with last time generated is : {}", getTokenTime());
			// Fetch a new access token and update its associated timestamp
			setToken(getAPIAccessToken()); // Fetch a new access token
			setTokenTime(currentTime); // Update the timestamp for the new token
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", getToken());
        tokenMap.put("tokenTime", getTokenTime().toString());
		tokenMap.put("tokenTimeoutHours", tokenTimeout); 
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


}
