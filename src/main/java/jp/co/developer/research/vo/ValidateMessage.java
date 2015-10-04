package jp.co.developer.research.vo;

public class ValidateMessage {

	private ValidateMessage(String key, String message) {
		this.key = key;
		this.message = message;
	}

	/**
	 * key about what field has error, basically this is fieldName of entity.
	 */
	private String key;

	private String message;

	/**
	 * @param key about what field has error, basically this is fieldName of entity.
	 * @param message error message
	 * @return
	 */
	public static ValidateMessage of(String key, String message) {
		return new ValidateMessage(key, message);
	}

	public String getKey() {
		return key;
	}

	public String getMessage() {
		return message;
	}
}
