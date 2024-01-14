package br.com.f1rst.challenge.f1rstbank.factories;

import java.util.Random;

import org.springframework.stereotype.Component;

import br.com.f1rst.challenge.f1rstbank.domain.ContaCorrente;
import br.com.f1rst.challenge.f1rstbank.domain.Cliente;

@Component
public class ContaFactory {

    private static final String BANCO_DEFAULT = "033";
    private static final String AGENCIA_DEFAULT = "04254";

    private static final Random GERADOR_DE_NUMEROS = new Random();

    public ContaCorrente criarConta(Cliente cliente) {
        String numero = Integer.toString(gerarNumeroDaConta());
        return new ContaCorrente(BANCO_DEFAULT, AGENCIA_DEFAULT, numero, cliente);
    }

    private Integer gerarNumeroDaConta() {
        return GERADOR_DE_NUMEROS.nextInt(Integer.MAX_VALUE);
    }
}
