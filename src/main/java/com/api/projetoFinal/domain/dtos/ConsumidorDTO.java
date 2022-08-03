package com.api.projetoFinal.domain.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.api.projetoFinal.domain.Consumidor;
import com.api.projetoFinal.domain.Pessoa;
import com.api.projetoFinal.domain.enums.Perfil;
import org.hibernate.validator.constraints.br.CPF;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsumidorDTO extends Pessoa {

	private static final long serialVersionUID = 1L;

	protected Integer id;
	@NotNull(message = "O campo NOME não poderá ser nulo")
	protected String nome;

	@CPF(message = "CPF inválido")
	@NotNull(message = "O CPF não poderá ser nulo")
	protected String cpf;

	@Email(message = "Email inválido")
	@NotNull(message = "O E-MAIL não poderá ser nulo")
	protected String email;

	@NotNull(message = "O campo de SENHA não pode ser nulo")
	protected String password;

	protected Set<Integer> perfil = new HashSet<>();;
	protected String celular;
	protected String cep;
	protected String estado;
	protected String cidade;
	protected String bairro;
	protected String rua;
	protected String numero;

	public ConsumidorDTO() {
		super();
		addPerfil(Perfil.CONSUMIDOR);
	}

	public ConsumidorDTO(Consumidor obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
		this.perfil = obj.getPerfil().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.celular = obj.getCelular();
		this.cep = obj.getCep();
		this.estado = obj.getEstado();
		this.cidade = obj.getCidade();
		this.bairro = obj.getBairro();
		this.rua = obj.getRua();
		this.numero = obj.getNumero();
		addPerfil(Perfil.CONSUMIDOR);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Perfil> getPerfil() {
		return perfil.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfil.add(perfil.getCodigo());
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}