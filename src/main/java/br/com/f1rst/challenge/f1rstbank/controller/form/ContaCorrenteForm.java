package br.com.f1rst.challenge.f1rstbank.controller.form;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaCorrenteForm {
    @JsonProperty
    @NotBlank(message = "banco é um campo obrigatório e não pode estar em branco")
    private String banco;
    @JsonProperty
    @NotBlank(message = "agência é um campo obrigatório e não pode estar em branco")
    private String agencia;
    @JsonProperty
    @NotBlank(message = "número é um campo obrigatório e não pode estar em branco")
    private String numero;

    public DadosDaConta toDadosDaConta() {
        return new DadosDaConta(banco, agencia, numero);
    }
}
