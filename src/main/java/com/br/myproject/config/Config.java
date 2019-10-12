package com.br.myproject.config;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.br.myproject.domain.Place;
import com.br.myproject.repository.PlaceRepository;
import com.github.slugify.Slugify;

@Configuration
public class Config implements CommandLineRunner {

	@Autowired
	PlaceRepository repository;

	@Override
	public void run(String... args) throws Exception {
		
		Slugify slg = new Slugify();
		String result = slg.slugify("Lago da batata");
		String result2 = slg.slugify("São Paulo");
		
		Place place1 = new Place(null, "Lago da batata", result, "São Paulo", "São Paulo",
				Instant.now(), Instant.now());
		
		
		Place place2 = new Place(null, "deus", result2, "São Paulo", "São Paulo",
				Instant.now(), Instant.now());
		repository.deleteAll();
		repository.save(place1);
		repository.save(place2);
//		repository.save(place2); MongoWriteException
		
	}

}
