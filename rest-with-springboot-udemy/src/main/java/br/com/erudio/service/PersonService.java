package br.com.erudio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.erudio.data.vo.v1.PersonVO;

public interface PersonService {

	/**
	 * @param pageable
	 * @return
	 */
	Page<PersonVO> findAll(Pageable pageable);

	/**
	 * @param firstName
	 * @param pageable
	 * @return
	 */
	Page<PersonVO> findPersonByName(String firstName, Pageable pageable);

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
