package br.com.f1rst.challenge.f1rstbank.controller.view;

public class DadosDaContaView {
    private String banco;
    private String agencia;
    private String numero;

    DadosDaContaView() {
    }

    public DadosDaContaView(String banco, String agencia, String numero) {
        this.banco = banco;
        this.agencia = agencia;
        this.numero = numero;
    }

    public String getBanco() {
        return banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getNumero() {
        return numero;
    }
}
