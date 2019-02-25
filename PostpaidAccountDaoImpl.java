package com.cg.billing.daoservices;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;

public class PostpaidAccountDaoImpl implements PostpaidAccountDao{
	EntityManagerFactory entityManagerFactory=Persistence.createEntityManagerFactory("JPA-PU");
	@Override
	public PostpaidAccount save(PostpaidAccount postpaidAccount) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(postpaidAccount);
		entityManager.getTransaction().commit();
		entityManager.close();
		return postpaidAccount;
	}

	@Override
	public boolean update(PostpaidAccount postpaidAccount) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(postpaidAccount);
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

	@Override
	public PostpaidAccount findOne(long mobileNo) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		return entityManager.find(PostpaidAccount.class, mobileNo);
	}

	@Override
	public List<PostpaidAccount> findAll(int customerID) {
		return entityManagerFactory.createEntityManager().createQuery("from PostpaidAccount p Where CUSTOMER_CUSTOMERID="+customerID,PostpaidAccount.class).getResultList();
	}

	@Override
	public boolean remove(PostpaidAccount postpaidAccount,long mobileNo) {
		EntityManager entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.remove(entityManager.find(PostpaidAccount.class, mobileNo));
		entityManager.flush();
		entityManager.getTransaction().commit();
		entityManager.close();
		return true;
	}

}
