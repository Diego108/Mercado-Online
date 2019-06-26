package br.com.cris.mercadoonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cris.mercadoonline.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

}
