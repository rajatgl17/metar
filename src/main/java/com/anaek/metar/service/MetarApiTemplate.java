package com.anaek.metar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.anaek.metar.exception.InvalidInputException;
import com.anaek.metar.utils.Constants;

@Component
public class MetarApiTemplate {

	private static final Logger LOGGER = LoggerFactory.getLogger(MetarApiTemplate.class);

	@Value("${mater.url}")
	public String materUrl;

	@Cacheable(value = "mater-cache", key = "#scode", unless = "#result == null")
	public String getWeatherDataWithCache(String scode) {
		return getWeatherData(scode);
	}

	@CachePut(value = "mater-cache", key = "#scode", unless = "#result == null")
	public String getWeatherDataWithoutCache(String scode) {
		return getWeatherData(scode);
	}

	private String getWeatherData(String scode) {
		String url = materUrl.replace(Constants.SCODE_PATTERN, scode);
		LOGGER.info("Calling URL : {}", url);
		
		ResponseEntity<String> response = null;

		try {
			response = new RestTemplate().getForEntity(url, String.class);
		} catch(Exception e) {
			throw new InvalidInputException("Invalid input scode : " + scode);
		}
		
		if (response.getStatusCode() == HttpStatus.OK) {
			LOGGER.info("Response : {}", response.getBody());
			return response.getBody();
		} else {
			LOGGER.error("Error while calling mater API, status : {}", response.getStatusCode());
			throw new InvalidInputException("Invalid input scode : " + scode);
		}
	}

}
