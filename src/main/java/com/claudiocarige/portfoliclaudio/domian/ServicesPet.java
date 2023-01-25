package com.claudiocarige.portfoliclaudio.domian;

import java.time.LocalDate;
import java.util.Objects;

import com.claudiocarige.portfoliclaudio.domain.enums.Priority;
import com.claudiocarige.portfoliclaudio.domain.enums.Status;

public class ServicesPet {

	private Integer id;
	private LocalDate openingDate = LocalDate.now();
	private LocalDate closingDate;
	private Priority priority;
	private Status status;
	private String title;
	private String comments;

	private Client client;
	private Employee employee;

	public ServicesPet() {
		super();
	}

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

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServicesPet other = (ServicesPet) obj;
		return Objects.equals(id, other.id);
	}

	
}
