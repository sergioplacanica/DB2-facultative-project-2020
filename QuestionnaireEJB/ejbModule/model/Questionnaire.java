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
@NamedQuery(name="Questionnaire.findAll", query="SELECT q FROM Questionnaire q")
public class Questionnaire implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuestionnairePK id;

	@Column(name="Age")
	private byte age;

	@Temporal(TemporalType.DATE)
	@Column(name="Date")
	private Date date;

	@Column(name="Expertise_level")
	private String expertise_level;

	@Column(name="Sex")
	private String sex;

	//bi-directional many-to-one association to Contain
	@OneToMany(mappedBy="questionnaire")
	private List<Contain> contains;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="ProductID")
	private Product product;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserID")
	private User user;

	public Questionnaire() {
	}

	public QuestionnairePK getId() {
		return this.id;
	}

	public void setId(QuestionnairePK id) {
		this.id = id;
	}

	public byte getAge() {
		return this.age;
	}

	public void setAge(byte age) {
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

	public List<Contain> getContains() {
		return this.contains;
	}

	public void setContains(List<Contain> contains) {
		this.contains = contains;
	}

	public Contain addContain(Contain contain) {
		getContains().add(contain);
		contain.setQuestionnaire(this);

		return contain;
	}

	public Contain removeContain(Contain contain) {
		getContains().remove(contain);
		contain.setQuestionnaire(null);

		return contain;
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