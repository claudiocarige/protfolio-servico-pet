package com.claudiocarige.portfoliclaudio.domain.enums;

public enum Perfil {
	
	ADMIN(0, "ROLE_ADMIN"), CLIENTE(1, "ROLE_CLIENTE"), FUNCIONARIO(2, "ROLE_FUNCIONARIO");
	
	private Integer codigo;
	private String descricao;

	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
			for(Perfil x: Perfil.values()) {
				if(codigo.equals(x.getCodigo())) {
					return x;
				}
			}
			throw new IllegalArgumentException("Perfil inválido");
	}
}
