package br.com.erudio.service;

import java.util.List;

import br.com.erudio.data.vo.v1.PersonVO;

public interface PersonService {
	List<PersonVO> findAll();

	/**
	 * @param id
	 * @return
	 */
	PersonVO findById(Long id);

	/**
	 * @param person
	 * @return
	 */
	PersonVO save(PersonVO person);

	/**
	 * @param person
	 * @return
	 */
	PersonVO update(PersonVO person);

	/**
	 * @param id
	 */
	void delete(Long id);

	/**
	 * @param id
	 * @return
	 */
	PersonVO disablePerson(Long id);
}
