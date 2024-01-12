package br.com.f1rst.challenge.f1rstbank.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Correntista {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@SuppressWarnings("PMD.UnusedPrivateField")
	private Integer id;

	private String documento;
	private String nome;
	private String senha;

	private LocalDate dataEntrada = LocalDate.now();

	private String email;

	private String externalId;

	protected Correntista() {
	}

	public Correntista(String documento, String nome, String email, String senha) {
		this.documento = documento;
		this.nome = nome;
		this.email = email;
	    this.senha = senha;
	}

	public String getCpf() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public String getEmail() {
		return email;
	}

	public void setInternetBankAccount(InternetBankAccount account) {
		this.email = account.getEmail();
		this.externalId = account.getId();
	}

	public Integer getId() {
		return id;
	}

	public boolean hasInternetBank() {
		return externalId != null && !externalId.isEmpty();
	}
}
