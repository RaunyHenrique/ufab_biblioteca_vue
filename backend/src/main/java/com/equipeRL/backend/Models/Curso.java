package com.equipeRL.backend.Models;

import com.equipeRL.backend.Models.enums.Tipo_curso;
import com.equipeRL.backend.Models.interfaces.IFDependencia;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Essa classe utilizada como modelo para um objeto do tipo Curso.
 * A classe contém os respectivos getters and setters de seus atributos.
 * @author EquipeACL
 */

@Entity
@Table(name="curso")
public class Curso implements IFDependencia {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Nome é obrigatório")
	private String nome;
	
	@NotEmpty(message="Sigla do curso é obrigatório")
	private String sigla;

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "area_conhecimento_id", nullable=false)
	@NotNull(message="Area do conhecimento é obrigatório")
	private AreaConhecimento area;

	@Enumerated(EnumType.STRING)
	@NotNull(message="Tipo do curso é obrigatório")
	private Tipo_curso tipo;
	
	/**
	 * Método construtor da classe Curso
	 * Construtor vazio (utilizado para criar um objeto do tipo Curso sem parametros definidos)
	 */
	public Curso(){
		
	}
	
	/**
	 * Método construtor da classe Curso (utilizado para criar um objeto do tipo Curso com parametros definidos)
	 * @param nome nome do curso
	 * @param sigla sigla do curso
	 * @param area objeto do tipo Area referente ao curso
	 * @param tipo Enum Tipo_curso, referente ao tipo do curso
	 */
	public Curso(String nome, String sigla, AreaConhecimento area, Tipo_curso tipo) {
		setNome(nome);
		setSigla(sigla);
		setArea(area);
		setTipo(tipo);
	}
	public Curso(Long id,String nome, String sigla, AreaConhecimento area, Tipo_curso tipo) {
		setId(id);
		setNome(nome);
		setSigla(sigla);
		setArea(area);
		setTipo(tipo);
	}
	
	public Curso(Curso curso){
		if(curso!= null) {
			setId(curso.getId());
			setNome(curso.getNome());
			setSigla(curso.getSigla());
			setArea(new AreaConhecimento(curso.getArea()));
			setTipo(curso.getTipo());
		}
		
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
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public AreaConhecimento getArea() {
		return area;
	}
	public void setArea(AreaConhecimento area) {
		this.area = area;
	}
	public Tipo_curso getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_curso tipo) {
		this.tipo = tipo;
	}

	public boolean validaDependencia() {
		return true;
	}

}
