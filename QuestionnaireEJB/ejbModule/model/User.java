package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
//@Table(name = "user", schema = "database")
@NamedQueries({@NamedQuery(name="User.findAll", query="SELECT u FROM User u order by u.points desc"),
@NamedQuery(name="User.checkCredentials", query="SELECT u FROM User u WHERE u.username= ?1 and u.password= ?2")}
)


public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="UserID")
	private int userID;

	@Column(name="Admin")
	private Boolean admin=false;

	@Column(name="Blocked")
	private Boolean blocked=false;

	@Column(name="Email")
	private String email;

	@Column(name="Password")
	private String password;

	@Column(name="Username")
	private String username;
	
	@Column(name="Points")
	private int points;

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

	public User(String username, String password, String email) {
		this.username=username;
		this.password=password;
		this.email=email;
		
	}
	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public Boolean getBlocked() {
		return this.blocked;
	}

	public void setBlocked(Boolean blocked) {
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

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
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