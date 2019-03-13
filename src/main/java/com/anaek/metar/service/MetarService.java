package com.anaek.metar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anaek.metar.model.MetarVO;
import com.anaek.metar.utils.Utils;

@Service
public class MetarService {

	@Autowired
	private MetarApiTemplate metarApiTemplate;

	public MetarVO getWeatherInformation(String scode, Integer nocache) throws Exception {
		String response = nocache == 1 ? metarApiTemplate.getWeatherDataWithoutCache(scode)
				: metarApiTemplate.getWeatherDataWithCache(scode);
		if(response == null) {
			throw new Exception("Invalid scode");
		}
		return Utils.parser(response);
	}

}
