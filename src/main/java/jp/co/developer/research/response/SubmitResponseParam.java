package jp.co.developer.research.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SubmitResponseParam {

	private ResponseStatusCode status;
	private Object result;

	public SubmitResponseParam() {
	}

	public ResponseStatusCode getStatus() {
		return status;
	}

	public void setStatus(ResponseStatusCode status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String toJson() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
}
