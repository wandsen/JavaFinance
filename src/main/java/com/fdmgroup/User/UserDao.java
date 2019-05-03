package com.fdmgroup.User;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {

	EntityManagerFactory emf;
	Logger logger = LogManager.getLogger();

	public UserDao(EntityManagerFactory emf) {

		this.emf = emf;
	}

	public void addUser(User user) {
		EntityManager em = emf.createEntityManager();
		logger.trace("adding user in userDao " + user);
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public User getUserByName(String name) {
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> query = em.createQuery("FROM User s WHERE s.name=:name", User.class);
		query.setParameter("name", name);

		User returnedUser = null;
		try {
			returnedUser = query.getSingleResult();
		} catch (NoResultException nre) {

		}

		em.close();
		if (returnedUser != null) {
			return returnedUser;
		} else
			return null;

	}

	public List<User> getAllUsers() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> query = em.createQuery("FROM Stock", User.class);
		System.out.println("calling db");
		List<User> allUserData = new ArrayList<User>();
		allUserData = query.getResultList();
		em.close();
		return allUserData;
	}

	public void updateUser(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(user);
		em.getTransaction().commit();
		em.close();

	}

	public void removeUser(User user) {
		EntityManager em = emf.createEntityManager();
		User foundUser = em.find(User.class, user.getId());
		em.getTransaction().begin();
		em.remove(foundUser);
		em.getTransaction().commit();
		em.close();
	}

}
