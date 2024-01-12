package br.com.f1rst.challenge.f1rstbank.converters;

import org.springframework.stereotype.Component;

import br.com.f1rst.challenge.f1rstbank.controller.view.DadosDaContaView;
import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;

@Component
public class DadosDaContaCoverter {

    public DadosDaContaView convert(DadosDaConta dadosDaConta) {
        return new DadosDaContaView(
                dadosDaConta.getBanco(),
                dadosDaConta.getAgencia(),
                dadosDaConta.getNumero()
        );
    }

}
