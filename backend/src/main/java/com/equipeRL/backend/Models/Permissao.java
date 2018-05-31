package com.equipeRL.backend.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "permissao")
public class Permissao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		Permissao permissao = (Permissao) o;
		return Objects.equals(id, permissao.id) &&
				Objects.equals(nome, permissao.nome);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, nome);
	}

}
