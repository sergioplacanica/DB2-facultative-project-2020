package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Review database table.
 * 
 */
@Entity
@NamedQuery(name="Review.findAll", query="SELECT r FROM Review r")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ReviewID")
	private int reviewID;

	@Column(name="Review_Text")
	private Object review_Text;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="ProductID")
	private Product product;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserID")
	private User user;

	public Review() {
	}

	public int getReviewID() {
		return this.reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public Object getReview_Text() {
		return this.review_Text;
	}

	public void setReview_Text(Object review_Text) {
		this.review_Text = review_Text;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}