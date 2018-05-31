package com.equipeRL.backend.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "grupo")
public class Grupo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	@NotNull(message = "Selecione pelo menos uma permissao")
	@ManyToMany
	@JoinTable(name = "grupo_has_permissao",joinColumns = @JoinColumn(name = "grupo_id")
												, inverseJoinColumns = @JoinColumn(name = "permissao_id"))
	private Set<Permissao> permissoes;

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

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Grupo grupo = (Grupo) o;
		return Objects.equals(id, grupo.id) &&
				Objects.equals(nome, grupo.nome) &&
				Objects.equals(permissoes, grupo.permissoes);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, nome);
	}

}
