package com.anaek.metar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anaek.metar.model.MaterVO;
import com.anaek.metar.model.ResponseVO;
import com.anaek.metar.service.MaterService;
import com.anaek.metar.utils.Constants;

@RestController
@RequestMapping("/metar")
public class MetarController {
	
	@Autowired
	private MaterService materService;

	@GetMapping("/ping")
	public ResponseVO ping() {
		return new ResponseVO(Constants.PONG);
	}

	@GetMapping("/info")
	public ResponseVO getWeather(@RequestParam String scode, @RequestParam(required = false) Integer nocache) {
		MaterVO mater = materService.getWeatherInformation(scode);
		return new ResponseVO(mater);
	}

}
