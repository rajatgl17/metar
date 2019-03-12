package com.anaek.metar.model;

import java.io.Serializable;

public class ResponseVO implements Serializable {
	
	private static final long serialVersionUID = 6421472777080661463L;
	
	private Object data;
	
	public ResponseVO(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
