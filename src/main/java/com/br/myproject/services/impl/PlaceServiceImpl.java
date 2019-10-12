package com.br.myproject.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.myproject.domain.Place;
import com.br.myproject.dto.PlaceDTO;
import com.br.myproject.repository.PlaceRepository;
import com.br.myproject.services.PlaceService;
import com.br.myproject.services.exception.ObjectAlreadyExistException;
import com.br.myproject.services.exception.ObjectNotFoundException;
import com.github.slugify.Slugify;

@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	PlaceRepository repository;

	public List<Place> findAll() {
		return repository.findAll();
	}

	public Place insertPlace() {
		// TODO Auto-generated method stub
		return null;
	}

	public Place findById(String id) {
		Optional<Place> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object Not Found"));
	}

	@Override
	public Place findBySlug(String slug) {
		Place obj = repository.findBySlug(slug);
		if (obj == null) {
			throw new ObjectNotFoundException("Object Not Found");
		}
		return obj;
	}

	@Override
	public Place findByName(String name) {
		Place obj = repository.findByNameIgnoreCase(name);
		if (obj == null) {
			throw new ObjectNotFoundException("Object Not Found");
		}
		return obj;
	}

	@Override
	public List<Place> findAllByCity(String city) {
		return repository.findAllByCityIgnoreCase(city);
	}

	@Override
	public Place insert(Place obj) {
		Place place = null;
		try {
			place = repository.insert(obj);
		} catch (org.springframework.dao.DuplicateKeyException e) {
			throw new ObjectAlreadyExistException("Object already created");
		}
		return place;
	}

	public Place update(Place obj) {
		Place newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	private void updateData(Place newObj, Place obj) {
		newObj.setName(obj.getName());
		newObj.setSlug(obj.getSlug());
		newObj.setCity(obj.getCity());
		newObj.setState(obj.getState());
		newObj.setUpdatedAt(obj.getUpdatedAt());

	}


	public Place fromDTO(PlaceDTO objDto) {
		Slugify slg = new Slugify();
		String result = slg.slugify(objDto.getName());
		return new Place(null, objDto.getName(), result, objDto.getCity(), objDto.getState());

	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

}
