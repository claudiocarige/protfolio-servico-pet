package com.claudiocarige.portfoliclaudio.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.claudiocarige.portfoliclaudio.domain.ServicesPet;
import com.claudiocarige.portfoliclaudio.domain.dtos.ServicePetDTO;
import com.claudiocarige.portfoliclaudio.services.ServicePetService;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/servicepet")
public class ServicePetResource {

	@Autowired
	private ServicePetService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ServicePetDTO> findById(@PathVariable Integer id) {
		ServicesPet obj = service.findById(id);
		return ResponseEntity.ok().body(new ServicePetDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<ServicePetDTO>> findAll() {
		List<ServicesPet> list = service.finAll();
		List<ServicePetDTO> listDTO = list.stream().map(obj -> new ServicePetDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<ServicePetDTO> create(@Valid @RequestBody ServicePetDTO objDTO) {
		ServicesPet newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ServicePetDTO> update(@PathVariable Integer id, @Valid @RequestBody ServicePetDTO objDTO) {
		ServicesPet servicesPet = service.update(id, objDTO);
		return ResponseEntity.ok().body(new ServicePetDTO(servicesPet));
	}

}
