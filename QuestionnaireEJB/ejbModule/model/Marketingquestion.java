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

	//bi-directional many-to-one association to Contain
	@OneToMany(mappedBy="marketingquestion")
	private List<Contain> contains;

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

	public List<Contain> getContains() {
		return this.contains;
	}

	public void setContains(List<Contain> contains) {
		this.contains = contains;
	}

	public Contain addContain(Contain contain) {
		getContains().add(contain);
		contain.setMarketingquestion(this);

		return contain;
	}

	public Contain removeContain(Contain contain) {
		getContains().remove(contain);
		contain.setMarketingquestion(null);

		return contain;
	}

}