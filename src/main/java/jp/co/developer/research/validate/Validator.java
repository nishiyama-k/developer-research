package jp.co.developer.research.validate;

import java.util.List;

import jp.co.developer.research.vo.ValidateMessage;

/**
 * Validator interface.<br>
 *
 * @param <T> validate target entity
 */
public interface Validator<T> {

	/**
	 * Validate entity<br>
	 * If the entity has some errors , return ValidateMessage list.
	 * 
	 * @param entity
	 * @return if nothing error, return empty list.
	 */
	public List<ValidateMessage> validate(T entity);
	
}
