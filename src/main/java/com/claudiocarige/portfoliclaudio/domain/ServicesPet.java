package com.claudiocarige.portfoliclaudio.domain;

import com.claudiocarige.portfoliclaudio.domain.enums.Priority;
import com.claudiocarige.portfoliclaudio.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class ServicesPet implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate openingDate = LocalDate.now();

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate closingDate;

	private Priority priority;
	private Status status;
	private String title;
	private String comments;

	@ManyToOne
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;


	public ServicesPet(Integer id, Priority priority, Status status, String title, String comments, Client client,
			Employee employee) {
		super();
		this.id = id;
		this.priority = priority;
		this.status = status;
		this.title = title;
		this.comments = comments;
		this.client = client;
		this.employee = employee;
	}

}
