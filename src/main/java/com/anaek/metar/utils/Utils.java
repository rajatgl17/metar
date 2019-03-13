package com.anaek.metar.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anaek.metar.model.MetarVO;

public class Utils {

	private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);

	private static final DateFormat RESPONSE_FORMAT = new SimpleDateFormat(Constants.DATE_FORMAT_1, Locale.ENGLISH);

	private static final DateFormat REQUEST_FORMAT = new SimpleDateFormat(Constants.DATE_FORMAT_2, Locale.ENGLISH);
	
	private static final String WIND_RESPONSE_FORMAT = "%s at %d mph (%d knots)";
	
	private static final String TEMP_RESPONSE_FORMAT = "%d C (%d F)";

	public static MetarVO parser(String response) {
		MetarVO mater = new MetarVO();
		try {
			String[] splitResponse = response.split(System.lineSeparator());
			String codes[] = splitResponse[1].split(Constants.SPACE_SPLIT);

			mater.setStation(codes[0]);
			mater.setLast_observation(parseDate(splitResponse[0]));
			mater.setTemperature(tempParser(codes));
			mater.setWind(windParser(codes));
			return mater;
		} catch (Exception e) {
			LOGGER.error("Exception occured while parsing : {}", e);
		}

		return null;
	}

	private static String parseDate(String response) throws ParseException {
		Date date = RESPONSE_FORMAT.parse(response);
		return REQUEST_FORMAT.format(date);
	}

	private static String windParser(String codes[]) {
		String windCode = Arrays.stream(codes).filter(code -> code.indexOf(Constants.WIND_REGEX) > -1).findFirst()
				.orElse(null);
		if (windCode == null) {
			return null;
		}
		Integer degree = Integer.parseInt(windCode.substring(0, 3));
		Integer speedInKnots = Integer.parseInt(windCode.substring(3, 5));
		;
		Integer speedInMph = knotsToMph(speedInKnots);
		return String.format(WIND_RESPONSE_FORMAT, degreeToDirection(degree), speedInMph, speedInKnots);
	}

	private static String tempParser(String codes[]) {
		String tempCode = Arrays.stream(codes).filter(code -> code.matches(Constants.TEMP_REGEX)).findFirst()
				.orElse(null);
		if (tempCode == null) {
			return null;
		}
		String temp = tempCode.split(Constants.TEMP_SPLITTER)[0];
		int tempInCelsius;
		if (temp.charAt(0) == Constants.NEGATIVE_CHAR) {
			tempInCelsius = -1 * Integer.parseInt(temp.substring(1));
		} else {
			tempInCelsius = Integer.parseInt(temp);
		}
		return String.format(TEMP_RESPONSE_FORMAT, tempInCelsius, celsiusToFahrenheit(tempInCelsius));
	}

	private static int celsiusToFahrenheit(int temp) {
		return Math.round((temp * (9 / 5)) + 32);
	}

	private static String degreeToDirection(int degree) {
		return Constants.DIRECTIONS[(int) Math.round((((double) degree % 360) / 45))];
	}

	private static int knotsToMph(int speed) {
		return (int) Math.round(speed * 1.15078);
	}
}
