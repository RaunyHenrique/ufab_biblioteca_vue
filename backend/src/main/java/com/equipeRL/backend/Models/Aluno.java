package com.equipeRL.backend.Models;

import com.equipeRL.backend.Models.enums.Tipo_nivel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * Essa classe � utilizada como modelo para um objeto do tipo Aluno;
 * A classe cont�m os respectivos getters and setters de seus atributos.
 * A classe Aluno extende a classe Usu�rio, que cont�m os atributos e m�todos comuns a todos os usu�rios do sistema.
 * @author EquipeACL
 */
@Entity
@Table(name="aluno")
public class Aluno extends Usuario {

	private String matricula;

	@NotEmpty(message=" Nome da mãe é obrigatório")
	private String nomeMae;

	@NotNull(message=" Curso é obrigatório")
	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "curso_id",nullable=false)
	private Curso curso;

	@NotNull(message=" Nivel do aluno é obrigatório")
	@Enumerated(EnumType.STRING)
	private Tipo_nivel nivel;

	@NotNull(message=" Data de ingresso é obrigatório")
	private Date anoIngresso;

	@NotNull(message=" Periodo de ingresso é obrigatório")
	private int periodoIngresso;

	/**
	 * M�todo construtor da classe Aluno
	 * Construtor vazio (utilizado para criar um objeto do tipo Aluno sem par�metros definidos)
	 */
	public Aluno() {

	}
	/**
	 * M�todo construtor da classe Aluno (utilizado para instanciar objetos durante a busca de um objeto do tipo Aluno no Banco de Dados)
	 * @param matricula, matr�cula do aluno
	 * @param cpf, n�mero do CPF do aluno
	 * @param rg, n�mero do RG do aluno
	 * @param naturalidade, cidade natal do aluno
	 * @param nomeCompleto, nome completo do aluno
	 * @param nomeMae, nome completo da m�e do aluno
	 * @param endereco, endere�o do aluno
	 * @param telefone, telefone para contato do aluno
	 * @param curso, Objeto do tipo Curso referente ao curso que o aluno ingressou
	 * @param nivel, Enum do nivel do aluno (se � graduando, mestrando, doutorando ou p�s-doutorando)
	 * @param email, endere�o de email do aluno
	 * @param anoIngresso, o ano de ingresso do aluno no curso
	 * @param periodoIngresso, o per�odo de ingresso do aluno no curso
	 * @param senhaAcesso, a senha de acesso ao sistema do aluno
	 */
	public Aluno(String matricula, String cpf, String rg, String naturalidade, String nomeCompleto, String nomeMae,
                 String endereco, String telefone, Curso curso, Tipo_nivel nivel, String email, Date anoIngresso,
                 int periodoIngresso, String senhaAcesso, String senhaConfirmacao) {
		super(cpf, nomeCompleto, rg, naturalidade, endereco, telefone, email, senhaAcesso,senhaConfirmacao);
		setMatricula(matricula);
		setNomeMae(nomeMae);
		setCurso(curso);
		setNivel(nivel);
		setAnoIngresso(anoIngresso);
		setPeriodoIngresso(periodoIngresso);
	}
	/**
	 * M�todo construtor da classe Aluno (utilizado como objeto que ser� passado por par�metro durante a inser��o de um objeto do tipo Aluno no Banco de Dados
	 */
	public Aluno(Aluno aluno) {
		super(aluno.getCpf(), aluno.getNome(), aluno.getRg(), aluno.getNaturalidade(), aluno.getEndereco(), aluno.getTelefone(), aluno.getEmail(), aluno.getSenha(),aluno.getConfirmacaoSenha());
		setId(aluno.getId());
		setNomeMae(aluno.getNomeMae());
		setCurso(new Curso(aluno.getCurso()));
		setNivel(aluno.getNivel());
		setAnoIngresso(aluno.getAnoIngresso());
		setPeriodoIngresso(aluno.getPeriodoIngresso());
		setMatricula(aluno.getMatricula());
		setSenha(aluno.getSenha());
		setConfirmacaoSenha(aluno.getConfirmacaoSenha());
		
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
	
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
}

