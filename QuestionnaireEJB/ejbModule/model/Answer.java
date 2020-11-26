package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Answer database table.
 * 
 */
@Entity
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Answer_ID")
	private int answer_ID;

	@Column(name="Answer_text")
	private Object answer_text;

	@Column(name="Offensive")
	private Object offensive;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="UserID")
	private User user;

	//bi-directional many-to-one association to Questionnaire
	@ManyToOne
	@JoinColumn(name="QuestionnaireID")
	private Questionnaire questionnaire;

	//bi-directional many-to-one association to Marketingquestion
	@ManyToOne
	@JoinColumn(name="QuestionID")
	private Marketingquestion marketingquestion;

	public Answer() {
	}

	public int getAnswer_ID() {
		return this.answer_ID;
	}

	public void setAnswer_ID(int answer_ID) {
		this.answer_ID = answer_ID;
	}

	public Object getAnswer_text() {
		return this.answer_text;
	}

	public void setAnswer_text(Object answer_text) {
		this.answer_text = answer_text;
	}

	public Object getOffensive() {
		return this.offensive;
	}

	public void setOffensive(Object offensive) {
		this.offensive = offensive;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Questionnaire getQuestionnaire() {
		return this.questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

	public Marketingquestion getMarketingquestion() {
		return this.marketingquestion;
	}

	public void setMarketingquestion(Marketingquestion marketingquestion) {
		this.marketingquestion = marketingquestion;
	}

}