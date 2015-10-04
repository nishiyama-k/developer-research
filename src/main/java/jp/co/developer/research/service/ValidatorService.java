package jp.co.developer.research.service;

import java.util.List;

import jp.co.developer.research.validate.Validator;
import jp.co.developer.research.validate.ValidatorException;

/**
 * This service provide you to validate entity functions<br>
 * When you want to BasicValidate to your own entity, annotate BasicValidate annotation at your entity.<br>
 * @see jp.co.developer.research.validate.annotation.BasicValidate
 *
 */
public interface ValidatorService {

	/**
	 * Validate only BasicValidate
	 * 
	 * @param e
	 * @throws ValidatorException
	 */
	<T> void validate(T e) throws ValidatorException;

	/**
	 * Validate BasicValidate and BusinessValidate
	 * 
	 * @param e
	 * @param businessValidatorList
	 * @throws ValidatorException
	 */
	<T> void validate(T e, List<Validator<T>> businessValidatorList) throws ValidatorException;
	
}
