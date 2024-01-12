package br.com.f1rst.challenge.f1rstbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.f1rst.challenge.f1rstbank.controller.form.FiltraMovimentacaoForm;
import br.com.f1rst.challenge.f1rstbank.controller.view.MovimentacaoView;
import br.com.f1rst.challenge.f1rstbank.service.MovimentacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("movimentacoes")
@Tag(name = "Movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService service;

    @GetMapping
    @Operation(summary = "Relatorio de Movimentacoes")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Conta nao encontrada")
    })
    public ResponseEntity<?> filtrar(FiltraMovimentacaoForm form) {
        List<MovimentacaoView> movimentacoes = service.filtraMovimentacoes(form);

        return ResponseEntity.ok(movimentacoes);

    }

}
