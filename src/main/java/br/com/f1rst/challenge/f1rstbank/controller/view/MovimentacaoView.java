package br.com.f1rst.challenge.f1rstbank.controller.view;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MovimentacaoView {

    private LocalDateTime data;
    private BigDecimal valor;

}
