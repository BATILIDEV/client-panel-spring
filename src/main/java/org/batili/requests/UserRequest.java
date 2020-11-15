package org.batili.requests;

import java.util.List;

public class UserRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Boolean admin;

	private List<AddressRequest> adresses;
	
	public List<AddressRequest> getAdresses() {
		return adresses;
	}
	public void setAdresses(List<AddressRequest> adresses) {
		this.adresses = adresses;
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
	@Override
	public String toString() {
		return "UserRequest [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", password="
				+ password + "]";
	}
	public Boolean getAdmin() {
		return admin;
	}
	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}


}
