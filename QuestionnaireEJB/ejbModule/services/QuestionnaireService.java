package services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Product;
import model.Questionnaire;
import model.User;

@Stateless

public class QuestionnaireService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;

	public QuestionnaireService() {

	}
	
	public Questionnaire createQuestionnaire (Integer age, String exp_lvl, String gender, Date date, Product product, User user) {
		Questionnaire questionnaire = new Questionnaire();
		questionnaire.setAge(age);
		questionnaire.setDate(date);
		questionnaire.setExpertise_level(exp_lvl);
		questionnaire.setSex(gender);
		questionnaire.setUser(user);
		questionnaire.setProduct(product);
		em.persist(questionnaire);
		return questionnaire;
		
		
	}

}
