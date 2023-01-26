package com.claudiocarige.portfoliclaudio.domian;

import java.util.ArrayList;
import java.util.List;

import com.claudiocarige.portfoliclaudio.domain.enums.Profile;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Employee extends Person {

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "employee")
	private List<ServicesPet> servicePet = new ArrayList<>();

	public Employee() {
		super();
		addProfile(Profile.EMPLOYEE);
	}

	public Employee(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
		addProfile(Profile.EMPLOYEE);
	}

	public List<ServicesPet> getServicePet() {
		return servicePet;
	}

	public void setServicePet(List<ServicesPet> servicePet) {
		this.servicePet = servicePet;
	}

}
