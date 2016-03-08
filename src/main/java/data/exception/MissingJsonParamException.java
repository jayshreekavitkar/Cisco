package data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.UNPROCESSABLE_ENTITY, reason="Missing a required param in Json. Please check your JSON request")
public class MissingJsonParamException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public MissingJsonParamException(){
		super(" Parameter missing");
	}

}

