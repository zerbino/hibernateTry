package com.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="adresses_02")
public class Adress {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "adresses_id")
	private Integer id;
	
	@Column(name = "adresses_name", nullable=false)
	private String name;
	
	@Column(name = "adresses_street")
	private String street;
	
	@Column(name = "adresses_comments")
	private String comments;
	
	@Column(name ="adresses_zipcode")
	private String zipcode;
	
	@Column(name = "adresses_city")
	private String city;
	
	public Adress(){
		
	}
	
	

	public Adress(String name, String street, String zipcode, String city) {
		this.name = name;
		this.street = street;
		this.zipcode = zipcode;
		this.city = city;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		String toRet;
		toRet = this.name+(char)10;
		toRet += this.street+(char)10;
		toRet+= this.city+(char)10;
		toRet += this.zipcode+(char)10;
		toRet += this.comments+(char)10;
		return toRet;
	}
}
