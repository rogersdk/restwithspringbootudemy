package br.com.erudio.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.erudio.model.Person;
import br.com.erudio.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private final AtomicLong counter = new AtomicLong();

	@Override
	public List<Person> findAll() {
		List<Person> list = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			Person person = mockPerson(i);
			list.add(person);
		}

		return list;
	}

	@Override
	public Person findById(String id) {
		Person person = new Person();

		person.setId(counter.incrementAndGet());
		person.setFirstName("FirstName");
		person.setLastName("LastName");
		person.setGender("male");
		person.setAddress("Rua sem nome");

		return person;
	}

	@Override
	public Person create(Person person) {

		return person;
	}

	@Override
	public Person update(Person person) {

		return person;
	}

	@Override
	public void delete(String id) {

	}

	private Person mockPerson(int i) {
		Person person = new Person();

		person.setId(counter.incrementAndGet());
		person.setFirstName("FirstName " + i);
		person.setLastName("LastName");
		person.setGender("male");
		person.setAddress("Rua sem nome");
		return person;
	}

}
