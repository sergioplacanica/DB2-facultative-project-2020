package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Accesstime database table.
 * 
 */
@Entity
@NamedQuery(name="Accesstime.findAll", query="SELECT a FROM Accesstime a")
public class Accesstime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Access_time")
	private Date access_time;

	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="Accesslog"
		, joinColumns={
			@JoinColumn(name="Access_time")
			}
		, inverseJoinColumns={
			@JoinColumn(name="UserID")
			}
		)
	private List<User> users;

	public Accesstime() {
	}

	public Date getAccess_time() {
		return this.access_time;
	}

	public void setAccess_time(Date access_time) {
		this.access_time = access_time;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}