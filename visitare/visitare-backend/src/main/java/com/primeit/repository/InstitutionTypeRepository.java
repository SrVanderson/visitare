package com.primeit.repository;

import javax.persistence.EntityManager;

import com.primeit.entity.InstitutionType;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.jpacontainer.provider.CachingMutableLocalEntityProvider;

@SuppressWarnings("serial")
public class InstitutionTypeRepository extends JPAContainer<InstitutionType> {

	EntityManager em;
	private static final String PERSISTENCE_UNIT = "visitare-backend";

	public InstitutionTypeRepository() {
		super(InstitutionType.class);
		em = JPAContainerFactory.createEntityManagerForPersistenceUnit(PERSISTENCE_UNIT);
		setEntityProvider(new CachingMutableLocalEntityProvider<InstitutionType>(InstitutionType.class, em));
	}

	public InstitutionType getTypeById(int id) {
		InstitutionType type = null;
		try {
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			type = em.find(InstitutionType.class, id);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		}
		return type;
	}

}
