package com.claudiocarige.portfoliclaudio.domain.dtos;

import com.claudiocarige.portfoliclaudio.domain.Client;
import com.claudiocarige.portfoliclaudio.domain.enums.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@NoArgsConstructor
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

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotNull(message = "O campo SENHA é requerido!")
	protected String password;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	protected Set<Integer> profile = new HashSet<>();

	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate createDate = LocalDate.now();

	public ClientDTO(Client obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.profile = obj.getProfile().stream().map(Profile::getCodigo).collect(Collectors.toSet());
		this.createDate = obj.getCreateDate();
}
	public Set<Profile> getProfile() {
		return profile.stream().map(Profile::toEnum).collect(Collectors.toSet());
	}

}
