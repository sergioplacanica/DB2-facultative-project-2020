package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Questionnaire database table.
 * 
 */
@Entity
@NamedQuery(name="Questionnaire.findAll", query="SELECT q FROM Questionnaire q")
public class Questionnaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="QuestionnaireID")
	private int questionnaireID;

	@Column(name="Age")
	private byte age;

	@Column(name="Expertise_level")
	private String expertise_level;

	@Column(name="Sex")
	private String sex;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="questionnaire")
	private List<Answer> answers;

	//bi-directional many-to-many association to Marketingquestion
	@ManyToMany(mappedBy="questionnaires")
	private List<Marketingquestion> marketingquestions;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserID")
	private User user;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="ProductID")
	private Product product;

	public Questionnaire() {
	}

	public int getQuestionnaireID() {
		return this.questionnaireID;
	}

	public void setQuestionnaireID(int questionnaireID) {
		this.questionnaireID = questionnaireID;
	}

	public byte getAge() {
		return this.age;
	}

	public void setAge(byte age) {
		this.age = age;
	}

	public String getExpertise_level() {
		return this.expertise_level;
	}

	public void setExpertise_level(String expertise_level) {
		this.expertise_level = expertise_level;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setQuestionnaire(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setQuestionnaire(null);

		return answer;
	}

	public List<Marketingquestion> getMarketingquestions() {
		return this.marketingquestions;
	}

	public void setMarketingquestions(List<Marketingquestion> marketingquestions) {
		this.marketingquestions = marketingquestions;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}