package data.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import data.domain.Errors;
import data.domain.Response;

@EnableWebMvc
@ControllerAdvice
public class GlobalExceptionHandlerController {

	private static final String UNPARSABLE_JSON_REQUEST = "Unparsable JSON Request";
	private static final String BAD_REQUEST = "Bad Request";
	private static final String INVALID_SERVICE_URI = "Invalid Service URI";
	private static final String SERVICE_UNAVAILABLE = "Service Unavailable";
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerController.class);

	/**
	 * To handle Invalid URL
	 * 
	 * @param e
	 * @return Response
	 */
	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class, TypeMismatchException.class,
			NoHandlerFoundException.class })
	@ResponseBody
	public Response MethodNotSupportedExceptionHandler(Exception e) {
		Response res = new Response();
		Errors er = new Errors();
		res.setStatus(503);
		res.setDescription(INVALID_SERVICE_URI);
		er.setMsg(SERVICE_UNAVAILABLE);
		res.setErrors(er);
		logger.error("Invalid URI");
		return res;
	}

	/**
	 * To handle incorrect json request
	 * 
	 * @return Response
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	public Response httpMessageNotReadableExceptionHandler() {
		Response res = new Response();
		Errors er = new Errors();
		res.setStatus(400);
		res.setDescription(BAD_REQUEST);
		er.setMsg(UNPARSABLE_JSON_REQUEST);
		res.setErrors(er);
		logger.error("Unparsable json request");
		return res;

	}

}
