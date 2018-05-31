package com.equipeRL.backend.Models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

/**
 * Essa classe � utilizada como modelo para um objeto do tipo Funcion�rio;
 * A classe cont�m os respectivos getters and setters de seus atributos.
 * A classe Aluno extende a classe Usu�rio, que cont�m os atributos e m�todos comuns a todos os usu�rios do sistema.
 * @author EquipeACL
 */

@Entity
@Table(name= "usuario")
public class Funcionario extends Usuario {
	
	
	@NotEmpty(message = " Nome de usuário é obrigatório")
	protected String login;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	private static final long serialVersionUID = 1L;
	public Funcionario() {
		
	}	
	/**
	 * M�todo construtor da classe Funcion�rio
	 * @param cpf, n�mero do CPF do Funcion�rio
	 * @param nomeCompleto, nome completo do Funcion�rio
	 * @param rg, n�mero do RG do Funcion�rio
	 * @param naturalidade, cidade natal do Funcion�rio
	 * @param endereco, endere�o completo do Funcion�rio
	 * @param telefone, numero de telefone do Funcion�rio
	 * @param email, endere�o de email do Funcion�rio
	 * @param senhaAcesso, senha de acesso ao sistema do Funcion�rio
	 * @param nomeUsuario, nome de usuario no sistema do Funcion�rio
	 */
	public Funcionario(String cpf, String nomeCompleto, String rg, String naturalidade, String endereco, String telefone,
                       String email, String senhaAcesso, String nomeUsuario, String senhaConfirmacao) {
		super(cpf, nomeCompleto, rg, naturalidade, endereco, telefone, email, senhaAcesso,senhaConfirmacao);
	}
	
}
