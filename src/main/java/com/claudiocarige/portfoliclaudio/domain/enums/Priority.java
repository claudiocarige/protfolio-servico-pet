package com.claudiocarige.portfoliclaudio.domain.enums;

public enum Priority {
	
	BAIXA(0, "BAIXA"), MEDIA(1, "MEDIA"), ALTA(2, "ALTA");
	
	private Integer codigo;
	private String descricao;

	private Priority(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	
	public static Priority toEnum(Integer codigo) {
		if(codigo == null) {
			return null;
		}
			for(Priority x: Priority.values()) {
				if(codigo.equals(x.getCodigo())) {
					return x;
				}
			}
			throw new IllegalArgumentException("Prioridade inválida");
	}
}
