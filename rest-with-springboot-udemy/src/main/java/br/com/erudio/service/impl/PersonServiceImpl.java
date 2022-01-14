package br.com.erudio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Person;
import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.PersonRepository;
import br.com.erudio.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository repository;

	@Override
	public Page<PersonVO> findAll(Pageable pageable) {
		Page<Person> page = repository.findAll(pageable);
		return page.map(this::convertToPersonVO);
	}

	@Override
	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
		Page<Person> page = repository.findPersonByName(firstName, pageable);
		return page.map(this::convertToPersonVO);
	}

	private PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	@Override
	public PersonVO findById(Long id) {
		Person p = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

		return DozerConverter.parseObject(p, PersonVO.class);
	}

	@Override
	public PersonVO save(PersonVO person) {
		Person entity = repository.save(DozerConverter.parseObject(person, Person.class));
		PersonVO vo = DozerConverter.parseObject(entity, PersonVO.class);
		return vo;
	}

	@Override
	public PersonVO update(PersonVO person) {
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());

		entity = repository.save(DozerConverter.parseObject(entity, Person.class));
		PersonVO vo = DozerConverter.parseObject(entity, PersonVO.class);

		return vo;
	}

	@Override
	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

		repository.delete(entity);
	}

	@Override
	@Transactional
	public PersonVO disablePerson(Long id) {
		repository.disablePerson(id);

		PersonVO vo = findById(id);
		return vo;
	}

}
