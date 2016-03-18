package data.web;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.domain.HttpStatus;
import data.domain.Response;
import data.domain.User;
import data.exception.ExceptionsHandler;
import data.service.UserDao;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private static final String INVALID_JSON = "Invalid Json";
	private static final String MISSING_JSON_PARAM = "Missing Json Param";
	private static final String USER_NOT_FOUND = "User not found";
	private static final String RETURNING_ALL_USERS = "Returning all Users";
	private static final String USER_DETAILS_SUCCESSFULLY_UPDATED = "User details successfully Updated";
	private static final String RETURNING_THE_DETAILS_OF_USER = "Returning the details of user";
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;

	/**
	 * Inserting entity in database
	 * 
	 * @param user
	 * @return Response
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/insert", consumes = "application/json")
	public Response create(@RequestBody User user) {
		Response res = new Response();
		ExceptionsHandler eh = new ExceptionsHandler();
		try {
			userDao.create(user);
			res.setStatus(HttpStatus.OK);
			res.setDescription("User Created");
			res.setData(user);
			return res;
		} catch (ConstraintViolationException te) {
			res = eh.MissingJsonParamException();
			logger.error(MISSING_JSON_PARAM);
			return res;
		} catch (InvalidDataAccessApiUsageException de) {
			res = eh.InvalidDataException(user.getId());
			logger.error(INVALID_JSON);
			return res;
		}
	}

	/**
	 * Deleting Entity from database
	 * 
	 * @param id
	 * 
	 * @return Response
	 */
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public Response delete(@PathVariable long id) {

		Response res = new Response();
		ExceptionsHandler eh = new ExceptionsHandler();
		try {
			User user = userDao.getById(id);
			userDao.delete(user);
			res.setStatus(HttpStatus.OK);
			res.setDescription("User with id " + user.getId() + "  successfully deleted");
			return res;
		} catch (Exception ex) {
			res = eh.UserNotFoundException(id);
			logger.error(USER_NOT_FOUND);
			return res;
		}
	}

	/**
	 * Returning the specified user
	 * 
	 * @param id
	 * @return Response
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", consumes = "application/json")

	public Response getByID(@PathVariable long id) {
		Response res = new Response();
		ExceptionsHandler eh = new ExceptionsHandler();
		try {
			User user = userDao.getById(id);
			System.out.println(user.getName());
			res.setStatus(HttpStatus.OK);
			res.setDescription(RETURNING_THE_DETAILS_OF_USER);
			res.setData(user);

		} catch (Exception ex) {
			res = eh.UserNotFoundException(id);
			logger.error(USER_NOT_FOUND);
			return res;

		}
		return res;
	}

	/**
	 * Updating the given User
	 * 
	 * @param user
	 * @return Response
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = "application/json")

	public Response Update(@RequestBody User user) {
		Response res = new Response();
		ExceptionsHandler eh = new ExceptionsHandler();
		try {

			User user1 = new User();
			user1 = userDao.getById(user.getId());
			System.out.println(user1.getName() + " " + user1.getEmail());
			userDao.update(user);
			res.setStatus(HttpStatus.OK);
			res.setDescription(USER_DETAILS_SUCCESSFULLY_UPDATED);

			res.setData(user);
			return res;
		} catch (NullPointerException ne) {
			res = eh.UserNotFoundException(user.getId());
			logger.error(USER_NOT_FOUND);
			return res;
		} catch (TransactionSystemException te) {
			res = eh.MissingJsonParamException();
			logger.error(MISSING_JSON_PARAM);
			return res;
		}

	}

	/**
	 * Displaying contents of database
	 * 
	 * @return Response
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/", consumes = "application/json")
	public Response getEmps() {
		Response res = new Response();

		List<User> user = userDao.getAll();
		res.setStatus(HttpStatus.OK);
		res.setDescription(RETURNING_ALL_USERS);
		res.setData(user);
		return res;
	}
}
