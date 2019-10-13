package com.br.myproject.services;

import java.util.List;

import com.br.myproject.domain.Place;
import com.br.myproject.dto.PlaceDTO;

public interface PlaceService {

	public List<Place> findAll();
	
	public Place findById(String id);

	public Place findByName(String name);

	public List<Place> findAllByCity(String city);
	
	public List<Place> findAllByNameContains(String name);

	public Place fromDTO(PlaceDTO objDto);
	
	public Place insert(Place place);
	
	public Place update(Place place);

	public void delete(String id);
}
