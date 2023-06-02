package com.claudiocarige.portfoliclaudio.resources;

import com.claudiocarige.portfoliclaudio.domain.Client;
import com.claudiocarige.portfoliclaudio.domain.dtos.ClientDTO;
import com.claudiocarige.portfoliclaudio.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
@RequiredArgsConstructor
public class ClientResource {

	private final ClientService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new ClientDTO(service.findById(id)));
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll() {
		return ResponseEntity.ok()
				.body(service.findAll()
						.stream()
						.map(ClientDTO::new)
						.collect(Collectors.toList()));
	}

	@PostMapping
	public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO objDTO) {
		Client newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Integer id, @Valid @RequestBody ClientDTO objDTO) {
		return ResponseEntity.ok().body(new ClientDTO(service.update(id, objDTO)));
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
