package services;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	
	
	public Product findProduct(int productID) {
		List<Product> products=em.createQuery("SELECT p FROM Product p WHERE p.productID=?1", Product.class).setParameter(1, productID).getResultList();
		return products.get(0);
	}
	
	public List<Product> findAll() {
		List<Product> products=em.createNamedQuery("Product.findAll", Product.class).getResultList();
		return products;
	}
	
	public List<Product> withActiveQuestionnaire() {
		List<Product> products = em.createQuery("SELECT DISTINCT q.product FROM Questionnaire q", Product.class).getResultList();
		return products;
	}

}
