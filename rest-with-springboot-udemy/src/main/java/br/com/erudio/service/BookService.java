package br.com.erudio.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.erudio.data.vo.v1.BookVO;

public interface BookService {
	Page<BookVO> findAll(Pageable pageable);

	BookVO findById(Long id);

	BookVO save(BookVO person);

	BookVO update(BookVO person);

	void delete(Long id);
}
