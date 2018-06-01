package com.equipeRL.backend.Models;

import com.equipeRL.backend.Models.interfaces.IFDependencia;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Classe utilizada como modelo para um objeto do tipo Tema.
 * A classe contém  os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */

@Entity
@Table(name="tema")
public class Tema implements IFDependencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message=" Nome é obrigatório.")
	private String nome;

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "areaconhecimento_id",nullable=false)
	@NotNull(message=" Area é obrigatório.")
	private AreaConhecimento area;

	/**
	 * Método construtor da classe Tema
	 * Construtor vazio (utilizado para criar um objeto do tipo Tema sem parametros definidos)
	 */
	public Tema(){
		
	}
	
	/**
	 * Método construtor da classe Tema (utilizado para criar um objeto do tipo Tema com parametros definidos)
	 * @param id id do tema
	 * @param nome nome do tema
	 * @param area objeto do tipo Area, referente ao tema
	 */
	public Tema(Long id, String nome,AreaConhecimento area) {
		setId(id);
		setNome(nome);
		setArea(area);
	}
	
	public Tema(Tema entity) {
		setId(entity.getId());
		setNome(entity.getNome());
		setArea(new AreaConhecimento(entity.getArea()));
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
	
	public AreaConhecimento getArea() {
		return area;
	}

	public void setArea(AreaConhecimento area) {
		this.area = area;
	}

	public boolean validaDependencia() {
		return true;
	}
}
