package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import model.User;

public class TestConnection {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("QuestionnaireEJB");
		EntityManager em = emf.createEntityManager();
		TestService testService = new TestService(em);
		
		//find an User by is id
		em.getTransaction().begin();
		User user = testService.findUser(1);
		System.out.println("found User:" + user);
	}

}
