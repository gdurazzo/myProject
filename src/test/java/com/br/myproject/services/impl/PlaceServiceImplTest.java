package com.br.myproject.services.impl;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.myproject.domain.Place;
import com.br.myproject.repository.PlaceRepository;
import com.br.myproject.services.exception.ObjectNotFoundException;

@SpringBootTest
public class PlaceServiceImplTest {

	@InjectMocks
	private PlaceServiceImpl placeServiceImpl;
	
	@Mock
	PlaceRepository repository;
			
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void findById() {
		final String id = "123456789123456789";
		Place place = new Place();
		place.setId(id);
		final Optional<Place> obj = Optional.of(place);
		
		Mockito.when(repository.findById(id)).thenReturn(obj);
		
		Place placeTeste = placeServiceImpl.findById(id);
		
		Assert.assertEquals(place.getId(), placeTeste.getId());
		
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void findByIdNotFound() {
		final String id = "123456789123456789";
		Mockito.when(repository.findById(id)).thenThrow(ObjectNotFoundException.class);
		placeServiceImpl.findById(id);
	}
	
}
