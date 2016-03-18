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

	// Inserting into databse
	public void create(User user) {
		entityManager.persist(user);
		return;
	}

	// Deleting from database
	public void delete(User user) {
		if (entityManager.contains(user))
			entityManager.remove(user);

		return;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAll() {
		return entityManager.createQuery("from User").getResultList();
	}

	public User getById(long id) {
		return entityManager.find(User.class, id);
	}

	public void update(User user) {

		entityManager.merge(user);
	}
}
