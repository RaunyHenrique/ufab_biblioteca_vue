package com.equipeRL.backend.Models;

import com.equipeRL.backend.Models.enums.Tipo_nivel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Objects;

/**
 * Essa classe � utilizada como modelo para um objeto do tipo Aluno;
 * A classe cont�m os respectivos getters and setters de seus atributos.
 * A classe Aluno extende a classe Usu�rio, que cont�m os atributos e m�todos comuns a todos os usu�rios do sistema.
 * @author EquipeACL
 */
@Entity
@Table(name="aluno")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(unique = true)
	@NotEmpty(message="Campo rg obrigatório")
	private String rg;

	@NotNull
	@Column(unique = true)
	@NotEmpty(message="Campo cpf obrigatório")
	private String cpf;

	@NotNull
	@NotEmpty(message="Campo naturalidade obrigatória")
	private String naturalidade;

	@NotNull
	@NotEmpty(message="Campo nome obrigatório")
	private String nome;

	private String matricula;

	@NotEmpty(message="Campo nome da mãe é obrigatório")
	private String nomeMae;

	@NotNull
	@NotEmpty(message="Campo endereco é obrigatório")
	private String endereco;

	@NotNull
	@Column(unique = true)
	@NotEmpty(message="Campo telefone é obrigatório")
	private String telefone;

	@NotNull(message="Curso é obrigatório")
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "curso_id",nullable=false)
	private Curso curso;

	@NotNull(message="Nivel do aluno é obrigatório")
	@Enumerated(EnumType.STRING)
	private Tipo_nivel nivel;

	@NotNull(message="Data de ingresso é obrigatório")
	private Date anoIngresso;

	@NotNull(message="Periodo de ingresso é obrigatório")
	private int periodoIngresso;

	/**
	 * M�todo construtor da classe Aluno
	 * Construtor vazio (utilizado para criar um objeto do tipo Aluno sem par�metros definidos)
	 */
	public Aluno() {}

	public Aluno(@NotNull @NotEmpty String rg, @NotNull @NotEmpty String cpf, @NotNull @NotEmpty String naturalidade, @NotNull @NotEmpty String nome, String matricula, @NotEmpty(message = " Nome da mãe é obrigatório") String nomeMae, @NotNull @NotEmpty String endereco, @NotNull @NotEmpty String telefone, @NotNull(message = " Curso é obrigatório") Curso curso, @NotNull(message = " Nivel do aluno é obrigatório") Tipo_nivel nivel, @NotNull(message = " Data de ingresso é obrigatório") Date anoIngresso, @NotNull(message = " Periodo de ingresso é obrigatório") int periodoIngresso) {
		this.rg = rg;
		this.cpf = cpf;
		this.naturalidade = naturalidade;
		this.nome = nome;
		this.matricula = matricula;
		this.nomeMae = nomeMae;
		this.endereco = endereco;
		this.telefone = telefone;
		this.curso = curso;
		this.nivel = nivel;
		this.anoIngresso = anoIngresso;
		this.periodoIngresso = periodoIngresso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Tipo_nivel getNivel() {
		return nivel;
	}

	public void setNivel(Tipo_nivel nivel) {
		this.nivel = nivel;
	}

	public Date getAnoIngresso() {
		return anoIngresso;
	}

	public void setAnoIngresso(Date anoIngresso) {
		this.anoIngresso = anoIngresso;
	}

	public int getPeriodoIngresso() {
		return periodoIngresso;
	}

	public void setPeriodoIngresso(int periodoIngresso) {
		this.periodoIngresso = periodoIngresso;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Aluno aluno = (Aluno) o;
		return periodoIngresso == aluno.periodoIngresso &&
				Objects.equals(id, aluno.id) &&
				Objects.equals(rg, aluno.rg) &&
				Objects.equals(cpf, aluno.cpf) &&
				Objects.equals(naturalidade, aluno.naturalidade) &&
				Objects.equals(nome, aluno.nome) &&
				Objects.equals(matricula, aluno.matricula) &&
				Objects.equals(nomeMae, aluno.nomeMae) &&
				Objects.equals(endereco, aluno.endereco) &&
				Objects.equals(telefone, aluno.telefone) &&
				nivel == aluno.nivel &&
				Objects.equals(anoIngresso, aluno.anoIngresso);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, rg, cpf, naturalidade, nome, matricula, nomeMae, endereco, telefone, nivel, anoIngresso, periodoIngresso);
	}

	/**
	 * M�todo utilizaod para gerar a matr�cula do aluno, utilizando os atributos da pr�pria classe
	 */
	public void gerarMatricula() {
		this.matricula = "";
		this.matricula+= String.valueOf(nivel).charAt(0)+ this.getCurso().getSigla()+this.anoIngresso.toString().substring(2, 4)+this.periodoIngresso;
		//this.matricula += this.getCurso().getSigla();
		System.out.println(curso.getSigla());
	}

}

