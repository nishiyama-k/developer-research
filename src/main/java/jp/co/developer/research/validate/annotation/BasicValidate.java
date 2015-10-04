package jp.co.developer.research.validate.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * BasicValidate annotation.<br>
 * BasicValidator search this in class annotation, and start validate each field
 * validate, so do not forget attach this to class of your entity.<br>
 * TODO validate error message should be configurable in this.
 * 
 * @see jp.co.developer.research.validate.impl.BasicValidator
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.TYPE })
public @interface BasicValidate {

	/**
	 * When validated, this label used in validation message.<br>
	 * If not specified, field name used as label.
	 * TODO should be managed under DB
	 */
	public String label() default "";

	/**
	 * Validate whether the field value is null or empty<br>
	 * If you want check your own object, please implement isEmpty() method.<br>
	 * If you do so, BasicValidator check it and if return true, the field is
	 * handled as error, not only null check.
	 */
	public boolean required() default false;

}
