package br.com.f1rst.challenge.f1rstbank.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.f1rst.challenge.f1rstbank.controller.exceptions.ContaNaoEncontradaException;
import br.com.f1rst.challenge.f1rstbank.controller.exceptions.SaldoInsuficienteException;
import br.com.f1rst.challenge.f1rstbank.controller.form.ContaCorrenteForm;
import br.com.f1rst.challenge.f1rstbank.controller.form.MovimentacaoForm;
import br.com.f1rst.challenge.f1rstbank.controller.form.PessoaFisicaForm;
import br.com.f1rst.challenge.f1rstbank.controller.form.TransferenciaForm;
import br.com.f1rst.challenge.f1rstbank.controller.view.DadosDaContaView;
import br.com.f1rst.challenge.f1rstbank.controller.view.ExtratoView;
import br.com.f1rst.challenge.f1rstbank.converters.DadosDaContaCoverter;
import br.com.f1rst.challenge.f1rstbank.converters.ExtratoConverter;
import br.com.f1rst.challenge.f1rstbank.domain.ContaCorrente;
import br.com.f1rst.challenge.f1rstbank.domain.Correntista;
import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;
import br.com.f1rst.challenge.f1rstbank.domain.MovimentacaoDeConta;
import br.com.f1rst.challenge.f1rstbank.domain.Operacao;
import br.com.f1rst.challenge.f1rstbank.factories.ContaFactory;
import br.com.f1rst.challenge.f1rstbank.repository.ContasCorrenteRepository;
import br.com.f1rst.challenge.f1rstbank.repository.CorrentistaRepository;
import br.com.f1rst.challenge.f1rstbank.repository.MovimentacaoRepository;

@Service
public class ContaService {

    private final ContasCorrenteRepository contasCorrenteRepository;
    private final CorrentistaRepository correntistaRepository;
    private final MovimentacaoRepository movimentacaoRepository;
    private final ContaFactory factory;
    private final DadosDaContaCoverter dadosDaContaConverter;

    private final RabbitTemplate rabbitTemplate;


    public ContaService(ContasCorrenteRepository contasCorrenteRepository, CorrentistaRepository correntistaRepository, MovimentacaoRepository movimentacaoRepository, ContaFactory factory, DadosDaContaCoverter dadosDaContaConverter,  RabbitTemplate rabbitTemplate, ExtratoConverter extratoConverter) {
        this.contasCorrenteRepository = contasCorrenteRepository;
        this.correntistaRepository = correntistaRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.factory = factory;
        this.dadosDaContaConverter = dadosDaContaConverter;
        this.rabbitTemplate = rabbitTemplate;
        this.extratoConverter = extratoConverter;
    }

    @Autowired
    private ExtratoConverter extratoConverter;

    public ExtratoView consultarExtrato(ContaCorrenteForm form) {
        ContaCorrente conta = buscaContaPor(form.toDadosDaConta());

        List<MovimentacaoDeConta> movimentacoes = movimentacaoRepository.findAllByConta(conta);

        return extratoConverter.convert(conta.getDadosDaConta(), movimentacoes);
    }


    public DadosDaContaView criarConta(PessoaFisicaForm pessoaFisicaForm) {
        Correntista correntista = pessoaFisicaForm.toCorrentista();

        correntistaRepository.save(correntista);

        ContaCorrente conta = factory.criarConta(correntista);

        contasCorrenteRepository.save(conta);

        DadosDaConta dadosDaConta = conta.getDadosDaConta();
        return dadosDaContaConverter.convert(dadosDaConta);
    }

    public void fecharConta(DadosDaConta dadosDaConta) {
        ContaCorrente conta = buscaContaPor(dadosDaConta);

        contasCorrenteRepository.delete(conta);
    }

    @Transactional
    public void movimentar(MovimentacaoForm form) {
        movimentarConta(form.getDadosDaConta(), form.getValor(), form.getOperacao());
    }

    @Transactional
    public void transferir(TransferenciaForm form) {
        BigDecimal valor = form.getValor();

        DadosDaConta origem = form.getDadosDeOrigem();
        movimentarConta(origem, valor, Operacao.SAQUE);

        DadosDaConta destino = form.getDadosDeDestino();
        movimentarConta(destino, valor, Operacao.DEPOSITO);
    }

    private ContaCorrente buscaContaPor(DadosDaConta dadosDaConta) {
        Optional<ContaCorrente> opContaCorrente = contasCorrenteRepository
                .findByDadosDaConta(dadosDaConta);


        if (opContaCorrente.isEmpty()) {
            throw new ContaNaoEncontradaException("Conta não encontrada");
        }

        return opContaCorrente.get();
    }

    private void movimentarConta(DadosDaConta dadosDaConta, BigDecimal valor, Operacao operacao) {
        ContaCorrente contaCorrente = buscaContaPor(dadosDaConta);

        if (operacao.equals(Operacao.SAQUE)) {
            BigDecimal saldo = movimentacaoRepository
                    .findAllByConta(contaCorrente)
                    .stream()
                    .map(MovimentacaoDeConta::getValor)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            if (saldo.compareTo(valor) < 0 ) {
                throw new SaldoInsuficienteException("Saldo insuficiente para a operação");
            }
        }

        MovimentacaoDeConta movimentacao = new MovimentacaoDeConta(contaCorrente, valor, operacao);

        movimentacaoRepository.save(movimentacao);

        // INFO: Envio de notificação por email
        var correntista = contaCorrente.getCorrentista();

        rabbitTemplate.convertAndSend("transacao", "envio-de-emails");

    }
}
