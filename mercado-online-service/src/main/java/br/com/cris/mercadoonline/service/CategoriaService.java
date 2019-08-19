package br.com.cris.mercadoonline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cris.mercadoonline.model.Categoria;
import br.com.cris.mercadoonline.repository.CategoriaRepository;

@Service
public class CategoriaService extends GenericService<Categoria, Integer>{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> findByCategoriaPai(Categoria categoriaPai) {
	
		return this.categoriaRepository.findByCategoriaPai(categoriaPai);
	}
}
