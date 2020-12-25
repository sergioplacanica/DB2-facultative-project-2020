package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import model.Marketingquestion;
import model.Product;
import exceptions.MarketingQuestionException;

@Stateless
public class MarketingQuestionService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;
	
	public MarketingQuestionService() {
		
	}
	
	public void createQuestion(String question,Product product) {
		Marketingquestion marketingQuestion=new Marketingquestion();
		marketingQuestion.setDescription(question);
		marketingQuestion.setProduct(product);
		em.persist(marketingQuestion);
	}
	
	public List <Marketingquestion> retrieveQuestions(Product product) throws MarketingQuestionException {
		List <Marketingquestion> qList;
		try {
			qList = em.createQuery("SELECT u FROM Marketingquestion u where u.product.productID = ?1 ",Marketingquestion.class).setParameter(1, product.getProductID()).getResultList();			
		} catch (PersistenceException e) {
			throw new MarketingQuestionException("Could not execute the query");
		}
		if (qList.isEmpty())
			return null;		
		return qList;
	}
}
