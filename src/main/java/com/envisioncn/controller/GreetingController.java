package com.envisioncn.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.envisioncn.entity.Greeting;
import com.envisioncn.exception.UserNotFoundException;
import com.envisioncn.repository.GreetingRepository;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
    @Autowired
    private GreetingRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Greeting greeting(@PathVariable final Long id) {
    	return repository.findById(id).orElseThrow(
    			() ->  new UserNotFoundException(id+""));
    }
    
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Greeting input){
    	//Greeting greeting = new Greeting();
    	//greeting.setContent(input.getContent());
    	Greeting greeting = repository.save(input);
    	HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(greeting.getId()).toUri());
    	return new ResponseEntity<>(null, httpHeaders, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/{id}",method = RequestMethod.PUT)
    ResponseEntity<?> add(@PathVariable Long id, @RequestBody Greeting input){
    	Greeting greeting = repository.findById(id).orElseThrow(
    			() ->  new UserNotFoundException(id+""));
    	greeting.setContent(input.getContent());
    	repository.save(greeting);    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
