package org.batili.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.batili.shared.dto.UserDto;

@Entity(name="adresses")
public class AdressEntity implements Serializable {
	
	private static final long serialVersionUID = -8093604331877407268L;
	@Id@GeneratedValue
	private long id;
	@Column(nullable = false, length = 40)
	private String adressId;
	

	@Column(nullable = false, length = 40)
	private String country;
	@Column(nullable = false, length = 40)

	private String city;
	@Column
	private String code_postal;

	@Column(nullable = false)

	private String street;

	@Column(nullable = false, length = 40)

	private String type;
@ManyToOne
@JoinColumn(name="users_id")
	private UserEntity user;

	public AdressEntity() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}



	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getAdressId() {
		return adressId;
	}

	public void setAdressId(String adressId) {
		this.adressId = adressId;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	
}
