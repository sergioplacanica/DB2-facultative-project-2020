package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Marketingquestion;
import model.Product;

@Stateless
public class MarketingQuestionService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;
	
	public void createQuestion(String question,Product product) {
		Marketingquestion marketingQuestion=new Marketingquestion();
		marketingQuestion.setDescription(question);
		marketingQuestion.setProduct(product);
		em.persist(marketingQuestion);
	}
}
