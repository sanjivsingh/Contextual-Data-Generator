package com.sanjivsingh.datagenerator.core.codegenerator.temp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	public User() {
	}

	@Column(name = "first_name")
	private String first_name;

	public String getfirst_name() {
		return first_name;
	}

	public void setfirst_name(String first_name) {
		this.first_name = first_name;
	}

	@Id
	private String userId;

	public String getuserId() {
		return userId;
	}

	public void setuserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "last_name")
	private String last_name;

	public String getlast_name() {
		return last_name;
	}

	public void setlast_name(String last_name) {
		this.last_name = last_name;
	}

	@Column(name = "city")
	private String city;

	public String getcity() {
		return city;
	}

	public void setcity(String city) {
		this.city = city;
	}
}