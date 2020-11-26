package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Contain database table.
 * 
 */
@Entity
@NamedQuery(name="Contain.findAll", query="SELECT c FROM Contain c")
public class Contain implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ContainPK id;

	@Column(name="Answer_text")
	private Object answer_text;

	@Column(name="Offensive")
	private Object offensive;

	//bi-directional many-to-one association to Marketingquestion
	@ManyToOne
	@JoinColumn(name="QuestionID")
	private Marketingquestion marketingquestion;

	//bi-directional many-to-one association to Questionnaire
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ProductID", referencedColumnName="ProductID"),
		@JoinColumn(name="UserID", referencedColumnName="UserID")
		})
	private Questionnaire questionnaire;

	public Contain() {
	}

	public ContainPK getId() {
		return this.id;
	}

	public void setId(ContainPK id) {
		this.id = id;
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

	public Marketingquestion getMarketingquestion() {
		return this.marketingquestion;
	}

	public void setMarketingquestion(Marketingquestion marketingquestion) {
		this.marketingquestion = marketingquestion;
	}

	public Questionnaire getQuestionnaire() {
		return this.questionnaire;
	}

	public void setQuestionnaire(Questionnaire questionnaire) {
		this.questionnaire = questionnaire;
	}

}