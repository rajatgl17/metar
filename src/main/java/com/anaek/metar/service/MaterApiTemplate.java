package com.anaek.metar.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.anaek.metar.utils.Constants;

@Component
public class MaterApiTemplate {

	private static final Logger LOGGER = LoggerFactory.getLogger(MaterApiTemplate.class);

	@Value("${mater.url}")
	public String materUrl;

	public String getWeatherData(String scode) {
		String url = materUrl.replace(Constants.SCODE_PATTERN, scode);
		LOGGER.info("Calling URL : {}", url);
		ResponseEntity<String> response = new RestTemplate().getForEntity(url, String.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			LOGGER.info("Response : {}", response.getBody());
			return response.getBody();
		} else {
			LOGGER.error("Error while calling mater API, status : {}", response.getStatusCode());
			return null;
		}
	}

}
