package com.claudiocarige.portfoliclaudio.resources;

import com.claudiocarige.portfoliclaudio.domain.ServicesPet;
import com.claudiocarige.portfoliclaudio.domain.dtos.ServicePetDTO;
import com.claudiocarige.portfoliclaudio.services.ServicePetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/servicepet")
public class ServicePetResource {

	@Autowired
	private ServicePetService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ServicePetDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new ServicePetDTO(service.findById(id)));
	}

	@GetMapping
	public ResponseEntity<List<ServicePetDTO>> findAll() {
		return ResponseEntity.ok()
				.body(service.finAll()
						.stream()
						.map(ServicePetDTO::new)
						.collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<ServicePetDTO> create(@Valid @RequestBody ServicePetDTO objDTO) {
		ServicesPet newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ServicePetDTO> update(@PathVariable Integer id, @Valid @RequestBody ServicePetDTO objDTO) {
		return ResponseEntity.ok().body(new ServicePetDTO(service.update(id, objDTO)));
	}
}
