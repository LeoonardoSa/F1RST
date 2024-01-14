package br.com.f1rst.challenge.f1rstbank.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String documento;
	private String nome;
	private String senha;
	private String endereco;
	
	private LocalDate dataEntrada = LocalDate.now();

	protected Cliente() {
	}

	public Cliente(String documento, String nome, String email, String senha) {
		this.documento = documento;
		this.nome = nome;
		this.endereco = email;
		this.setSenha(senha);
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public String getEndereco() {
		return endereco;
	}

	public Integer getId() {
		return id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
