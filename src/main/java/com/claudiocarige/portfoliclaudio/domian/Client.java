package com.claudiocarige.portfoliclaudio.domian;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {

	private List<ServicesPet> servicesPet = new ArrayList<>();

	public Client() {
		super();
	}

	public Client(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
	}

	public List<ServicesPet> getServicesPet() {
		return servicesPet;
	}

	public void setServicesPet(List<ServicesPet> servicesPet) {
		this.servicesPet = servicesPet;
	}

}
