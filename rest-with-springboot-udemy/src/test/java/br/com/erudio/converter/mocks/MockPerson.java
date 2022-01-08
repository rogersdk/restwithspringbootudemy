package br.com.erudio.converter.mocks;

import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;

public class MockPerson {
	public Person mockEntity() {
		return mockEntity(0);
	}

	public PersonVO mockVO() {
		return mockVO(0);
	}

	public List<Person> mockEntityList() {
		List<Person> persons = new ArrayList<>();

		for (int i = 0; i < 14; i++) {
			persons.add(mockEntity(i));
		}

		return persons;
	}

	public List<PersonVO> mockVOList() {
		List<PersonVO> persons = new ArrayList<>();

		for (int i = 0; i < 14; i++) {
			persons.add(mockVO(i));
		}

		return persons;
	}

	private Person mockEntity(Integer i) {
		Person p = new Person();
		p.setAddress("Addres Test" + i);
		p.setFirstName("First Name Test" + i);
		p.setGender((i % 2 == 0) ? "Male" : "Female");
		p.setId(i.longValue());
		p.setLastName("Last Name Test" + i);

		return p;
	}

	private PersonVO mockVO(Integer i) {
		PersonVO p = new PersonVO();
		p.setAddress("Addres Test" + i);
		p.setFirstName("First Name Test" + i);
		p.setGender( (i % 2 == 0) ? "Male" : "Female");
		p.setId(i.longValue());
		p.setLastName("Last Name Test" + i);
		return p;
	}

}
