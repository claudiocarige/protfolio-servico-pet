package com.claudiocarige.portfoliclaudio.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.br.CPF;

import com.claudiocarige.portfoliclaudio.domain.Client;
import com.claudiocarige.portfoliclaudio.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

public class ClientDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotNull(message = "O campo NOME é requerido!")
	protected String name;
	@CPF
	@NotNull(message = "O campo CPF é requerido!")
	protected String cpf;
	@NotNull(message = "O campo EMAIL é requerido!")
	protected String email;
	@NotNull(message = "O campo SENHA é requerido!")
	protected String password;
	protected Set<Integer> profile = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate createDate = LocalDate.now();

	public ClientDTO() {
		super();
		addProfile(Profile.CLIENT);
	}

	public ClientDTO(Client obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profile = obj.getProfile().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.createDate = obj.getCreateDate();
		addProfile(Profile.CLIENT);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Profile> getProfile() {
		return profile.stream().map(x -> Profile.toEnum(x)).collect(Collectors.toSet());
	}

	public void addProfile(Profile profile) {
		this.profile.add(profile.getCodigo());
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
}
