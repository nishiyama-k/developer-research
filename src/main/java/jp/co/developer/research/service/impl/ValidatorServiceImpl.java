package jp.co.developer.research.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.developer.research.service.ValidatorService;
import jp.co.developer.research.validate.Validator;
import jp.co.developer.research.validate.ValidatorException;
import jp.co.developer.research.validate.impl.BasicValidator;
import jp.co.developer.research.vo.ValidateMessage;

@Service
public class ValidatorServiceImpl implements ValidatorService {

	@Override
	public <T> void validate(T e) throws ValidatorException {
		List<ValidateMessage> errorList = validateBasic(e);
		throwExceptionIfNotEmpty(errorList);
	}

	@Override
	public <T> void validate(T e, List<Validator<T>> businessValidatorList) throws ValidatorException {
		List<ValidateMessage> errorList = validateBasic(e);
		businessValidatorList.stream().forEach(v -> errorList.addAll(v.validate(e)));
		throwExceptionIfNotEmpty(errorList);
	}

	/**
	 * execute BasicValidator
	 * 
	 * @param e
	 * @return
	 */
	private <T> List<ValidateMessage> validateBasic(T e) {
		BasicValidator<T> basic = new BasicValidator<T>();
		return basic.validate(e);
	}

	/**
	 * Throw ValidatorException if errorList is not empty.
	 * 
	 * @param errorList
	 * @throws ValidatorException
	 */
	private void throwExceptionIfNotEmpty(List<ValidateMessage> errorList) throws ValidatorException {
		if (!errorList.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();
			String json;
			try {
				json = mapper.writeValueAsString(errorList);
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
				json = e1.getMessage();
			}
			throw new ValidatorException(json, errorList);
		}
	}
}
