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
//@Table(name = "accesstime", schema = "database")
@NamedQuery(name="Accesstime.findAll", query="SELECT a FROM Accesstime a")
public class Accesstime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int acessTimeID;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="Access_time")
	private Date access_time;

	//bi-directional many-to-many association to User
	@ManyToOne
	@JoinColumn(name="user")
	private User user;

	public Accesstime() {
	}

	public Date getAccess_time() {
		return this.access_time;
	}

	public void setAccess_time(Date access_time) {
		this.access_time = access_time;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}