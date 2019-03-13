package com.anaek.metar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anaek.metar.model.MetarVO;
import com.anaek.metar.model.ResponseVO;
import com.anaek.metar.service.MetarService;
import com.anaek.metar.utils.Constants;

@RestController
@RequestMapping("/metar")
public class MetarController {

	@Autowired
	private MetarService metarService;

	@GetMapping(path = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseVO ping() {
		return new ResponseVO(Constants.PONG);
	}

	@GetMapping(path = "/info", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseVO getWeather(@RequestParam String scode,
			@RequestParam(required = false, defaultValue = "0") Integer nocache) throws Exception {
		MetarVO metar = metarService.getWeatherInformation(scode, nocache);
		return new ResponseVO(metar);
	}

}
