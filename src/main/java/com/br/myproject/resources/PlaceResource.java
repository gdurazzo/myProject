package com.br.myproject.resources;

import java.net.URI;
import java.time.Instant;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.myproject.domain.Place;
import com.br.myproject.dto.PlaceDTO;
import com.br.myproject.resources.util.URL;
import com.br.myproject.services.PlaceService;

@RestController
@RequestMapping(value = "/places")
public class PlaceResource {

	@Autowired
	PlaceService placeService;
	
	@GetMapping
	public ResponseEntity<List<Place>> findAll(){
		List<Place> places = placeService.findAll();
		return ResponseEntity.ok().body(places);
	}
	
//	@GetMapping(value = "/{slug}")
//	public ResponseEntity<Place> findBySlug(@PathVariable String slug){
//		Place place = placeService.findBySlug(slug);
//		return ResponseEntity.ok().body(place)
//	}
	
	@GetMapping(value = "/placename")
	public ResponseEntity<Place> findByName(@RequestParam(value="name", defaultValue = "") String name){
		name = URL.decodeParam(name);
		Place place = placeService.findByName(name);
		return ResponseEntity.ok().body(place);
	}
	
	@GetMapping(value = "/cities")
	public ResponseEntity<List<Place>> findAllByCity(@RequestParam(value="city", defaultValue = "") String city){
		city = URL.decodeParam(city);
		List<Place> places = placeService.findAllByCity(city);
		return ResponseEntity.ok().body(places);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PlaceDTO> findById(@PathVariable String id) {

		Place place = placeService.findById(id);
		
		return ResponseEntity.ok().body(new PlaceDTO(place));
	}
	
	
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PlaceDTO objDto) {

		Place place = placeService.fromDTO(objDto);
		place.setCreatedAt(Instant.now());
		place.setUpdatedAt(Instant.now());
		place = placeService.insert(place);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(place.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PlaceDTO objDto, @PathVariable String id) {

		Place obj = placeService.fromDTO(objDto);
		obj.setId(id);
		obj.setUpdatedAt(Instant.now());
		obj = placeService.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		
		placeService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
