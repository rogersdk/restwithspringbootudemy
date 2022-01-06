package br.com.erudio.service;

import java.util.List;

import br.com.erudio.model.Person;

public interface PersonService {
	List<Person> findAll();

	Person findById(String id);
	
	Person create(Person person);
	
	Person update(Person person);
	
	void delete(String id);
}
