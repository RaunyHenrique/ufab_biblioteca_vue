package com.equipeRL.backend.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Essa classe � utilizada como modelo para um objeto do tipo Cidade.
 * A classe cont�m os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */

@Entity
@Table(name="cidades")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_cidades;

	@NotNull
	private Long estados_cod_estados;
	
	@NotEmpty(message = " Nome da cidade é obrigatório")
	private String nome;

	@NotEmpty(message = " Cep da cidade é obrigatório")
	private String cep;

	public Cidade() {}

	public Cidade(@NotNull Long estados_cod_estados, @NotEmpty(message = " Nome da cidade é obrigatório") String nome, @NotEmpty(message = " Cep da cidade é obrigatório") String cep) {
		this.estados_cod_estados = estados_cod_estados;
		this.nome = nome;
		this.cep = cep;
	}

	public Long getCod_cidades() {
		return cod_cidades;
	}

	public void setCod_cidades(Long cod_cidades) {
		this.cod_cidades = cod_cidades;
	}

	public Long getEstados_cod_estados() {
		return estados_cod_estados;
	}

	public void setEstados_cod_estados(Long estados_cod_estados) {
		this.estados_cod_estados = estados_cod_estados;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cidade cidade = (Cidade) o;
		return Objects.equals(cod_cidades, cidade.cod_cidades) &&
				Objects.equals(estados_cod_estados, cidade.estados_cod_estados) &&
				Objects.equals(nome, cidade.nome) &&
				Objects.equals(cep, cidade.cep);
	}

	@Override
	public int hashCode() {

		return Objects.hash(cod_cidades, estados_cod_estados, nome, cep);
	}

}
