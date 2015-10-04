package jp.co.developer.research.validate;

import java.util.List;

import jp.co.developer.research.vo.ValidateMessage;

public class ValidatorException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<ValidateMessage> errorList;

	public ValidatorException(String message, List<ValidateMessage> errorList) {
		super(message);
		this.errorList = errorList;
	}

	public List<ValidateMessage> getErrorList() {
		return this.errorList;
	}

}
