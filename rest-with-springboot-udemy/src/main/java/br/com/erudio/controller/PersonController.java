package br.com.erudio.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.data.vo.v1.PersonVO;
import br.com.erudio.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

//@CrossOrigin //habilita a chamada de servico de dominios distintos para todo o endpoint
@Api(value = "Person Endpoint", description = "Description for Person", tags = { "PersonEndpoint" })
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

	@Autowired
	private PersonService service;

	@Autowired
	private PagedResourcesAssembler<PersonVO> assembler;

	// @CrossOrigin(origins="http://localhost:8080") //habilita a chamada de servico
	// de dominios apenas para o localhost
	@ApiOperation(value = "Find All person recorded")
	@GetMapping(value = "/", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Direction sortDirection = "desc".equals(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

		Page<PersonVO> persons = service.findAll(pageable);
		persons.stream().forEach(p -> {
			p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
		});

		PagedResources<?> resources = assembler.toResource(persons);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

	@ApiOperation(value = "Find All person by name")
	@GetMapping(value = "/findPersonByName/{name}", produces = { "application/json", "application/xml",
			"application/x-yaml" })
	public ResponseEntity<?> findPersonByName(@PathVariable(value = "name") String name,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {

		Direction sortDirection = "desc".equals(direction) ? Direction.DESC : Direction.ASC;

		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

		Page<PersonVO> persons = service.findPersonByName(name, pageable);
		persons.stream().forEach(p -> {
			p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
		});

		PagedResources<?> resources = assembler.toResource(persons);

		return new ResponseEntity<>(resources, HttpStatus.OK);
	}

//	@CrossOrigin(origins={"http://localhost:8080", "outrodominiopermitido.com.br"}) //
	@ApiOperation(value = "Find person by id")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public PersonVO findById(@PathVariable("id") Long id) {
		PersonVO vo = service.findById(id);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Create a new person")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO create(@RequestBody PersonVO person) {
		PersonVO vo = service.save(person);
		vo.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Update a person with given id")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = {
			"application/json", "application/xml", "application/x-yaml" })
	public PersonVO update(@RequestBody PersonVO person) {
		PersonVO vo = service.update(person);
		vo.add(linkTo(methodOn(PersonController.class).findById(person.getKey())).withSelfRel());
		return vo;
	}

	@ApiOperation(value = "Delete a person by id")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@ApiOperation(value = "Disables a person by id")
	@PatchMapping(value = "/{id}")
	public PersonVO disablePerson(@PathVariable("id") Long id) throws Exception {
		PersonVO vo = service.disablePerson(id);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
	}

}
