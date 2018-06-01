package com.equipeRL.backend.Models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * Essa classe � utilizada como modelo para um objeto do tipo Funcion�rio;
 * A classe cont�m os respectivos getters and setters de seus atributos.
 * A classe Aluno extende a classe Usu�rio, que cont�m os atributos e m�todos comuns a todos os usu�rios do sistema.
 * @author EquipeACL
 */

@Entity
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
	 * @param rg, n�mero do RG do Funcion�rio
	 * @param naturalidade, cidade natal do Funcion�rio
	 * @param endereco, endere�o completo do Funcion�rio
	 * @param telefone, numero de telefone do Funcion�rio
	 * @param email, endere�o de email do Funcion�rio
	 */

	public Funcionario(@NotEmpty(message = " CPF é obrigatório") String cpf, @NotEmpty(message = " O nome é obrigatório") String nome, @NotEmpty(message = " RG é obrigatório") String rg, @NotEmpty(message = " A naturalidade é obrigatória") String naturalidade, @NotEmpty(message = " O endereço é obrigatório") String endereco, @NotEmpty(message = " O telefone é obrigatório") String telefone, @Size(min = 5, max = 45, message = " O tamanho do email deve estar entre 5 e 20") @NotEmpty(message = " O email é obrigatório") String email, @NotEmpty(message = " A senha é obrigatória") String senha, String confirmacaoSenha, Set<Permissao> permissoes, @NotEmpty(message = " Nome de usuário é obrigatório") String login) {
		super(cpf, nome, rg, naturalidade, endereco, telefone, email, senha, confirmacaoSenha, permissoes);
		this.login = login;
	}
}
