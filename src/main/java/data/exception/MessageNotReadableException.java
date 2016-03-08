package data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Unparsable Json request")
public class MessageNotReadableException extends HttpMessageNotReadableException {
	
	public MessageNotReadableException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
	

}