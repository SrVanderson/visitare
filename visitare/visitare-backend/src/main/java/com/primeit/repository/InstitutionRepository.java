package com.primeit.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.primeit.entity.Institution;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;

@SuppressWarnings("serial")
public class InstitutionRepository extends JPAContainer<Institution> {

	EntityManager em;
	private static final String PERSISTENCE_UNIT = "visitare-backend";

	public InstitutionRepository() {
		super(Institution.class);
		em = JPAContainerFactory.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
		setEntityProvider(new CachingMutableLocalEntityProvider<Institution>(Institution.class, em));
	}

	@SuppressWarnings("unchecked")
	public List<Institution> getInstitutions() {
		List<Institution> institution = null;
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			Query query = em.createQuery("SELECT i FROM Institution i WHERE i.deleted=0");
			institution = query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return institution;
	}

	@SuppressWarnings("unchecked")
	public List<Institution> getInstitutionsEA() {
		List<Institution> institution = null;
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			Query query = em.createQuery("SELECT i FROM Institution i WHERE i.deleted=0 and type=1");
			institution = query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return institution;
	}

	@SuppressWarnings("unchecked")
	public List<Institution> getInstitutionsBA() {
		List<Institution> institution = null;
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			Query query = em.createQuery("SELECT i FROM Institution i WHERE i.deleted=0 and type=2");
			institution = query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return institution;
	}

	@SuppressWarnings("unchecked")
	public List<Institution> getInstitutionsBeneficiary() {
		List<Institution> institution = null;
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			Query query = em.createQuery("SELECT i FROM Institution i WHERE i.deleted=0 and type=3");
			institution = query.getResultList();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return institution;
	}

	public Institution getInstitutionByName(String name) {
		Institution institution = new Institution();
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			TypedQuery<Institution> query = em.createQuery("FROM Institution i WHERE i.name = :name", Institution.class);
			institution = query.setParameter("name", name).getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return institution;
	}

}
