package br.com.f1rst.challenge.f1rstbank.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Embedded
    private DadosDaConta dadosDaConta;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Cliente cliente;

    public ContaCorrente(String banco, String agencia, String numero, String status, Cliente cliente){
        this.dadosDaConta = new DadosDaConta(banco, agencia, numero, status);
        this.cliente = cliente;
    }

    protected ContaCorrente() {
    }

    public boolean identificadaPor(String banco, String agencia, String numero, String status) {
        return this.dadosDaConta.equals(new DadosDaConta(banco, agencia, numero, status));
    }

    public Integer getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public DadosDaConta getDadosDaConta() {
        return dadosDaConta;
    }
}
