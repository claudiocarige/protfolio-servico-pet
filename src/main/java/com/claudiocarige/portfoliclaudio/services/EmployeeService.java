package com.claudiocarige.portfoliclaudio.services;

import com.claudiocarige.portfoliclaudio.domain.Employee;
import com.claudiocarige.portfoliclaudio.domain.dtos.EmployeeDTO;

import javax.validation.Valid;
import java.util.List;

public interface EmployeeService {
    Employee findById(Integer id);
    List<Employee> findAll();
    Employee create(EmployeeDTO objDTO);
    Employee update(Integer id, @Valid EmployeeDTO objDTO);
    void delete(Integer id);
}
