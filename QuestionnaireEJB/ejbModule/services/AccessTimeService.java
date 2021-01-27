package services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Accesstime;
import model.User;

@Stateless
public class AccessTimeService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;

	public void createAccessTime(Date date, User user) {
		Accesstime accessTime=new Accesstime();
		accessTime.setUser(user);
		accessTime.setAccess_time(date);
		em.persist(accessTime);
	}
}
