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
@Table(name="cidade")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private int codigo;
	
	@NotEmpty(message = " Nome da cidade é obrigatório")
	private String nome;
	
	@NotEmpty(message = " Código da cidade é obrigatório")
	private String uf;
	
	/**
	 * M�todo construtor da classe Cidade
	 * Construtor vazio (utilizado para criar um objeto do tipo Cidade sem par�metros definidos)
	 */
	public Cidade() {
		
	}
	
	/**
	 * M�todo construtor da classe Cidade (utilizado para criar um objeto do tipo Cidade com par�metros definidos)
	 * @param id, id da cidade
	 * @param codigo, codigo da cidade
	 * @param nome, nome da cidade
	 * @param uf, uni�o federativa da cidade
	 */
	
	public Cidade(Cidade cidade) {
		setId(cidade.getId());
		setCodigo(cidade.getCodigo());
		setNome(cidade.getNome());
		setUf(cidade.getUf());
	}
	
	public Cidade(Long id, int codigo, String nome, String uf) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.uf = uf;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cidade cidade = (Cidade) o;
		return codigo == cidade.codigo &&
				Objects.equals(id, cidade.id) &&
				Objects.equals(nome, cidade.nome) &&
				Objects.equals(uf, cidade.uf);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, codigo, nome, uf);
	}
}
