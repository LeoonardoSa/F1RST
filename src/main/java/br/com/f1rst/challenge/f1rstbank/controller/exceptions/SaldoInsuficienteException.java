package br.com.f1rst.challenge.f1rstbank.controller.exceptions;

public class SaldoInsuficienteException extends IllegalArgumentException{

    private static final long serialVersionUID = 1L;

    public SaldoInsuficienteException(String message) {
        super(message);
    }

}
