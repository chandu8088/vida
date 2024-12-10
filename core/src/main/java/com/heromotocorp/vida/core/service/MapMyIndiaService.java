package com.heromotocorp.vida.core.service;

import org.apache.http.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * The Interface MapMyIndia.
 */
public interface MapMyIndiaService {

	/**
	 * Environment.
	 *
	 * @return the string
	 */
	Map<String, String> getAccessToken() throws ParseException, IOException, URISyntaxException;
}
