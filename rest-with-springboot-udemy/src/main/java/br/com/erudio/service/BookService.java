package br.com.erudio.service;

import java.util.List;

import br.com.erudio.data.vo.v1.BookVO;

public interface BookService {
	List<BookVO> findAll();

	BookVO findById(Long id);

	BookVO save(BookVO person);

	BookVO update(BookVO person);

	void delete(Long id);
}
