package com.claudiocarige.portfoliclaudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.claudiocarige.portfoliclaudio.domian.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
