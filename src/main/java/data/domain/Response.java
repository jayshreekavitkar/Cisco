package data.domain;

import java.util.ArrayList;
import java.util.List;

public class Response {

	int status;
	String description;
	Errors errors;
	List<User> data;

	public Response() {

	}

	public Response(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<User> getData() {
		return data;
	}

	public void setData(List<User> data) {
		this.data = data;
	}

	public void setData(User user) {
		data = new ArrayList<User>();
		data.add(user);
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

}
