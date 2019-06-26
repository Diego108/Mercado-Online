package br.com.cris.mercadoonline.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cris.mercadoonline.dto.CategoriaDTO;
import br.com.cris.mercadoonline.event.ReturnResourceEvent;
import br.com.cris.mercadoonline.model.Categoria;
import br.com.cris.mercadoonline.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping("/categorias")
@Api(value = "Eventos API REST - Categoria Resource")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	private CategoriaDTO categoriaDTO;
	
	@ApiOperation(value = "Retorna uma lista de recurso Categoria.")
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll(){
		
		List<Categoria> categorias = this.categoriaService.findAll();
		
		if(categorias.size() > 0) {
			
			return ResponseEntity.ok().body(categorias);
		}else {
			
			return ResponseEntity.noContent().build();
		}
	}
	
	@ApiOperation(value = "Retorna um recurso de Categoria.")
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id){
		
		Optional<Categoria> categoria = this.categoriaService.findById(id);
		
		if(categoria.isPresent()) {
			
			categoriaDTO = CategoriaDTO.builder().nome(categoria.get().getNome()).build();
			
			return ResponseEntity.ok().body(categoriaDTO);
		}else {
			
			return ResponseEntity.noContent().build();
		}
	}
	
	@ApiOperation(value = "Retorna um recurso de Categoria.")
	@PostMapping
	public ResponseEntity<Categoria> save(@RequestBody @Valid Categoria categoria, HttpServletResponse httpServletResponse){
		
		Categoria categoriaSave = this.categoriaService.save(categoria);
		
		this.applicationEventPublisher.publishEvent(new ReturnResourceEvent(categoriaSave, httpServletResponse, categoriaSave.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSave);
	}
}
