package com.claudiocarige.portfoliclaudio.domain.dtos;

import com.claudiocarige.portfoliclaudio.domain.ServicesPet;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
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
}
