package br.com.f1rst.challenge.f1rstbank.converters;

import org.springframework.stereotype.Component;

import br.com.f1rst.challenge.f1rstbank.controller.view.MovimentacaoView;
import br.com.f1rst.challenge.f1rstbank.domain.MovimentacaoDeConta;

@Component
public class MovimentacaoConverter {
    public MovimentacaoView convert(MovimentacaoDeConta movimentacaoDeConta) {
        return new MovimentacaoView();
    }
}
