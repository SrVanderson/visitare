package com.primeit.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.primeit.entity.UserRole;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;

@SuppressWarnings("serial")
public class RoleRepository extends JPAContainer<UserRole> {

	EntityManager em;
	private static final String PERSISTENCE_UNIT = "visitare-backend";

	public RoleRepository() {
		super(UserRole.class);
		em = JPAContainerFactory.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
		setEntityProvider(new CachingMutableLocalEntityProvider<UserRole>(UserRole.class, em));
	}

	@SuppressWarnings("unchecked")
	public List<UserRole> getRoles() {
		List<UserRole> roles = null;
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			Query query = em.createQuery("SELECT r FROM UserRole r");
			roles = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roles;
	}

	public UserRole getRoleByName(String roleName) {
		UserRole role = new UserRole();
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			TypedQuery<UserRole> query = em.createQuery("FROM UserRole ur WHERE ur.roleName = :roleName", UserRole.class);
			role = query.setParameter("roleName", roleName).getSingleResult();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return role;
	}

	public UserRole getRoleById(int id) {
		UserRole role = null;
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			role = em.find(UserRole.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

}
