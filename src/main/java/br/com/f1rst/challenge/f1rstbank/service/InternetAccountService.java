package br.com.f1rst.challenge.f1rstbank.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import br.com.f1rst.challenge.f1rstbank.commands.CreateUser;
import br.com.f1rst.challenge.f1rstbank.controller.form.InternetBankAccountForm;
import br.com.f1rst.challenge.f1rstbank.repository.ContasCorrenteRepository;

@Service
public class InternetAccountService {

    private final ContasCorrenteRepository contasCorrenteRepository;
    private final RabbitTemplate rabbitTemplate;

    public InternetAccountService(ContasCorrenteRepository contasCorrenteRepository, RabbitTemplate rabbitTemplate) {
        this.contasCorrenteRepository = contasCorrenteRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void createUser(InternetBankAccountForm form) {
        if (form.rePasswordMatch()) {
            var contaCorrente = contasCorrenteRepository.findByDadosDaConta(form.getDadosDaConta()).orElseThrow();

            var cliente = contaCorrente.getCliente();

            var command = new CreateUser(cliente.getId(), form.getEmail(), form.getPassword());

            rabbitTemplate.convertAndSend("create-user", "create-user", command);
        }
    }
}
