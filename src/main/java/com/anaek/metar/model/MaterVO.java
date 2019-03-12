package com.anaek.metar.model;

import java.io.Serializable;

public class MaterVO implements Serializable {

	private static final long serialVersionUID = 3034328696873590269L;

	private String station;

	private String last_observation;

	private String temperature;

	private String wind;

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getLast_observation() {
		return last_observation;
	}

	public void setLast_observation(String last_observation) {
		this.last_observation = last_observation;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

}
