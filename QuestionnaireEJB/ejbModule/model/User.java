package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="UserID")
	private int userID;

	@Column(name="Admin")
	private Object admin;

	@Column(name="Blocked")
	private Object blocked;

	@Column(name="Email")
	private String email;

	@Column(name="Password")
	private String password;

	@Column(name="Username")
	private String username;

	//bi-directional many-to-many association to Accesstime
	@ManyToMany(mappedBy="users")
	private List<Accesstime> accesstimes;

	//bi-directional many-to-one association to Questionnaire
	@OneToMany(mappedBy="user")
	private List<Questionnaire> questionnaires;

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="user")
	private List<Review> reviews;

	public User() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Object getAdmin() {
		return this.admin;
	}

	public void setAdmin(Object admin) {
		this.admin = admin;
	}

	public Object getBlocked() {
		return this.blocked;
	}

	public void setBlocked(Object blocked) {
		this.blocked = blocked;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Accesstime> getAccesstimes() {
		return this.accesstimes;
	}

	public void setAccesstimes(List<Accesstime> accesstimes) {
		this.accesstimes = accesstimes;
	}

	public List<Questionnaire> getQuestionnaires() {
		return this.questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

	public Questionnaire addQuestionnaire(Questionnaire questionnaire) {
		getQuestionnaires().add(questionnaire);
		questionnaire.setUser(this);

		return questionnaire;
	}

	public Questionnaire removeQuestionnaire(Questionnaire questionnaire) {
		getQuestionnaires().remove(questionnaire);
		questionnaire.setUser(null);

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
		review.setUser(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setUser(null);

		return review;
	}

}