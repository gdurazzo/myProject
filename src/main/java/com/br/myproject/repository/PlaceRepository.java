package com.br.myproject.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.br.myproject.domain.Place;

@Repository
public interface PlaceRepository extends MongoRepository<Place, String>{
	
	Place findBySlug(String slug);
	
	Place findByNameIgnoreCase(String name);
	
	List<Place> findAllByCityIgnoreCase(String city);

}
