package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Offensiveword database table.
 * 
 */
@Entity
@NamedQuery(name="Offensiveword.findAll", query="SELECT o FROM Offensiveword o")
public class Offensiveword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="Word")
	private String word;

	public Offensiveword() {
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}