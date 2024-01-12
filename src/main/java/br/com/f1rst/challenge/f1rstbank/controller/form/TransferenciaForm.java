package br.com.f1rst.challenge.f1rstbank.controller.form;

import java.math.BigDecimal;

import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class TransferenciaForm {
    @NotNull
    private DadosDaConta dadosDeOrigem;

    @NotNull
    private DadosDaConta dadosDeDestino;


    @DecimalMin("0.1")
    @NotNull
    private BigDecimal valor;

    public DadosDaConta getDadosDeOrigem() {
        return dadosDeOrigem;
    }

    public void setDadosDeOrigem(DadosDaConta dadosDeOrigem) {
        this.dadosDeOrigem = dadosDeOrigem;
    }

    public DadosDaConta getDadosDeDestino() {
        return dadosDeDestino;
    }

    public void setDadosDeDestino(DadosDaConta dadosDeDestino) {
        this.dadosDeDestino = dadosDeDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
