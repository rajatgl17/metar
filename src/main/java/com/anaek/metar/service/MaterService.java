package com.anaek.metar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anaek.metar.model.MaterVO;
import com.anaek.metar.utils.Utils;

@Service
public class MaterService {
	
	@Autowired
	private MaterApiTemplate materApiTemplate;
	
	public MaterVO getWeatherInformation(String scode) {
		String response = materApiTemplate.getWeatherData(scode);
		return Utils.parser(response);
	}

}
