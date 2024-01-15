package br.com.f1rst.challenge.f1rstbank.controller.form;

import org.hibernate.validator.constraints.br.CNPJ;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.f1rst.challenge.f1rstbank.domain.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PessoaJuridicaForm {

    @JsonProperty
    @CNPJ(message = "CNPJ Invalido")
    @NotNull(message = "CNPJ é um campo obrigatório")
    private String cnpj;
    @JsonProperty
    @NotBlank(message = "Nome do Cliente é um campo obrigatório e não pode estar em branco")
    private String nome;

    @JsonProperty
    @NotBlank(message = "Endereço do cliente é um campo obrigatório")
    private String endereco;
    
    @JsonProperty
    @NotBlank(message = "Senha do cliente é um campo obrigatório")
    private String senha;

    PessoaJuridicaForm() {
    }

    public PessoaJuridicaForm(String nome, String cnpj, String endereco, String senha) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.endereco = endereco;
        this.senha = senha;
    }

    public Cliente toCliente(){
        return new Cliente(cnpj, nome, endereco, senha);
    }
}
