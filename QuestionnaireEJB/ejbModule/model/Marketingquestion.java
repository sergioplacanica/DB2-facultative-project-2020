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
	private List<Answer> answer;

	//bi-directional many-to-one association to Marketingquestion
	@ManyToOne
	@JoinColumn(name="ProductID")

	private Product product;
	
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

	public List<Answer> getAnswer() {
		return this.answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

	public Answer addAnswer(Answer answer) {
		getAnswer().add(answer);
		answer.setMarketingquestion(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswer().remove(answer);
		answer.setMarketingquestion(null);

		return answer;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProductID() {
		return this.product;
	}	

}
