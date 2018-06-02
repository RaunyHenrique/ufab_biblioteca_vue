package com.equipeRL.backend.Models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
	private String cpf;

	@NotEmpty(message = " O nome é obrigatório")
	protected String nome;

	@NotEmpty(message = " RG é obrigatório")
	private String rg;

	@NotEmpty(message = " A naturalidade é obrigatória")
	private String naturalidade;

	@NotEmpty(message = " O endereço é obrigatório")
	private String endereco;

	@NotEmpty(message = " O telefone é obrigatório")
	private String telefone;

	@NotEmpty(message = " O email é obrigatório")
	private String username;

	@NotEmpty(message = " A senha é obrigatória")
	private String password;

	@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set <Permissao> permissoes;

	public Usuario() {}

	public Usuario(@NotEmpty(message = " CPF é obrigatório") String cpf, @NotEmpty(message = " O nome é obrigatório") String nome, @NotEmpty(message = " RG é obrigatório") String rg, @NotEmpty(message = " A naturalidade é obrigatória") String naturalidade, @NotEmpty(message = " O endereço é obrigatório") String endereco, @NotEmpty(message = " O telefone é obrigatório") String telefone, @NotEmpty(message = " O email é obrigatório") String username, @NotEmpty(message = " A senha é obrigatória") String password, Set<Permissao> permissoes) {
		this.cpf = cpf;
		this.nome = nome;
		this.rg = rg;
		this.naturalidade = naturalidade;
		this.endereco = endereco;
		this.telefone = telefone;
		this.username = username;
		this.password = password;
		this.permissoes = permissoes;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

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
				Objects.equals(username, usuario.username) &&
				Objects.equals(password, usuario.password) &&
				Objects.equals(permissoes, usuario.permissoes);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, cpf, nome, rg, naturalidade, endereco, telefone, username, password, permissoes);
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
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
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
