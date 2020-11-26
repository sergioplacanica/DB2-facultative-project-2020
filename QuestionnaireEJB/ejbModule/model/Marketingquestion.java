package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the Marketingquestion database table.
 * 
 */
@Entity
@NamedQuery(name="Marketingquestion.findAll", query="SELECT m FROM Marketingquestion m")
public class Marketingquestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="QuestionID")
	private int questionID;

	@Column(name="Description")
	private String description;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="marketingquestion")
	private List<Answer> answers;

	//bi-directional many-to-many association to Questionnaire
	@ManyToMany
	@JoinTable(
		name="Contain"
		, joinColumns={
			@JoinColumn(name="QuestionID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="QuestionnaireID")
			}
		)
	private List<Questionnaire> questionnaires;

	public Marketingquestion() {
	}

	public int getQuestionID() {
		return this.questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setMarketingquestion(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setMarketingquestion(null);

		return answer;
	}

	public List<Questionnaire> getQuestionnaires() {
		return this.questionnaires;
	}

	public void setQuestionnaires(List<Questionnaire> questionnaires) {
		this.questionnaires = questionnaires;
	}

}