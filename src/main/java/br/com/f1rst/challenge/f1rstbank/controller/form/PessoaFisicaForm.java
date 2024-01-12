package br.com.f1rst.challenge.f1rstbank.controller.form;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.f1rst.challenge.f1rstbank.domain.Correntista;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PessoaFisicaForm {

    @JsonProperty
    @CPF(message = "CPF invalido")
    @NotNull(message = "CPF é um campo obrigatório")
    private String cpf;
    @JsonProperty
    @NotBlank(message = "Nome do Correntista é um campo obrigatório e não pode estar em branco")
    private String nome;

    @JsonProperty
    @NotBlank(message = "Email do correntista é um campo obrigatório")
    private String email;
    
    @JsonProperty
    @NotBlank(message = "Senha do correntista é um campo obrigatório")
    private String senha;

    PessoaFisicaForm() {
    }

    public PessoaFisicaForm(String nome, String cpf, String email, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Correntista toCorrentista(){
        return new Correntista(cpf, nome, email, senha);
    }
}
