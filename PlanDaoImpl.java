package com.cg.billing.daoservices;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.billing.beans.Plan;

public class PlanDaoImpl implements PlanDao {
 EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");
	@Override
	public Plan findOne(int planID) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		return entityManager.find(Plan.class, planID);
	}
	@Override
	public List<Plan> findAll() {
		return entityManagerFactory.createEntityManager().createQuery("from Plan p",Plan.class).getResultList();
	}

}
