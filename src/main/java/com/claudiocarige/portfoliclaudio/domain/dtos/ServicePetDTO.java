package com.claudiocarige.portfoliclaudio.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.claudiocarige.portfoliclaudio.domain.ServicesPet;
import com.fasterxml.jackson.annotation.JsonFormat;


public class ServicePetDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate openingDate = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate closingDate;
	@NotNull(message = "O Campo PRIORIDADE é requerido")
	private Integer priority;
	@NotNull(message = "O Campo STATUS é requerido")
	private Integer status;
	@NotNull(message = "O Campo TIPO DE SERVIÇO é requerido")
	private String title;
	@NotNull(message = "O Campo DESCRIÇÃO é requerido")
	private String comments;
	@NotNull(message = "O Campo FUNCIONÁRIO é requerido")
	private Integer employee;
	@NotNull(message = "O Campo CLIENTE é requerido")
	private Integer client;
	private String nameEmploye;
	private String nameClient;

	public ServicePetDTO() {
		super();
	}

	public ServicePetDTO(ServicesPet obj) {
		this.id = obj.getId();
		this.openingDate = obj.getOpeningDate();
		this.closingDate = obj.getClosingDate();
		this.priority = obj.getPriority().getCodigo();
		this.status = obj.getStatus().getCodigo();
		this.title = obj.getTitle();
		this.comments = obj.getComments();
		this.employee = obj.getEmployee().getId();
		this.client = obj.getClient().getId();
		this.nameEmploye = obj.getEmployee().getName();
		this.nameClient = obj.getClient().getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public LocalDate getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(LocalDate closingDate) {
		this.closingDate = closingDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getEmployee() {
		return employee;
	}

	public void setEmployee(Integer employee) {
		this.employee = employee;
	}

	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

	public String getNameEmploye() {
		return nameEmploye;
	}

	public void setNameEmploye(String nameEmploye) {
		this.nameEmploye = nameEmploye;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}
}
