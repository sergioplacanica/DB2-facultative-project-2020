package services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Product;
import model.Questionnaire;
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
	
	public  Product findProductByDate(Date date) {
		List<Product> products=em.createQuery("SELECT p FROM Product p WHERE p.date=?1", Product.class).setParameter(1, date).getResultList();
		
		if(products.isEmpty()) 
			return null;
		
		return products.get(0);
	}
	
	public Product getProductOfTheDay() throws ParseException {
		Date startDate = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String current_date = (String) dtf.format(now);
		startDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(current_date).getTime());
		return findProductByDate(startDate);
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
		List<Product> products = em.createQuery("SELECT DISTINCT q.product FROM Questionnaire q order by q.date asc", Product.class).getResultList();
		if(products.isEmpty()) 
			return null;
		return products;
	}
	
	public List<Product> futureQuestionnaire() {
		Date startDate = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String current_date = (String) dtf.format(now);
		try {
			startDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(current_date).getTime());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		List<Product> products = em.createQuery("SELECT p FROM Product p WHERE p.date > ?1", Product.class)
				.setParameter(1, startDate).getResultList();
		if(products.isEmpty())
			return null;
		return products;
	}

	public boolean alreadyScheduled(Date date) {
		boolean scheduled = !em.createQuery("SELECT p FROM Product p WHERE p.date = ?1", Product.class)
				.setParameter(1, date)
				.getResultList()
				.isEmpty();
		return scheduled;
		
		
	}
	
	public void deleteProduct(Product p) {
		TypedQuery<Product> query = em.createQuery("DELETE FROM Product  WHERE productID = ?1", Product.class);
		int deleted = query.setParameter(1, p.getProductID()).executeUpdate();
	}
}
