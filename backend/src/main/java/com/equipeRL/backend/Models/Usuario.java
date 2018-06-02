package com.equipeRL.backend.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.*;


/**
 * Essa classe � utilizada como modelo para um objeto do tipo Usuario;
 * A classe cont�m os respectivos getters and setters de seus atributos.
 * Essa classe � a super classe que os usuarios do sistema herdam seus m�todos e atributos, que s�o comuns a todos.
 * @author EquipeACL
 */
@Entity
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = " CPF é obrigatório")
	protected String cpf;

	@NotEmpty(message = " O nome é obrigatório")
	protected String nome;

	@NotEmpty(message = " RG é obrigatório")
	protected String rg;

	@NotEmpty(message = " A naturalidade é obrigatória")
	protected String naturalidade;

	@NotEmpty(message = " O endereço é obrigatório")
	protected String endereco;

	@NotEmpty(message = " O telefone é obrigatório")
	protected String telefone;

	@Size(min = 5, max = 45, message = " O tamanho do email deve estar entre 5 e 20")
	@NotEmpty(message = " O email é obrigatório")
	protected String email;

	@NotEmpty(message = " A senha é obrigatória")
	protected String senha;

	@Transient
	protected String confirmacaoSenha;


	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set <Permissao> permissoes;


/*	@NotNull(message = "Selecione pelo menos um grupo")
	@ManyToMany
	@JoinTable(name = "usuario_has_grupo",joinColumns = @JoinColumn(name = "usuario_id")
												, inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private List <Grupo> grupos;*/

	/**
	 * M�todo Construtor da classe Usu�rio
	 * Construtor vazio (utilizado para criar um objeto do tipo Usuario sem par�metros definidos)
	 */
	public Usuario() {

	}

    public Usuario(@NotEmpty(message = " CPF é obrigatório") String cpf, @NotEmpty(message = " O nome é obrigatório") String nome, @NotEmpty(message = " RG é obrigatório") String rg, @NotEmpty(message = " A naturalidade é obrigatória") String naturalidade, @NotEmpty(message = " O endereço é obrigatório") String endereco, @NotEmpty(message = " O telefone é obrigatório") String telefone, @Size(min = 5, max = 45, message = " O tamanho do email deve estar entre 5 e 20") @NotEmpty(message = " O email é obrigatório") String email, @NotEmpty(message = " A senha é obrigatória") String senha, String confirmacaoSenha, Set<Permissao> permissoes) {
        this.cpf = cpf;
        this.nome = nome;
        this.rg = rg;
        this.naturalidade = naturalidade;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.confirmacaoSenha = confirmacaoSenha;
        this.permissoes = permissoes;
    }

    /**
	 * M�todo Construtor da classe Usu�rio
	 */

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(id, usuario.id) &&
				Objects.equals(cpf, usuario.cpf) &&
				Objects.equals(nome, usuario.nome) &&
				Objects.equals(rg, usuario.rg) &&
				Objects.equals(naturalidade, usuario.naturalidade) &&
				Objects.equals(endereco, usuario.endereco) &&
				Objects.equals(telefone, usuario.telefone) &&
				Objects.equals(email, usuario.email) &&
				Objects.equals(senha, usuario.senha) &&
				Objects.equals(confirmacaoSenha, usuario.confirmacaoSenha);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, cpf, nome, rg, naturalidade, endereco, telefone, email, senha, confirmacaoSenha);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> permissoes = new ArrayList<GrantedAuthority>();

		for (Permissao permissao : getPermissoes()) {

			permissoes.add(new SimpleGrantedAuthority(permissao.getNome()));

		}

		return permissoes;

	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
