package com.cg.billing.daoservices;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.billing.beans.Bill;
public class BillDaoImpl implements BillDao{
EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");
	@Override
	public Bill save(Bill bill) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(bill);
		entityManager.getTransaction().commit();
		entityManager.close();
		return bill;
	}

	@Override
	public Bill findOne(int billID) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		return entityManager.find(Bill.class, billID);
	}

	@Override
	public List<Bill> findAll() {
		return entityManagerFactory.createEntityManager().createQuery("from Bill b").getResultList();
	}

}
