package br.com.f1rst.challenge.f1rstbank.controller.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;
import br.com.f1rst.challenge.f1rstbank.domain.Operacao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FiltraMovimentacaoForm {
    private String banco;
    private String agencia;
    private String numero;
    private String status;
    private Operacao operacao;
    private BigDecimal valor;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate inicio;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fim;


    public DadosDaConta toDadosDaConta() {
        return new DadosDaConta(banco, agencia, numero,status);
    }

    public LocalDateTime toInicio() {
        return LocalDateTime.of(inicio, LocalTime.MIN);
    }

    public LocalDateTime toFim() {
        return LocalDateTime.of(fim, LocalTime.MAX);
    }

    public boolean hasFilter() {
        return inicio != null && fim != null && operacao != null;
    }

}
