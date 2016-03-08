package data.service;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import data.domain.User;

@Repository
@Transactional

public class UserDao {
	  @PersistenceContext
	  private EntityManager entityManager;
	  
	 public void create(User user) {
		    entityManager.persist(user);
		    return;
		  }
	 
	 public void delete(User user) {
		    if (entityManager.contains(user))
		      entityManager.remove(user);
		 /*   else
		    	entityManager.remove( entityManager.merge(user));*/
		    return;
		  }
	 
	 @SuppressWarnings("unchecked")
	  public List<User> getAll() {
	    return entityManager.createQuery("from User").getResultList();
	  }
	 
	
	 
	 public User getById(long id) {
		    return entityManager.find(User.class, id);
		  }
	 
	 public int update(User user) {
		 	if(entityManager.contains(user))
		 	{
		 		entityManager.merge(user);
		 		return 1;
		 	}
		    return 0;
		  }

}

