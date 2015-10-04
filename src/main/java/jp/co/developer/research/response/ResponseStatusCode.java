package jp.co.developer.research.response;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Response status code for submit
 *
 */
public enum ResponseStatusCode {
	SUCCESS(100), VALIDATION_ERROR(200), RUNTIME_ERROR(999);

	private ResponseStatusCode(int code) {
		this.statusCode = code;
	}

	private int statusCode;

	@JsonValue
	public int getValue() {
		return this.statusCode;
	}
}
