package br.com.cris.mercadoonline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cris.mercadoonline.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	public List<Categoria> findByCategoriaPai(Integer idPai);
}
