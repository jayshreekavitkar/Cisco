package data.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import data.domain.User;

import data.exception.MessageNotReadableException;
import data.exception.MissingJsonParamException;
import data.exception.UserNotFoundException;
import data.service.UserDao;

@RestController
@RequestMapping(value = "/Users")
public class UserController {
	
	 @Autowired
	  private UserDao userDao;
	
    @RequestMapping(method=RequestMethod.POST ,value = "/insert/")
	  @ResponseBody
	  public String create(@RequestBody User user) {
	   
		 try 
		 {
	    	//User user = new User(email, name);
	    	userDao.create(user);
	     }
	     catch (NullPointerException ex)
		 {
	    	 return "Error creating the user: " + ex.toString();
	     }
		 catch(TransactionSystemException te)
		 {
			 throw new MissingJsonParamException(); 
		 }
		 catch(HttpMessageNotReadableException e)
		 {
			 throw new MessageNotReadableException(e.toString());
		 }
	     return "User succesfully created!";
	  }
	 
	 @RequestMapping(method=RequestMethod.DELETE,value="/{id}")
	  @ResponseBody
	  public String delete(@PathVariable long id) {
	    try 
	    {
	      User user = userDao.getById(id);
	      userDao.delete(user);
	    }
	    catch (Exception ex)
	    {
	      throw new UserNotFoundException();
	    }
	    
	    return "User succesfully deleted!";
	  }
	 
	 @RequestMapping(method=RequestMethod.GET, value="/{id}")
	 @ResponseBody
	  public User getByID(@PathVariable long id) {
	    
	  try
	  {
	      User user = userDao.getById(id);
	      System.out.println(user.getName());
	      return user;
	  }
	  catch (Exception ex) {
		  throw new UserNotFoundException();
	    }
	    
	  }
	  
	 @RequestMapping(method=RequestMethod.PUT, value= "/update/")
	  @ResponseBody
	 
	  public String updateName(@RequestBody User user) {
		 System.out.println("In try block");
		 try {
			int flag; 
	      User user1 = userDao.getById(user.getId());
	      flag=userDao.update(user1);
	      if(flag==0)
	      {
	    	  throw new UserNotFoundException();
	      }
	    }
		 catch(TransactionSystemException te)
		 {
			 throw new MissingJsonParamException(); 
		 }
		 
	   
	    return "User succesfully updated!";
	  } 
	 
	 
	 
	 @RequestMapping(method=RequestMethod.GET, value="/")
		public List<User> getEmps() {
		 	List<User> user=userDao.getAll();
			return user;
		}

}

