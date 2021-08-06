package com.my.app.user.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.my.app.user.entity.User;

@Repository
public class UserDao {

	@PersistenceContext
	private EntityManager em;

	public Integer getMax() {
		return em.createQuery("select max(seq) from User", Integer.class).getSingleResult() + 1;

		// CriteriaBuilder cb = em.getCriteriaBuilder();
		// CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
		// Root<User> root = cq.from(User.class);
		// cq.select(cb.max(root.get("seq")));
		// return em.createQuery(cq).getSingleResult() + 1;
	}

	public void save(User user) {
		user.setSeq(getMax());
		em.persist(user);
	}

}
