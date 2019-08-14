package br.com.cris.mercadoonline.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public class GenericService<T , I>{
	
	@Autowired
	private JpaRepository<T, I> genericRepository;
	
	public  T save(T t) {
		
		return this.genericRepository.save(t);
	}
	
	public List<T> findAll(){
		
		return this.genericRepository.findAll();
	}
	
	public Optional<T> findById(I id){
		
		return this.genericRepository.findById(id);
	}
	
	public boolean delete(I id) {
		
		Optional<T> t = this.genericRepository.findById(id);
		
		if(t.isPresent()) {
			
			this.genericRepository.delete(t.get());
			return true;
		}
		
		return false;
	}
}
