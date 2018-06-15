package com.equipeRL.backend.Models.acervo;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

/**
 * Essa classe � utilizada como modelo para um objeto do tipo ItemAcervo.
 * A classe cont�m os respectivos getters and setters de seus atributos.
 * A classe ItemAcervo � a super classe dos itens de acervo (jornal, livro, midias_eletr�nicas)
 * @author EquipeACL
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ItemAcervo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message=" Data é obrigatorio")
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private  Date data;
	
	@NotNull(message=" Edicao é obrigatória")
	private int edicao;
	
	@NotEmpty(message=" Título é obrigatório")
	private String titulo;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ItemAcervo that = (ItemAcervo) o;
		return edicao == that.edicao &&
				Objects.equals(id, that.id) &&
				Objects.equals(data, that.data) &&
				Objects.equals(titulo, that.titulo);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, data, edicao, titulo);
	}
}
