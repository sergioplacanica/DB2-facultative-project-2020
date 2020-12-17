package services;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Product;

@Stateless
public class ProductService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;
	
	public void createProduct(String productName,String imagePath,Date date) {
		Product product=new Product();
		product.setImage(imagePath);
		product.setName(productName);
		product.setDate(date);
		em.persist(product);
	}

}
