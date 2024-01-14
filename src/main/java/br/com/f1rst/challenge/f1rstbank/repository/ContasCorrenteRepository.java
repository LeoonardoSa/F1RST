package br.com.f1rst.challenge.f1rstbank.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.f1rst.challenge.f1rstbank.domain.Cliente;
import br.com.f1rst.challenge.f1rstbank.domain.ContaCorrente;
import br.com.f1rst.challenge.f1rstbank.domain.DadosDaConta;

@org.springframework.stereotype.Repository
public interface ContasCorrenteRepository extends Repository<ContaCorrente, Integer> {

    void save(ContaCorrente novaConta);

    Optional<ContaCorrente> findByDadosDaConta(DadosDaConta dadosDaConta);

    Optional<ContaCorrente> findByCliente(Cliente cliente);

    void delete(ContaCorrente conta);

    long count();

}
