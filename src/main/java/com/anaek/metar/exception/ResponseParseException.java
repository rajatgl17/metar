package com.anaek.metar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ResponseParseException extends RuntimeException {

	private static final long serialVersionUID = -2895532434190157521L;

	public ResponseParseException(String message) {
		super(message);
	}

}
