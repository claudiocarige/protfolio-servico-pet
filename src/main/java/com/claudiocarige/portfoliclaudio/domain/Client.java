package com.claudiocarige.portfoliclaudio.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.claudiocarige.portfoliclaudio.domain.dtos.ClientDTO;
import com.claudiocarige.portfoliclaudio.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Client extends Person {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client")
	private List<ServicesPet> servicesPet = new ArrayList<>();

	public Client() {
		super();
		addProfile(Profile.CLIENT);
	}

	public Client(Integer id, String name, String cpf, String email, String password) {
		super(id, name, cpf, email, password);
		addProfile(Profile.CLIENT);
	}
	public Client(ClientDTO obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profile = obj.getProfile().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.createDate = obj.getCreateDate();
	}

	public List<ServicesPet> getServicesPet() {
		return servicesPet;
	}

	public void setServicesPet(List<ServicesPet> servicesPet) {
		this.servicesPet = servicesPet;
	}

}
