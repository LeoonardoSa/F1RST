package br.com.f1rst.challenge.f1rstbank.controller.form;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.f1rst.challenge.f1rstbank.domain.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PessoaFisicaForm {

    @JsonProperty
    @CPF(message = "CPF invalido")
    @NotNull(message = "CPF é um campo obrigatório")
    private String cpf;
    @JsonProperty
    @NotBlank(message = "Nome do Cliente é um campo obrigatório e não pode estar em branco")
    private String nome;

    @JsonProperty
    @NotBlank(message = "Endereço do cliente é um campo obrigatório")
    private String endereco;
    
    @JsonProperty
    @NotBlank(message = "Senha do cliente é um campo obrigatório")
    private String senha;

    PessoaFisicaForm() {
    }

    public PessoaFisicaForm(String nome, String cpf, String endereco, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.senha = senha;
    }

    public Cliente toCliente(){
        return new Cliente(cpf, nome, endereco, senha);
    }
}
