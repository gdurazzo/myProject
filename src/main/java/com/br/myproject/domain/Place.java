package com.br.myproject.domain;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Place {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;
	private String slug;
	private String city;
	private String state;
	private Date createdAt;
	private Date updatedAt;
	
	public Place() {
		
	}

	public Place(String name, String city, String state, Date createdAt, Date updatedAt) {
		super();
		this.name = name;
		this.city = city;
		this.state = state;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", name=" + name + ", slug=" + slug + ", city=" + city + ", state=" + state
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
