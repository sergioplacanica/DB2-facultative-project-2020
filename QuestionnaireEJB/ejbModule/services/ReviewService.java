package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Product;
import model.Review;
import model.User;

@Stateless
public class ReviewService {
	@PersistenceContext(unitName = "QuestionnaireEJB")
	private EntityManager em;
	
	public void createReview(User user,String reviewText,Product product) {
		Review review=new Review();
		review.setUser(user);
		review.setReview_Text(reviewText);
		review.setProduct(product);
		em.persist(review);
	}
	
	public List<Review> findReviewsOfAProduct(Product product){
		List<Review> reviews=em.createQuery("SELECT r FROM Review r WHERE r.product=?1", Review.class).setParameter(1, product).getResultList();
		
		return reviews;
	}
}
