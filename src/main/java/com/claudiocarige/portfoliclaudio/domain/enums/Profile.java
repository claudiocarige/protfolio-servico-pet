package com.claudiocarige.portfoliclaudio.domain.enums;

public enum Profile {
	
	ADMIN(0, "ROLE_ADMIN"), CLIENT(1, "ROLE_CLIENT"), EMPLOYEE(2, "ROLE_EMPLOYEE");
	
	private Integer codigo;
	private String descricao;

	private Profile(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static Profile toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
			for(Profile x: Profile.values()) {
				if(codigo.equals(x.getCodigo())) {
					return x;
				}
			}
			throw new IllegalArgumentException("Perfil inválido");
	}
}
