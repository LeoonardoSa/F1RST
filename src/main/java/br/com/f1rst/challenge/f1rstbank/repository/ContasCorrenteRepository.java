package br.com.f1rst.challenge.f1rstbank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.Repository;

import br.com.f1rst.challenge.f1rstbank.domain.ContaCorrente;
import br.com.f1rst.challenge.f1rstbank.domain.Correntista;
import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;

public interface ContasCorrenteRepository extends Repository<ContaCorrente, Integer> {

    void save(ContaCorrente novaConta);

    @EntityGraph("contaComCorrentista")
    Optional<ContaCorrente> findByDadosDaConta(DadosDaConta dadosDaConta);

    Optional<ContaCorrente> findByCorrentista(Correntista correntista);

    void delete(ContaCorrente conta);

    long count();

}
