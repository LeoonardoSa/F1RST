package br.com.f1rst.challenge.f1rstbank.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import br.com.f1rst.challenge.f1rstbank.domain.Cliente;


public interface ClienteRepository extends Repository<Cliente, Integer> {

    void save(Cliente cliente);

    Optional<Cliente> findById(Integer ClienteId);
}
