package com.equipeRL.backend.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="estados")
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cod_estados;
	
	private String sigla;

	private String nome;

	public Estado() {}

	public Estado(String sigla, String nome) {
		this.sigla = sigla;
		this.nome = nome;
	}

	public Long getCod_estados() {
		return cod_estados;
	}

	public void setCod_estados(Long cod_estados) {
		this.cod_estados = cod_estados;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Estado estado = (Estado) o;
		return Objects.equals(cod_estados, estado.cod_estados) &&
				Objects.equals(sigla, estado.sigla) &&
				Objects.equals(nome, estado.nome);
	}

	@Override
	public int hashCode() {

		return Objects.hash(cod_estados, sigla, nome);
	}

}
