package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import model.Questionnaire;
import model.User;
import javax.persistence.PersistenceException;

import java.util.ArrayList;
import java.util.List;
import exceptions.CredentialsException;
import exceptions.NonUniqueResultException;
import exceptions.UpdateProfileException;
import exceptions.UsernameException;

import javax.ejb.Stateless;

@Stateless
public class UserService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;
	
	public UserService() {
	}
	
	public User checkCredentials (String usrn, String pwd) throws CredentialsException, NonUniqueResultException {
		List <User> uList;

		try {
	        uList =em.createQuery("SELECT u FROM User u where u.username = ?1 and u.password = ?2", User.class).setParameter(1, usrn).setParameter(2, pwd).getResultList();			
		} catch (PersistenceException e) {
			throw new CredentialsException ("Could not verify credentials");
		}
		if(uList.isEmpty())
			return null;
		else if (uList.size()==1)
			return uList.get(0);
		throw new NonUniqueResultException("There are many users with this credentials");
	}	
	public void updateProfile(User u) throws UpdateProfileException {
		try {
			em.merge(u);
		} catch (PersistenceException e) {
			throw new UpdateProfileException("Could not change profile");
		}
	}
	
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
	
	public List<User> findAllUsers() {
		 List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
		 return users;
	}
	
	

}
