
package services;

import java.util.List;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.*;



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
	
	
	//This is kind of weird Not gonna lie
	public List<Questionnaire> findAllDistinct() {
		//get distinct products
		
		List<Questionnaire> questionnaires = em.createNamedQuery("Questionnaire.findAllDistinct", Questionnaire.class).getResultList();
		return questionnaires;
		
	}
	
	public Questionnaire findByPK(int userID, int productID) {
		Questionnaire questionnaire;
		TypedQuery<Questionnaire> query = em.createQuery("SELECT q FROM Questionnaire q WHERE q.user.userID = :userID and q.product.productID = :productID", Questionnaire.class);
		questionnaire = query
				.setParameter("userID", userID)
				.setParameter("productID", productID)
				.getSingleResult();
		
		return questionnaire;
	}
	
	public Boolean alreadyCompiled(int userID, int productID) {
		Boolean compiled;
		TypedQuery<Questionnaire> query = em.createQuery("SELECT q FROM Questionnaire q WHERE q.user.userID = :userID and q.product.productID = :productID", Questionnaire.class);
		compiled = !query
				.setParameter("userID", userID)
				.setParameter("productID", productID)
				.getResultList()
				.isEmpty();
		
		return compiled;
	}
	
	//return all the questionnaires for a specific product
	public List<Questionnaire> findByProduct(Product product) {
		List<Questionnaire> questionnaires;
		TypedQuery<Questionnaire> query = em.createQuery("SELECT q FROM Questionnaire q WHERE q.product = :product ", Questionnaire.class);
		questionnaires = query.setParameter("product", product).getResultList();
		return questionnaires;
	}
	
	//delete all the questionnaire relative to a product, return the number of entities removed
	//TODO check what happens on referenced rows in other tables
	public int deleteByProduct(Product product) {
		TypedQuery<Questionnaire> query = em.createQuery("DELETE FROM Questionnaire WHERE product.productID = ?1", Questionnaire.class);
		int deleted = query.setParameter(1, product.getProductID()).executeUpdate();
		return deleted;
	}
	
	
	
	
}

	