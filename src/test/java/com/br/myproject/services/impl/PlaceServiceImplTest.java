package com.br.myproject.services.impl;

import java.util.ArrayList;
import java.util.List;
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
import com.br.myproject.dto.PlaceDTO;
import com.br.myproject.repository.PlaceRepository;
import com.br.myproject.services.exception.ObjectAlreadyExistException;
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
	
	@Test
	public void findByName() {
		final String name = "teste";
		Place obj = new Place();
		obj.setName(name);
		Mockito.when(repository.findByNameIgnoreCase(name)).thenReturn(obj);
		Place place = placeServiceImpl.findByName(name);
		Assert.assertEquals(obj.getName(), place.getName());
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void findByNameNotFound() {
		final String name = "teste";
		Mockito.when(repository.findByNameIgnoreCase(name)).thenThrow(ObjectNotFoundException.class);
		placeServiceImpl.findByName(name);
	}
	
	@Test
	public void findAllByCity() {
		String city = "cidade";
		List<Place> places = new ArrayList<>();
		Place place1 = new Place();
		Place place2 = new Place();
		places.add(place1);
		places.add(place2);
		Mockito.when(repository.findAllByCityIgnoreCase(city)).thenReturn(places);
		
		List<Place> placesReturn = placeServiceImpl.findAllByCity(city);
		Assert.assertEquals(places.size(), placesReturn.size());
	}
	
	@Test
	public void findAllByCityIsEmpty() {
		String city = "cidade";
		List<Place> places = new ArrayList<>();
		Mockito.when(repository.findAllByCityIgnoreCase(city)).thenReturn(places);
		List<Place> placesReturn = placeServiceImpl.findAllByCity(city);
		Assert.assertEquals(places.size(), placesReturn.size());
	}
	
	@Test
	public void insert() {
		Place obj = new Place();
		obj.setName("teste");
		Mockito.when(repository.insert(obj)).thenReturn(obj);
		Place obj2 = placeServiceImpl.insert(obj);
		Assert.assertEquals(obj.getName(), obj2.getName());
	}
	
	@Test(expected = ObjectAlreadyExistException.class)
	public void insertException() {
		Place obj = new Place();
		obj.setName("teste");
		Mockito.when(repository.insert(obj)).thenThrow(org.springframework.dao.DuplicateKeyException.class);
		placeServiceImpl.insert(obj);
	}
	
	@Test
	public void update() {
		Place placeNew = new Place();
		placeNew.setId("1234566788");
		placeNew.setName("new");
		
		Place placeOld = new Place();
		placeOld.setId("1234566788");
		placeOld.setName("old");
		
		final Optional<Place> obj = Optional.of(placeOld);
		Mockito.when(repository.findById("1234566788")).thenReturn(obj);
		Mockito.when(repository.save(placeNew)).thenReturn(placeNew);
		
		Place placeUpdate = placeServiceImpl.update(placeNew);
		Assert.assertEquals(placeNew.getName(), placeUpdate.getName());
		
	}
	
	
	@Test(expected = ObjectNotFoundException.class)
	public void updateObjNotFound() {
		Place placeNew = new Place();
		placeNew.setId("1234566788");
		placeNew.setName("new");
		
		Place placeOld = new Place();
		placeOld.setId("1234566788");
		placeOld.setName("old");
		
		Mockito.when(repository.findById("1234566788")).thenThrow(ObjectNotFoundException.class);

		placeServiceImpl.update(placeNew);
		
	}
	
	@Test(expected = ObjectNotFoundException.class)
	public void deleteObjNotFound() {
		Place place = new Place();
		place.setId("123456");
		Mockito.when(repository.findById("123456")).thenThrow(ObjectNotFoundException.class);
		placeServiceImpl.update(place);
	}
	
	
	@Test
	public void fromDto() {
		PlaceDTO placeDto = new PlaceDTO();
		placeDto.setName("São Paulo");
		placeDto.setCity("São Paulo");
		placeDto.setState("São Paulo");
		
		Place place = placeServiceImpl.fromDTO(placeDto);
		
		Assert.assertEquals(placeDto.getCity(), place.getCity());
		Assert.assertEquals(placeDto.getState(), place.getState());
		Assert.assertEquals(placeDto.getName(), place.getName());
		Assert.assertEquals("sao-paulo", place.getSlug());
		

		
	}
	
}
