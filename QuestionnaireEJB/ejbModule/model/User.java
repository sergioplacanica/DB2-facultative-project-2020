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
	private byte admin;

	@Column(name="Blocked")
	private byte blocked;

	@Column(name="Email")
	private String email;

	@Column(name="Password")
	private String password;

	@Column(name="Username")
	private String username;

	//bi-directional many-to-many association to Accesstime
	@ManyToMany(mappedBy="users")
	private List<Accesstime> accesstimes;

	public User() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public byte getAdmin() {
		return this.admin;
	}

	public void setAdmin(byte admin) {
		this.admin = admin;
	}

	public byte getBlocked() {
		return this.blocked;
	}

	public void setBlocked(byte blocked) {
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

}