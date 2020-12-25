package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.AnswerPK;
import model.Marketingquestion;
import model.Product;

@Stateless
public class AnswerService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;

	public AnswerService() {

	}
	
	/*
	public void createAnswer(Integer U) {
		AnswerPK answer = new AnswerPK();
		answer.setProductID(product.ge);
		
	}
	
	public void createQuestion(String question,Product product) {
		Marketingquestion marketingQuestion=new Marketingquestion();
		marketingQuestion.setDescription(question);
		marketingQuestion.setProduct(product);
		em.persist(marketingQuestion);
	}
	*/

}
