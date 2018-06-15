package com.equipeRL.backend.Models.acervo;

import com.equipeRL.backend.Models.Autor;
import com.equipeRL.backend.Models.Cidade;
import com.equipeRL.backend.Models.enums.Tipo_anal;
import com.equipeRL.backend.Models.interfaces.IFAcervo;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Classe utilizada como modelo para um objeto do tipo Anal.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Anal extends da classe ItemAcervo e implementa a interface IFAcervo
 * @author EquipeACL
 */

@Entity
@Table(name="anal")
public class Anal extends ItemAcervo implements IFAcervo {

	@Enumerated(EnumType.STRING)
	@NotNull(message=" Tipo é obrigatório")
	private Tipo_anal tipo;

	@NotNull(message=" Autores é obrigatório")
	@OneToMany(
			targetEntity=Autor.class,
			cascade=CascadeType.REFRESH,
			fetch=FetchType.EAGER
	)
	private List<Autor> autores;
	
	@NotEmpty(message = " Nome do Congresso é Obrigatório")
	private String nome_congresso;

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "cod_cidades",nullable=false)
	@NotNull(message=" Cidade é obrigatório")
	private Cidade local;
	
	/**
	 * Método construtor da classe Anal
	 * Construtor vazio (utilizado para criar um objeto do tipo Anal sem par�metros definidos)
	 */
	public Anal() {}
	
	/**
	 * Método construtor da classe Anal (utilizado para criar um objeto do tipo Anal com par�metros definidos)
	 * @param id id do anal
	 * @param tipo Enum do tipo do anal
	 * @param titulo titulo do anal
	 * @param nome_congresso nome do congresso onde o anal foi apresentado
	 * @param anoPublicacao ano de publicacao do anal
	 * @param local objeto do tipo Cidade referente ao anal
	 */
	public Anal(Long id,Tipo_anal tipo, String titulo, List<Autor> autores, String nome_congresso, Date anoPublicacao, Cidade local){
		setId(id);
		setTipo(tipo);
		setTitulo(titulo);
		setAutores(autores);
		setNome_congresso(nome_congresso);
		setAnoPublicacao(anoPublicacao);
		setLocal(local);
	}

	public Date getAnoPublicacao() {
		return getData();
	}

	public void setAnoPublicacao(Date anoPublicacao) {
		setData(anoPublicacao);
	}

	public Tipo_anal getTipo() {
		return tipo;
	}

	public void setTipo(Tipo_anal tipo) {
		this.tipo = tipo;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public String getNome_congresso() {
		return nome_congresso;
	}

	public void setNome_congresso(String nome_congresso) {
		this.nome_congresso = nome_congresso;
	}

	public Cidade getLocal() {
		return local;
	}

	public void setLocal(Cidade local) {
		this.local = local;
	}

	public boolean validaItem() {
		return true;
	}
	
}
