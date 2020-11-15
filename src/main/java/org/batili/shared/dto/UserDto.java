package org.batili.shared.dto;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {
	
	private static final long serialVersionUID = 45017486154293878L;
	private long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean admin;

	private String password;
	private String passwordEncrypted;
	private List<AdressesDto> adresses;
	public List<AdressesDto> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<AdressesDto> adresses) {
		this.adresses = adresses;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordEncrypted() {
		return passwordEncrypted;
	}
	public void setPasswordEncrypted(String passwordEncrypted) {
		this.passwordEncrypted = passwordEncrypted;
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}


}
