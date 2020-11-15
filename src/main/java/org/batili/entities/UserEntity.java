package org.batili.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="users")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = -4460754252324717185L;
	@Id@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
//	@Column(nullable = false)
	private String userId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false,unique = true)
	private String email;
	@Column(nullable = false)
	private String passwordEncrypted;
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<AdressEntity> adresses;
	@Column(nullable = true)
	private Boolean admin=false;
	
	public UserEntity() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordEncrypted() {
		return passwordEncrypted;
	}

	public void setPasswordEncrypted(String passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}

	public List<AdressEntity> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<AdressEntity> adresses) {
		this.adresses = adresses;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	 
}
