package com.claudiocarige.portfoliclaudio.domian;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {

	private List<ServicesPet> servicePet = new ArrayList<>();

	public Employee() {
		super();
	}

	public Employee(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
	}

	public List<ServicesPet> getServicePet() {
		return servicePet;
	}

	public void setServicePet(List<ServicesPet> servicePet) {
		this.servicePet = servicePet;
	}

}
