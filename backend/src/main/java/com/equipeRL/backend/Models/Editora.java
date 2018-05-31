package com.equipeRL.backend.Models;

import com.equipeRL.backend.Models.interfaces.IFDependencia;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Classe utilizada como modelo para um objeto do tipo Editora.
 * A classe contém os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */

@Entity
@Table(name = "editora")
public class Editora implements IFDependencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = " Nome é obrigatório")
	private String nome;
	
	
	/**
	 * Método construtor da classe Editora
	 * Construtor vazio (utilizado para criar um objeto do tipo Editora sem parametros definidos)
	 */
	public Editora() {

	}
	
	public Editora(String nome){
		setNome(nome);
	}
	
	/**
	 * Método construtor da classe Editora (utilizado para criar um objeto do tipo Editora com parametros definidos)
	 * @param id id da editora
	 * @param nome nome da editora
	 */
	public Editora(Long id, String nome) {
		setId(id);
		setNome(nome);
	}
	
	public Editora(Editora entity) {
		setId(entity.getId());
		setNome(entity.getNome());
	}
	
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

	public boolean validaDependencia() {
		return true;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Editora editora = (Editora) o;
		return Objects.equals(id, editora.id) &&
				Objects.equals(nome, editora.nome);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, nome);
	}
}
