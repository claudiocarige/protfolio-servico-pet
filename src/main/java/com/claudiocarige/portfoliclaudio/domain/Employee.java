package com.claudiocarige.portfoliclaudio.domain;

import com.claudiocarige.portfoliclaudio.domain.dtos.EmployeeDTO;
import com.claudiocarige.portfoliclaudio.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Getter
@Setter
public class Employee extends Person {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
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

	public Employee(EmployeeDTO obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profile = obj.getProfile().stream().map(Profile::getCodigo).collect(Collectors.toSet());
		this.createDate = obj.getCreateDate();
	}

}
