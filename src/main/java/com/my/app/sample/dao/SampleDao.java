package com.my.app.sample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.my.app.sample.entity.Sample;

@Repository
public class SampleDao {

	@PersistenceContext
	private EntityManager entityManager;

	public List<Sample> findSeqs() {
		return entityManager.createQuery("from Sample", Sample.class).getResultList();
	}

	public Sample findSeq(Object primaryKey) {
		return entityManager.find(Sample.class, primaryKey);
	}

	public int save() {
		entityManager.persist(null);

		entityManager.merge(null);

		entityManager.remove(null);

		return 1;
	}

}
