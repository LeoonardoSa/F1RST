package br.com.f1rst.challenge.f1rstbank.converters;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.f1rst.challenge.f1rstbank.controller.view.ExtratoView;
import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;
import br.com.f1rst.challenge.f1rstbank.domain.MovimentacaoDeConta;

@Component
public class ExtratoConverter {

    public ExtratoView convert(DadosDaConta dadosDaConta, List<MovimentacaoDeConta> movimentacoes) {
        List<BigDecimal> valores = movimentacoes
                                        .stream()
                                        .map(MovimentacaoDeConta::getValor)
                                        .collect(Collectors.toList());

        BigDecimal saldo = movimentacoes.stream()
                .map(MovimentacaoDeConta::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new ExtratoView(dadosDaConta, valores , saldo);
    }
}
