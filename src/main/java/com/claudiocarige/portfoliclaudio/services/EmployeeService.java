package com.claudiocarige.portfoliclaudio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claudiocarige.portfoliclaudio.domian.Employee;
import com.claudiocarige.portfoliclaudio.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repository;
	
	public Employee findById(Integer id) {
		Optional<Employee> employee = repository.findById(id);
		return employee.orElse(null);
	}
	
	public List<Employee> findAll(){
		return repository.findAll();
	}
	
	public void create(Employee obj) {
		if(obj.getId() == null) {
			repository.save(obj);
		}
	}
}
