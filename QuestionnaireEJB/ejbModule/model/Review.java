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

	@EmbeddedId
	private ReviewPK id;

	@Column(name="Review_Text")
	private String review_Text;

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

	public ReviewPK getId() {
		return this.id;
	}

	public void setId(ReviewPK id) {
		this.id = id;
	}

	public String getReview_Text() {
		return this.review_Text;
	}

	public void setReview_Text(String review_Text) {
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