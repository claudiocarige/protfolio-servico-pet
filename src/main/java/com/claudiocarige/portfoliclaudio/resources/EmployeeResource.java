package com.claudiocarige.portfoliclaudio.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudiocarige.portfoliclaudio.domain.Employee;
import com.claudiocarige.portfoliclaudio.domain.dtos.EmployeeDTO;
import com.claudiocarige.portfoliclaudio.services.EmployeeService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/employeies")
public class EmployeeResource {

	@Autowired
	private EmployeeService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployeeDTO> findById(@PathVariable Integer id) {
		Employee obj = service.findById(id);
		return ResponseEntity.ok().body(new EmployeeDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> findAll() {
		List<Employee> list = service.findAll();
		List<EmployeeDTO> listDTO = list.stream().map(obj -> new EmployeeDTO(obj)).
									collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping
	public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO objDTO){
		Employee newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
					buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build(); 
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<EmployeeDTO> update(@PathVariable Integer id, 
												@Valid @RequestBody EmployeeDTO objDTO){
		Employee obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new EmployeeDTO(obj));
	}
	
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<EmployeeDTO> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
				
	}
}
