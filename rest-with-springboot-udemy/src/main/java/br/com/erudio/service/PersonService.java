package br.com.erudio.service;

import java.util.List;

import br.com.erudio.data.vo.v1.PersonVO;

public interface PersonService {
	List<PersonVO> findAll();

	PersonVO findById(Long id);

	PersonVO save(PersonVO person);

	PersonVO update(PersonVO person);

	void delete(Long id);
}
