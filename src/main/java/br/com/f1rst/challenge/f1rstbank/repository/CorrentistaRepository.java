package br.com.f1rst.challenge.f1rstbank.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.f1rst.challenge.f1rstbank.domain.Correntista;

public interface CorrentistaRepository extends Repository<Correntista, Integer> {

    void save(Correntista correntista);

    Optional<Correntista> findByExternalId(String externalId);

    Optional<Correntista> findById(Integer correntistaId);
}
