package br.com.f1rst.challenge.f1rstbank.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.util.Assert;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimentacoes")
public class MovimentacaoDeConta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SuppressWarnings("PMD.UnusedPrivateField")
    private Integer id;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private Operacao operacao;

    @ManyToOne
    private ContaCorrente conta;

    private LocalDateTime data = LocalDateTime.now();

    protected MovimentacaoDeConta() {
    }

    public MovimentacaoDeConta(ContaCorrente conta, BigDecimal valor, Operacao operacao) {
        Assert.notNull(conta, "Conta corrente não pode ser nula");
        Assert.notNull(valor, "Valor não pode ser nulo");
        Assert.isTrue(valor.compareTo(BigDecimal.ZERO) > 0, "Valor deve ser maior que zero");
        Assert.notNull(operacao, "Operação não pode ser nula");

        this.valor = valor;
        this.operacao = operacao;
        this.conta = conta;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public BigDecimal getValor() {
        if (operacao.equals(Operacao.SAQUE)) {
            return valor.negate();
        }

        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public ContaCorrente getConta() {
        return conta;
    }
}
