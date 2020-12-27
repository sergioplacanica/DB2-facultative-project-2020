package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import exceptions.AnswerException;
import exceptions.CredentialsException;
import exceptions.MarketingQuestionException;
import model.Answer;
import model.AnswerPK;
import model.Marketingquestion;
import model.Product;
import model.Questionnaire;

@Stateless
public class AnswerService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;

	public AnswerService() {

	}

	public void createAnswers(Integer UserID, Integer ProductID, List<Marketingquestion> questions, List<String> answers, Questionnaire questionnaire) throws AnswerException {
		try {
			int i = 0;
			for (Marketingquestion x : questions) {
				Answer answer = new Answer();
				AnswerPK answerPK = new AnswerPK();
				//answerPK.setProductID(ProductID);
				//answerPK.setUserID(UserID);
				//answerPK.setQuestionID(x.getQuestionID());
				//System.out.println("questa è la answerPK " + answerPK.getProductID() + " " + answerPK.getQuestionID() + " "	+ answerPK.getUserID());
				answer.setQuestionnaire(questionnaire);
				answer.setMarketingquestion(x);
				answer.setAnswer_text(answers.get(i));
				answer.setOffensive(false);
				answer.setId(answerPK);
				System.out.println("L'user che sto inserendo ha Answer_text:" +answer.getAnswer_text()+" Offensive: " +answer.getOffensive() +"AnswerPK object:" +answer.getId() +"answer QuestionID: "+answerPK.getQuestionID()+"answer ProductID: "+answerPK.getProductID()+"answer UserID: "+answerPK.getUserID());
				em.persist(answer);
				i = i+1;
			}
			
		} catch (PersistenceException e) {
			throw new AnswerException("Could not create the answers");		
			}
	}
	/*
	 * public void createQuestion(String question,Product product) {
	 * Marketingquestion marketingQuestion=new Marketingquestion();
	 * marketingQuestion.setDescription(question);
	 * marketingQuestion.setProduct(product); em.persist(marketingQuestion); }
	 */

}
