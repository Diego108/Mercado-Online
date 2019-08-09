package br.com.cris.mercadoonline.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	
	@ApiOperation(value = "Retorna uma lista de recurso Categoria.")
	@GetMapping("/findAll")
	public ResponseEntity<List<Categoria>> findAll(){
		
		List<Categoria> categorias = this.categoriaService.findAll();
		
		if(!categorias.isEmpty()) {
			return ResponseEntity.ok().body(categorias);
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@ApiOperation(value = "Retorna um recurso de Categoria.")
	@GetMapping("/findById/{id}")
	public ResponseEntity<CategoriaDTO> findById(@PathVariable Integer id){
		
		Optional<Categoria> categoria = this.categoriaService.findById(id);
		
		if(categoria.isPresent()) {
			CategoriaDTO categoriaDTO = CategoriaDTO.builder().nome(categoria.get().getNome()).build();
			return ResponseEntity.ok().body(categoriaDTO);
		}else {
			
			return ResponseEntity.noContent().build();
		}
	}
	
	@ApiOperation(value = "Retorna um recurso de Categoria.")
	@PostMapping()
	public ResponseEntity<Categoria> save(@RequestBody @Valid CategoriaDTO categoriaDTO, HttpServletResponse httpServletResponse){
		
		if(categoriaDTO.getNome() != null || categoriaDTO.getNome().length() > 0) {
			Categoria categoriaSave = this.categoriaService.save(Categoria.valueOf(categoriaDTO));
			this.applicationEventPublisher.publishEvent(new ReturnResourceEvent(categoriaSave, httpServletResponse, categoriaSave.getId()));		
			return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSave);
		}
			
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
	}
	
	@ApiOperation(value = "Retorna um recurso de Categoria.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable("id") Integer idCategoria, HttpServletResponse httpServletResponse){

		if(this.categoriaService.delete(idCategoria)) {
		
			return ResponseEntity.status(HttpStatus.OK).body(true);
		}
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
