package com.cg.billing.daoservices;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.cg.billing.beans.Customer;

public class CustomerdaoImpl implements CustomerDao{
 EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");
	@Override
	public Customer save(Customer customer) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
		entityManager.close();
		return customer;
	}

	@Override
	public boolean update(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Customer findOne(int customerId) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		return entityManager.find(Customer.class, customerId);
	}

	@Override
	public List<Customer> findAll() {
		return entityManagerFactory.createEntityManager().createQuery("from Customer c ").getResultList();
	}

	@Override
	public boolean remove(Customer customer) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.merge(customer));
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

}
