package com.envisioncn.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.envisioncn.entity.Greeting;

public interface GreetingRepository extends CrudRepository<Greeting, Long>{

	Optional<Greeting> findById(Long id);
}
