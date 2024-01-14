package br.com.f1rst.challenge.f1rstbank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.f1rst.challenge.f1rstbank.controller.form.ContaCorrenteForm;
import br.com.f1rst.challenge.f1rstbank.controller.form.MovimentacaoForm;
import br.com.f1rst.challenge.f1rstbank.controller.form.PessoaFisicaForm;
import br.com.f1rst.challenge.f1rstbank.controller.form.TransferenciaForm;
import br.com.f1rst.challenge.f1rstbank.controller.view.DadosDaContaView;
import br.com.f1rst.challenge.f1rstbank.controller.view.ExtratoView;
import br.com.f1rst.challenge.f1rstbank.service.ContaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService service;

    @PostMapping("/pessoa-fisica")
    public ResponseEntity<?> criarNovaContaPF(@Valid @RequestBody PessoaFisicaForm pessoaFisicaForm){

        DadosDaContaView dadosDaConta = service.criarContaPF(pessoaFisicaForm);

        return ResponseEntity.status(HttpStatus.CREATED).body(dadosDaConta);
    }
    
//    @PostMapping("/pessoaJuridica")
//    public ResponseEntity<?> criarNovaContaPJ(@Valid @RequestBody ClienteForm clienteForm){
//
//        DadosDaContaView dadosDaConta = service.criarConta(clienteForm);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(dadosDaConta);
//    }

    @DeleteMapping
    public ResponseEntity<?> fecharConta(@Valid @RequestBody ContaCorrenteForm contaForm){

        service.fecharConta(contaForm.toDadosDaConta());


        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<String> movimentarConta(@Valid @RequestBody MovimentacaoForm form){

        service.movimentar(form);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/transferir")
    public ResponseEntity<String> transferir(@Valid @RequestBody TransferenciaForm form){

        service.transferir(form);


        return ResponseEntity.noContent().build();
    }

    @GetMapping("/extrato")
    public ResponseEntity<?> extrato(@Valid @RequestBody ContaCorrenteForm form){
        ExtratoView extrato = service.consultarExtrato(form);

        return ResponseEntity.ok(extrato);
    }



}