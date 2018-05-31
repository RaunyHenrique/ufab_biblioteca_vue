package com.equipeRL.backend.Models.enums;

/**
 * Essa classe � respons�vel por criar os Enum utilizados como atributo de um objeto do tipo Anal
 * @author EquipeACL
 *
 */
public enum Tipo_anal {
	
	ARTIGO("Artigo"),
	POSTER("Poster"),
	RESUMO("Resumo");
	
	private String descricao;
	
	Tipo_anal(String descricao){
		this.descricao = descricao;
	}
	public String getDescricao() {
		return descricao;
	}

}
