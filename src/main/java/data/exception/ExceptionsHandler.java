package data.exception;

import data.domain.Errors;
import data.domain.HttpStatus;
import data.domain.Response;

public class ExceptionsHandler {

	private static final String NO_DATA_FOUND = "No data found";
	public static final String MISSING_JSON_PARAM = "Missing a required param in Json. Please check your JSON request";
	/**
	 * Handle exception if given entity is not present in database
	 * 
	 * @param id
	 * @return Response
	 */
	public Response UserNotFoundException(long id) {
		Response res = new Response();
		Errors errors = new Errors();
		res.setStatus(HttpStatus.NOT_FOUND);
		res.setDescription(NO_DATA_FOUND);
		errors.setMsg("User with id " + id + "  not found");
		res.setErrors(errors);
		System.out.println(res.getStatus());
		return res;
	}

	/**
	 *Handle exception if some required field is missing in json request
	 * 
	 * @return Response
	 */
	public Response MissingJsonParamException() {
		Response res = new Response();
		Errors errors = new Errors();
		res.setStatus(HttpStatus.MISSING_DATA);
		res.setDescription("Missing data");
		errors.setMsg("Missing a required param in Json. Please check your JSON request");
		res.setErrors(errors);
		System.out.println(res.getStatus());
		return res;
	}

	/**
	 * Handle exception if id is specified explicitly in json request
	 * 
	 * @param id
	 * @return Response
	 */
	public Response InvalidDataException(long id) {
		Response res = new Response();
		Errors errors = new Errors();
		res.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		res.setDescription("Internal Server Error");
		errors.setMsg("Invalid Json:Id is auto generated");
		res.setErrors(errors);
		System.out.println(res.getStatus());
		return res;
	}
}
