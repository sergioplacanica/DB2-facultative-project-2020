package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Contain database table.
 * 
 */
@Embeddable
public class AnswerPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="UserID", insertable=false, updatable=false)
	private int userID;

	@Column(name="ProductID", insertable=false, updatable=false)
	private int productID;

	@Column(name="QuestionID", insertable=false, updatable=false)
	private int questionID;

	public AnswerPK() {
	}
	public int getUserID() {
		return this.userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getProductID() {
		return this.productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getQuestionID() {
		return this.questionID;
	}
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AnswerPK)) {
			return false;
		}
		AnswerPK castOther = (AnswerPK)other;
		return 
			(this.userID == castOther.userID)
			&& (this.productID == castOther.productID)
			&& (this.questionID == castOther.questionID);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userID;
		hash = hash * prime + this.productID;
		hash = hash * prime + this.questionID;
		
		return hash;
	}
}