package com.equipeRL.backend.Models;

import com.equipeRL.backend.Models.interfaces.IFDependencia;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Essa classe � utilizada como modelo para um objeto do tipo AreaConhecimento.
 * A classe cont�m os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */

@Entity
@Table(name = "area_conhecimento")
public class AreaConhecimento implements IFDependencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message=" Area conhecimento é obrigatorio!")
	private String nome;
	
	/**
	 * Método construtor da classe AreaConhecimento
	 * Construtor vazio (utilizado para criar um objeto do tipo AreaConhecimento sem parametros definidos)
	 */
	public AreaConhecimento() {
		
	}
	
	/**
	 * Método construtor da classe AreaConhecimento (utilizado para criar um objeto do tipo AreaConhecimento com parametros definidos)
	 * @param id id da area do conhecimento
	 * @param nome nome da area do conhecimento
	 */
	public AreaConhecimento(Long id, String nome) {
		setId(id);
		setNome(nome);
	}
	
	public AreaConhecimento(AreaConhecimento entity) {
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

}
