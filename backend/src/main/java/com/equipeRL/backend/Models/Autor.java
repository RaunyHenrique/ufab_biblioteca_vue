package com.equipeRL.backend.Models;

import com.equipeRL.backend.Models.interfaces.IFDependencia;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Classe utilizada como modelo para um objeto do tipo Autor.
 * A classe contém  os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */

@Entity
@Table(name = "autor")
public class Autor implements IFDependencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = " Nome do Autor é obrigatório")
	private String nome;
	
	public Autor(){
	}
	/**
	 * Método construtor da classe Autor
	 * Construtor vazio (utilizado para criar um objeto do tipo Autor sem parametros definidos)
	 */
	public Autor(String nome){
		setNome(nome);
	}
	
	/**
	 * Método construtor da classe Autor (utilizado para criar um objeto do tipo Autor com parametros definidos)
	 * @param id id do autor
	 * @param nome nome do autor
	 */
	public Autor(Long id, String nome) {
		setId(id);
		setNome(nome);
	}
	
	public Autor(Autor entity) {
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
		Autor autor = (Autor) o;
		return Objects.equals(id, autor.id) &&
				Objects.equals(nome, autor.nome);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, nome);
	}
}
