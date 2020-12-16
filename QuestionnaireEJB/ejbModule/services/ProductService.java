package services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Product;

@Stateless
public class ProductService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;
	
	public void createProduct(String productName,String imagePath) {
		Product product=new Product();
		product.setImage(imagePath);
		product.setName(productName);
		em.persist(product);
	}

}
