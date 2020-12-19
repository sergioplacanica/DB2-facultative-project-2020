package model;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Product database table.
 * 
 */
//@Table(name = "product", schema = "db_questionnaire")
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ProductID")
	private int productID;

	@Column(name="Image")
	private String image;

	@Column(name="Name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name="Date")
	private Date date;

	//bi-directional many-to-one association to Questionnaire 
	@OneToMany(mappedBy="product")
	private List<Questionnaire> questionnaires;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="product")
	private List<Review> reviews;
	
	//bi-directional many-to-one association to MarketingQuestion
	@OneToMany(mappedBy="product")
	private List<Marketingquestion> marketingQuestion;
	

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
	
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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


	public List<Marketingquestion> getMarketingQuestions() {
		return marketingQuestion;
	}

	public void setMarketingQuestions(List<Marketingquestion> marketingQuestion) {
		this.marketingQuestion = marketingQuestion;
	}
	
	public Marketingquestion addMarketingQuestion(Marketingquestion marketingQuestion) {
		getMarketingQuestions().add(marketingQuestion);
		marketingQuestion.setProduct(this);

		return marketingQuestion;
	}

	public Marketingquestion removeReview(Marketingquestion marketingQuestion) {
		getMarketingQuestions().remove(marketingQuestion);
		marketingQuestion.setProduct(null);

		return marketingQuestion;
	}
}
