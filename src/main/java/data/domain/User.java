package data.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "users")

public class User {
	
	@Id
	  private long id;
	  
	  @NotNull
	  private String name;  
	
	  @NotNull
	  private String email;
	  
	  public User() { }

	  public User(long id) { 
	    this.id = id;
	  }

	  public User(String email, String name) {
	    this.name = name;
	    this.email = email;
	  }

	  public long getId() {
	    return id;
	  }

	  public void setId(long value) {
	    this.id = value;
	  }

	  public String getEmail() {
	    return email;
	  }
	  
	  public void setEmail(String value) {
	    this.email = value;
	  }
	  
	  public String getName() {
	    return name;
	  }

	  public void setName(String value) {
	    this.name = value;
	  }
	  
	/*  @Override
	    public String toString() {
	        return String.format(id+"  "+name+"   "+email);
	    }*/
}

