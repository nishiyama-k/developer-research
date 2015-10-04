package jp.co.developer.research.validate;

/**
 * When you want your own validator , implement this
 *
 * @param <T> validate target entity
 */
public abstract class AbstractBusinessValidator<T> implements Validator<T>{

	public AbstractBusinessValidator() {
	}
	
}
