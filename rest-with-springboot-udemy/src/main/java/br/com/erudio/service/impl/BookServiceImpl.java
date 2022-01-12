package br.com.erudio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repository.BookRepository;
import br.com.erudio.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository repository;

	@Override
	public List<BookVO> findAll() {
		return DozerConverter.parseList(repository.findAll(), BookVO.class);
	}

	@Override
	public BookVO findById(Long id) {
		Book b = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

		return DozerConverter.parseObject(b, BookVO.class);
	}

	@Override
	public BookVO save(BookVO book) {
		Book entity = repository.save(DozerConverter.parseObject(book, Book.class));
		BookVO vo = DozerConverter.parseObject(entity, BookVO.class);
		return vo;
	}

	@Override
	public BookVO update(BookVO book) {
		Book entity = repository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());

		entity = repository.save(DozerConverter.parseObject(entity, Book.class));
		BookVO vo = DozerConverter.parseObject(entity, BookVO.class);

		return vo;
	}

	@Override
	public void delete(Long id) {
		Book entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID."));

		repository.delete(entity);
	}

}
