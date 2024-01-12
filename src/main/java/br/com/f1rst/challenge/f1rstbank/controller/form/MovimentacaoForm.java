package br.com.f1rst.challenge.f1rstbank.controller.form;

import java.math.BigDecimal;

import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;
import br.com.f1rst.challenge.f1rstbank.domain.Operacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class MovimentacaoForm {
    @NotNull
    private DadosDaConta dadosDaConta;

    @NotNull
    @DecimalMin("0.1")
    private BigDecimal valor;

    public Operacao getOperacao() {
        return operacao;
    }

    public void setOperacao(Operacao operacao) {
        this.operacao = operacao;
    }

    private Operacao operacao;

    public DadosDaConta getDadosDaConta() {
        return dadosDaConta;
    }

    public void setDadosDaConta(DadosDaConta dadosDaConta) {
        this.dadosDaConta = dadosDaConta;
    }


    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
