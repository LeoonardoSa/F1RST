package br.com.f1rst.challenge.f1rstbank.controller.exceptions;

public class ContaNaoEncontradaException extends IllegalArgumentException {

    private static final long serialVersionUID = 1L;

	public ContaNaoEncontradaException(String s) {
        super(s);
    }
}
