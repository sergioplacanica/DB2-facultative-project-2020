package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Questionnaire database table.
 * 
 */
@Entity
//@Table(name = "Questionnaire", schema = "database")
@NamedQueries({
	@NamedQuery(name="Questionnaire.findAll", query="SELECT q FROM Questionnaire q"),
	
	
})

public class Questionnaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuestionnairePK id;

	@Column(name="Age")
	private int age;

	@Temporal(TemporalType.DATE)
	@Column(name="Date")
	private Date date;

	@Column(name="Expertise_level")
	private String expertise_level;

	@Column(name="Sex")
	private String sex;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="questionnaire", fetch=FetchType.EAGER)
	private List<Answer> answer;

	//bi-directional many-to-one association to Product
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ProductID")
	private Product product;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserID")
	private User user;

	public Questionnaire(int age, Date date, String exLvl, String sex, User user, Product product) {
		this.age = age;
		this.date = date;
		this.expertise_level = exLvl;
		this.sex = sex;
		this.user = user;
		this.product = product;
		
	}
	
	public Questionnaire() {
		
	}

	public QuestionnairePK getId() {
		return this.id;
	}

	public void setId(QuestionnairePK id) {
		this.id = id;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public List<Answer> getAnswer() {
		return this.answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

	public Answer addAnswer(Answer answer) {
		getAnswer().add(answer);
		answer.setQuestionnaire(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswer().remove(answer);
		answer.setQuestionnaire(null);

		return answer;
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
