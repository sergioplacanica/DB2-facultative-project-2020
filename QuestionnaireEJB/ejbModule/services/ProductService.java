package services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Product;
import model.User;

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
	
	public  Product findProduct(Date date) {
		List<Product> products=em.createQuery("SELECT p FROM Product p WHERE p.date=?1", Product.class).setParameter(1, date).getResultList();
		return products.get(0);
	}

}
