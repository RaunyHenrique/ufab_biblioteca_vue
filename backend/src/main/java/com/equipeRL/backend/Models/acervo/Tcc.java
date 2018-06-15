package com.equipeRL.backend.Models.acervo;

import com.equipeRL.backend.Models.Autor;
import com.equipeRL.backend.Models.Cidade;
import com.equipeRL.backend.Models.Orientador;
import com.equipeRL.backend.Models.enums.Tipo_tcc;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Essa classe utilizada como modelo para um objeto do tipo Tcc.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Tcc extends da classe ItemAcervo e implementa a interface IFAcervo
 * @author EquipeACL
 */

@Entity
@Table(name="tcc")
public class Tcc extends ItemAcervo {

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "autor_id",nullable=false)
	@NotNull(message=" Autor é obrigatorio")
	private Autor autor;

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "orientador_id",nullable=false)
	@NotNull(message=" Orientador é obrigatorio")
	private Orientador orientador;

	@Enumerated(EnumType.STRING)
	@NotNull(message=" Tipo não pode ser nulo!")
	private Tipo_tcc tipo;

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "cod_cidades",nullable=false)
	@NotNull(message=" Cidade é obrigatorio")
	private Cidade cidade;

	/**
	 * Método construtor da classe Tcc (utilizado para criar um objeto do tipo Tcc com parametros definidos)
	 * @param id id do tcc
	 * @param titulo titulo do tcc
	 * @param autor objeto do tipo Autor referente ao tcc
	 * @param orientador objeto do tipo Orientador referente ao tcc
	 * @param tipo Enum que define o tipo do tcc
	 * @param ano_defesa ano da defesa do tcc
	 * @param cidade cidade da defesa do tcc
	 */
	public Tcc(Long id, String titulo, Autor autor, Orientador orientador, Tipo_tcc tipo, Date ano_defesa, Cidade cidade) {
		setId(id);
		setTitulo(titulo);
		setAutor(autor);
		setOrientador(orientador);
		setTipo(tipo);
		setAno_defesa(ano_defesa);
		setCidade(cidade);
	}

	public Tcc() {
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Orientador getOrientador() {
		return orientador;
	}
	public void setOrientador(Orientador orientador) {
		this.orientador = orientador;
	}
	public Tipo_tcc getTipo() {
		return tipo;
	}
	public void setTipo(Tipo_tcc tipo) {
		this.tipo = tipo;
	}
	public Date getAno_defesa() {
		return getData();
	}
	public void setAno_defesa(Date ano_defesa) {
		setData(ano_defesa);
	}
	public Cidade getCidade() {
		return cidade;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}	
	
	public boolean validaItem() {
		return true;
	}
}
