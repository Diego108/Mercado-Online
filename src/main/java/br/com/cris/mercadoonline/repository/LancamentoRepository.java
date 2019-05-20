package br.com.cris.mercadoonline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cris.mercadoonline.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Integer>{

}
