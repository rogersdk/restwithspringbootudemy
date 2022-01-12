package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Book Endpoint", description = "Description for Book", tags = { "BookEndpoint" })
@RestController
@RequestMapping("/api/v1/book")
public class BookController {

	@Autowired
	private BookService service;

	@ApiOperation(value = "Find All book recorded")
	@GetMapping(value = "/", produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BookVO> findAll() {
		List<BookVO> list = service.findAll();
		list.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(BookController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return list;
	}

	@ApiOperation(value = "Find book by id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BookVO findById(@PathVariable("id") Long id) {
		BookVO vo = service.findById(id);
		vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Create a new book")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO create(@RequestBody BookVO book) {
		BookVO vo = service.save(book);
		vo.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Update a book with given id")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public BookVO update(@RequestBody BookVO book) {
		BookVO vo = service.update(book);
		vo.add(linkTo(methodOn(BookController.class).findById(book.getKey())).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Delete a book by id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}
