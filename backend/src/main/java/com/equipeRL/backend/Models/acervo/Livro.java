package com.equipeRL.backend.Models.acervo;

import com.equipeRL.backend.Models.Autor;
import com.equipeRL.backend.Models.Editora;
import com.equipeRL.backend.Models.Tema;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe utilizada como modelo para um objeto do tipo Livro.
 * A classe contém os respectivos getters and setters de seus atributos.
 * A classe Livro extends da classe ItemAcervo e implementa a interface IFAcervo
 * @author EquipeACL
 */

@Entity
@Table(name="livro")
public class Livro extends ItemAcervo {
	
	@NotEmpty(message=" ISBN é obrigatório")
	private String isbn;

	@NotNull(message=" Autores é obrigatório")
	@OneToMany(
			targetEntity=Autor.class,
			cascade=CascadeType.REFRESH,
			fetch=FetchType.EAGER
	)
	private List<Autor> autores;

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "editora_id",nullable=false)
	@NotNull(message=" Editora é obrigatório")
	private Editora editora;
	
	@NotNull(message=" Numero de paginas é obrigatório")
	private int numero_paginas;

	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "tema_id",nullable=false)
	@NotNull(message=" Tema é obrigatório")
	private Tema tema;
	
	public Livro() {}
	
	/**
	 * Método construtor da classe Livro
	 * Construtor vazio (utilizado para criar um objeto do tipo Livro sem parametros definidos)
	 */
	public Livro(Livro livro) {
		if(livro!=null){
			setId(livro.getId());
			setIsbn(livro.getIsbn());
			setTitulo(livro.getTitulo());

			List<Autor> listaAutores = new ArrayList<>();

			for(Autor a: livro.getAutores()){
				listaAutores.add(new Autor(a));
			}

			setAutores(listaAutores);
			setEditora(new Editora(livro.getEditora()));
			setData(livro.getAnoPublicacao());
			setEdicao(livro.getEdicao());
			setNumero_paginas(livro.getNumero_paginas());
			setTema(new Tema(livro.getTema()));
		}
	}
	
	/**
	 * Método construtor da classe Livro (utilizado para criar um objeto do tipo Livro com parametros definidos)
	 * @param isbn isbn do livro
	 * @param titulo titulo do livro
	 * @param autores array de objetos do tipo Autor com os autores do livro
	 * @param editora objeto do tipo Editora referente ao livro
	 * @param ano_publicacao ano de publicacao do livro
	 * @param edicao edicao do livro
	 * @param numero_paginas numero de paginas do livro
	 */
	public Livro(String isbn, String titulo, List<Autor> autores, Editora editora, Date ano_publicacao, int edicao, int numero_paginas, Tema tema) {
		setIsbn(isbn);
		setTitulo(titulo);
		setAutores(autores);
		setEditora(editora);
		setData(ano_publicacao);
		setEdicao(edicao);
		setNumero_paginas(numero_paginas);
		setTema(tema);
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	
	public Date getAnoPublicacao(){
		return getData();
	}
	
	public void setAnoPublicacao(Date anoPublicacao){
		setData(anoPublicacao);
	}

	public int getNumero_paginas() {
		return numero_paginas;
	}

	public void setNumero_paginas(int numero_paginas) {
		this.numero_paginas = numero_paginas;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public boolean validaItem() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Livro livro = (Livro) o;
		return numero_paginas == livro.numero_paginas &&
				Objects.equals(isbn, livro.isbn) &&
				Objects.equals(editora, livro.editora) &&
				Objects.equals(tema, livro.tema);
	}

	@Override
	public int hashCode() {

		return Objects.hash(isbn, editora, numero_paginas, tema);
	}
}
