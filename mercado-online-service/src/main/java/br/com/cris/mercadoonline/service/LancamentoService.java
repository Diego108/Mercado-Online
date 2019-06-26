package br.com.cris.mercadoonline.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.cris.mercadoonline.model.Lancamento;

@Service
public class LancamentoService extends GenericService<Lancamento , Integer>{

	@Override
	public Lancamento save(Lancamento t) {
		
		return null;
	}

	@Override
	public List<Lancamento> findAll() {
		
		return null;
	}

	@Override
	public Optional<Lancamento> findById(Integer id) {
		
		return null;
	}

	@Override
	public void delete(Integer id) {
		
	}

}
