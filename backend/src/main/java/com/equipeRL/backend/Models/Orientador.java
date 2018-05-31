package com.equipeRL.backend.Models;

import com.equipeRL.backend.Models.interfaces.IFDependencia;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Essa classe � utilizada como modelo para um objeto do tipo Orientador.
 * A classe cont�m os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */

@Entity
@Table(name = "orientador")
public class Orientador implements IFDependencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = " Nome do Orientador é obrigatório!")
	private String nome;
	
	@NotEmpty(message = " Formação é obrigatória !")
	private String formacao;
	
	/**
	 * M�todo construtor da classe Orientador
	 * Construtor vazio (utilizado para criar um objeto do tipo Orientador sem par�metros definidos)
	 */
	public Orientador(){		
		
	}
	
	/**
	 * M�todo construtor da classe Orientador (utilizado para criar um objeto do tipo Orientador com par�metros definidos)
	 * @param id, id do orientador
	 * @param nome, nome do orientador
	 * @param formacao, formacao do orientado
	 */
	public Orientador(Long id,String nome, String formacao){
		setId(id);
		setNome(nome);
		setFormacao(formacao);
	}
	
	public Orientador(Orientador entity){
		setId(entity.getId());
		setNome(entity.getNome());
		setFormacao(entity.getFormacao());
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
	public String getFormacao() {
		return formacao;
	}
	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public boolean validaDependencia() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Orientador that = (Orientador) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(nome, that.nome) &&
				Objects.equals(formacao, that.formacao);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, nome, formacao);
	}
}
