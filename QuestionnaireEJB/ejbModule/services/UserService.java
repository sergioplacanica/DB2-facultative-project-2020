package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import exceptions.UsernameException;
import model.User;

@Stateless
public class UserService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;
	
	public void createUser(String username, String password, String email) throws UsernameException {
		System.out.println(em);
		User user = new User(username, password, email);
		user.setAdmin(false);
		user.setBlocked(false);
		try {
			em.persist(user);
		}
		catch(Exception e){
			throw new UsernameException("This username has been already used");
		}

	}
}
