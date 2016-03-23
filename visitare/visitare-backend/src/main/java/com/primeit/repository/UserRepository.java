package com.primeit.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.primeit.entity.User;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;

@SuppressWarnings("serial")
public class UserRepository extends JPAContainer<User> {

	EntityManager em;
	private static final String PERSISTENCE_UNIT = "visitare-backend";

	public UserRepository() {
		super(User.class);
		em = JPAContainerFactory.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
		setEntityProvider(new CachingMutableLocalEntityProvider<User>(User.class, em));
	}

	public User getUserByEmail(String email) {
		User user = new User();
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			TypedQuery<User> query = em.createQuery("FROM User u WHERE u.email = :email", User.class);
			user = query.setParameter("email", email).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	public User getUserById(int id) {
		User user = null;
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			user = em.find(User.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return user;
	}

	public User getUserByUserName(String username) {
		User user = new User();
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			TypedQuery<User> query = em.createQuery("FROM User u WHERE u.userName = :userName", User.class);
			user = query.setParameter("userName", username).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		List<User> users = null;
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			Query query = em.createQuery("SELECT U FROM User U WHERE U.deleted=0");
			users = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public void removeUser(User user) {
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			user.setDeleted(1);
			em.merge(user);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
	}

}
