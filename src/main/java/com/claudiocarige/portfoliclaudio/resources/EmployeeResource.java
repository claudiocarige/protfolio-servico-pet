package com.claudiocarige.portfoliclaudio.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claudiocarige.portfoliclaudio.domian.Employee;
import com.claudiocarige.portfoliclaudio.domian.dtos.EmployeeDTO;
import com.claudiocarige.portfoliclaudio.services.EmployeeService;

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
		List<EmployeeDTO> listDTO = list.stream().map(obj -> new EmployeeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}
