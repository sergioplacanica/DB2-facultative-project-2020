package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ProductID")
	private int productID;

	@Column(name="Image")
	private String image;

	@Column(name="Name")
	private String name;

	//bi-directional many-to-one association to Questionnaire
	@OneToMany(mappedBy="product")
	private List<Questionnaire> questionnaires;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="product")
	private List<Review> reviews;

	public Product() {
	}

	public int getProductID() {
		return this.productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Questionnaire> getQuestionnaires() {
		return this.questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

	public Questionnaire addQuestionnaire(Questionnaire questionnaire) {
		getQuestionnaires().add(questionnaire);
		questionnaire.setProduct(this);

		return questionnaire;
	}

	public Questionnaire removeQuestionnaire(Questionnaire questionnaire) {
		getQuestionnaires().remove(questionnaire);
		questionnaire.setProduct(null);

		return questionnaire;
	}

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setProduct(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setProduct(null);

		return review;
	}

}