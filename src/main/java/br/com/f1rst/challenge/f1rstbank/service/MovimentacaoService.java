package br.com.f1rst.challenge.f1rstbank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.f1rst.challenge.f1rstbank.controller.form.FiltraMovimentacaoForm;
import br.com.f1rst.challenge.f1rstbank.controller.view.MovimentacaoView;
import br.com.f1rst.challenge.f1rstbank.converters.MovimentacaoConverter;
import br.com.f1rst.challenge.f1rstbank.domain.ContaCorrente;
import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;
import br.com.f1rst.challenge.f1rstbank.domain.MovimentacaoDeConta;
import br.com.f1rst.challenge.f1rstbank.domain.Operacao;
import br.com.f1rst.challenge.f1rstbank.repository.ContasCorrenteRepository;
import br.com.f1rst.challenge.f1rstbank.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository repository;

    @Autowired
    private ContasCorrenteRepository contasCorrenteRepository;

    @Autowired
    private MovimentacaoConverter converter;

    public List<MovimentacaoView> filtraMovimentacoes(FiltraMovimentacaoForm form) {
        DadosDaConta dadosDaConta = form.toDadosDaConta();

        ContaCorrente conta = contasCorrenteRepository.findByDadosDaConta(dadosDaConta)
                .orElseThrow(() -> {throw new IllegalArgumentException("Conta não encontrada.");});


        List<MovimentacaoDeConta> movimentacoes;

        if (form.hasFilter()) {
            LocalDateTime inicio = form.toInicio();
            LocalDateTime fim = form.toFim();
            Operacao operacao = Operacao.DEPOSITO; // = form.getOperacao();

            movimentacoes = repository.filterMovimentacoesBy(dadosDaConta, inicio, fim, operacao);
        } else  {
            movimentacoes = repository.findAllByConta(conta);
        }

        return movimentacoes.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}
