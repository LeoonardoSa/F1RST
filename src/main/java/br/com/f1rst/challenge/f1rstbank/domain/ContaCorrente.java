package br.com.f1rst.challenge.f1rstbank.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.OneToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(
          name = "pegaTudo",
          includeAllAttributes = true
        ),

        @NamedEntityGraph(
                name = "contaComCorrentista",
                attributeNodes = {
                        @NamedAttributeNode("correntista"),
                }

        )
})

@EqualsAndHashCode(of = {"dadosDaConta"})
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Getter
    @Setter
    @Embedded
    private DadosDaConta dadosDaConta;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Correntista correntista;

    public ContaCorrente(String banco, String agencia, String numero, Correntista correntista){
        this.dadosDaConta = new DadosDaConta(banco, agencia, numero);
        this.correntista = correntista;
    }

    protected ContaCorrente() {
    }

    public boolean identificadaPor(String banco, String agencia, String numero) {
        return this.dadosDaConta.equals(new DadosDaConta(banco, agencia, numero));
    }

    public Integer getId() {
        return id;
    }

    public Correntista getCorrentista() {
        return correntista;
    }

    public DadosDaConta getDadosDaConta() {
        return dadosDaConta;
    }
}
