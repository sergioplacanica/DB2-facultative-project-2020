package services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import model.Accesstime;
import model.User;

@Stateless
public class AccessTimeService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;

	public void createAccessTime(Date date, User user) {
		System.out.println(date);
		Accesstime accessTime=new Accesstime();
		accessTime.setUser(user);
		accessTime.setAccess_time(date);
		em.persist(accessTime);
		
	}
	
	public List<Accesstime> getAbortedQuest(Date date) {
		
		List<Accesstime> accessTimeList = em.createQuery("SELECT a FROM Accesstime a WHERE a.access_time = ?1", Accesstime.class )
				.setParameter(1, date, TemporalType.TIMESTAMP)
				.getResultList();
		
		return accessTimeList;
	}
}
