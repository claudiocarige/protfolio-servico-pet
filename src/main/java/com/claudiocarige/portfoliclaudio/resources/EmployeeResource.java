package com.claudiocarige.portfoliclaudio.resources;

import com.claudiocarige.portfoliclaudio.domain.Employee;
import com.claudiocarige.portfoliclaudio.domain.dtos.EmployeeDTO;
import com.claudiocarige.portfoliclaudio.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/employees")
@RequiredArgsConstructor
public class EmployeeResource {

	private final EmployeeService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployeeDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new EmployeeDTO(service.findById(id)));
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> findAll() {
		return ResponseEntity.ok()
				.body(service.findAll()
						.stream()
						.map(EmployeeDTO::new)
						.collect(Collectors.toList()));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<EmployeeDTO> create(@Valid @RequestBody EmployeeDTO objDTO){
		Employee newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
					buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build(); 
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<EmployeeDTO> update(@PathVariable Integer id, 
												@Valid @RequestBody EmployeeDTO objDTO){
		return ResponseEntity.ok().body(new EmployeeDTO(service.update(id, objDTO)));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value ="/{id}")
	public ResponseEntity<EmployeeDTO> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();	
	}
} 
