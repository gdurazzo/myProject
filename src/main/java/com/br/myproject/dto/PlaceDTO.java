package com.br.myproject.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.br.myproject.domain.Place;

public class PlaceDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private String name;
	@NotNull
	private String city;
	@NotNull
	private String state;
	
	public PlaceDTO() {
		
	}
	
	public PlaceDTO(Place place) {
		name = place.getName();
		city = place.getCity();
		state = place.getState();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	
}
