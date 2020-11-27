package test;

import javax.persistence.EntityManager;

import model.User;

public class TestService {
	protected EntityManager em;
	
	public TestService(EntityManager em) {
		this.em = em;
		
	}
	
	public User findUser(int id) {
		return em.find(User.class, id);
	}
}
