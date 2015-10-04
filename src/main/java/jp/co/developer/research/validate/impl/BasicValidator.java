package jp.co.developer.research.validate.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;

import jp.co.developer.research.validate.Validator;
import jp.co.developer.research.validate.annotation.BasicValidate;
import jp.co.developer.research.vo.ValidateMessage;

/**
 * This provide Basic validate logic as a common service.<br>
 * You can validate your entity only with BasicValidate annotation on you entity.
 *
 * @param <T>
 */
public class BasicValidator<T> implements Validator<T> {

	/**
	 * BasicValidate annotation class definition
	 */
	private static final Class<BasicValidate> CLASS_DEF = BasicValidate.class;

	private static final String IS_EMPTY_METHOD = "isEmpty";

	/**
	 * TODO This should be managed under DB.
	 */
	private static final String REQUIRED_MSG = "%s is required. Please enter it.";

	@Override
	public List<ValidateMessage> validate(T entity) {
		if (!entity.getClass().isAnnotationPresent(CLASS_DEF)) {
			return Collections.emptyList();
		}
		List<ValidateMessage> errorList = new ArrayList<>();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field field : fields) {
			Optional<BasicValidate> optional = Optional.ofNullable(field.getAnnotation(CLASS_DEF));
			optional.ifPresent(v -> {
				try {
					checkRequired(entity, field).ifPresent(msg -> errorList.add(msg));
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}
		return errorList;
	}

	/**
	 * Check the field value has some value.<br>
	 * If the value has isEmpty method, also check it.<br>
	 * So if you want to check your own custom POJO, please implement isEmpty() method.<br>
	 * TODO Interface which has isEmpty() should be provided for custom object.
	 * 
	 * @param entity
	 * @param field
	 * @return Optional nullable
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private Optional<ValidateMessage> checkRequired(T entity, Field field)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (!field.getAnnotation(CLASS_DEF).required()) {
			return Optional.ofNullable(null);
		}
		field.setAccessible(true);

		Object value = field.get(entity);
		if (value == null) {
			return Optional.of((ValidateMessage.of(field.getName(), String.format(REQUIRED_MSG, getLabel(field)))));
		}
		// If the value has isEmpty() method, call the method and check return is true.
		Method[] methods = value.getClass().getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(IS_EMPTY_METHOD) && method.getParameterCount() == 0) {
				boolean isEmpty = (boolean) method.invoke(value);
				if (isEmpty) {
					return generateValidateMessage(field, String.format(REQUIRED_MSG, getLabel(field)));
				}
				break;
			}
		}
		return Optional.ofNullable(null);
	}

	/**
	 * Generate ValidateMessage
	 * 
	 * @param field
	 * @param message
	 * @return Optional non-null
	 */
	private Optional<ValidateMessage> generateValidateMessage(Field field, String message) {
		return Optional.of((ValidateMessage.of(field.getName(), message)));
	}

	/**
	 * Get label from BasicValidate annotation's label property<br>
	 * If BasicValidate annotation's label property is not specified, return field name.
	 * 
	 * @param field
	 * @return
	 */
	private String getLabel(Field field) {
		if (StringUtils.isEmpty(field.getAnnotation(CLASS_DEF).label())) {
			return field.getName();
		} else {
			return field.getAnnotation(CLASS_DEF).label();
		}
	}
}
