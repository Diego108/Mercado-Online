package br.com.cris.mercadoonline.resource;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cris.mercadoonline.dto.CategoriaDTO;
import br.com.cris.mercadoonline.event.ReturnResourceEvent;
import br.com.cris.mercadoonline.model.Categoria;
import br.com.cris.mercadoonline.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@RequestMapping("/categorias")
@Api(value = "Eventos API REST - Categoria Resource")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 401, message = "Não autorizado para visualização."),
		@ApiResponse(code = 403, message = "O recurso que você está tentando acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso que você está tentando acessar não foi encontrado.") 
})
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

		if (categoria.isPresent()) {
			CategoriaDTO categoriaDTO = CategoriaDTO.builder().nome(categoria.get().getNome())
					.id(categoria.get().getId()).build();
			return ResponseEntity.ok().body(categoriaDTO);
		} else {

			return ResponseEntity.noContent().build();
		}
	}
	
	@ApiOperation(value = "Retorna um recurso de Categoria.")
	@GetMapping("/findByCategoriaPai/{id}")
	public ResponseEntity<List<CategoriaDTO>> findByCategoriaPai(@PathVariable String id) {

		List<CategoriaDTO> categoriasDTO = new ArrayList<>();
		Categoria categoria = Categoria.getInstance();
		
		categoria.setId(Integer.valueOf(!id.equals("undefined") ? id : "0"));
		List<Categoria> categorias = this.categoriaService.findByCategoriaPai(categoria);	
		
		if (!categorias.isEmpty()) {
			
			for(Categoria categ: categorias) {
				
				categoriasDTO.add(CategoriaDTO.builder().id(categ.getId()).nome(categ.getNome()).idPai(null).build());
			}
			
			
		} 
		
		return ResponseEntity.ok().body(categoriasDTO);
	}
	

	@ApiOperation(value = "Retorna um recurso de Categoria.")
	@PostMapping("/{idPai}")
	public ResponseEntity<Categoria> save(@RequestBody @Valid CategoriaDTO categoriaDTO,
			@PathVariable(value = "idPai") Integer idPai, HttpServletResponse httpServletResponse) {

		if (categoriaDTO.getNome() != null && categoriaDTO.getNome().length() > 0) {

			Optional<Categoria> categoriaPai = this.categoriaService.findById(categoriaDTO.getIdPai());
			Categoria categoriaSave = Categoria.valueOf(categoriaDTO);

			if (categoriaPai.isPresent()) {
				categoriaSave.setCategoriaPai(categoriaPai.get());
			}

			this.categoriaService.save(categoriaSave);

			this.applicationEventPublisher
					.publishEvent(new ReturnResourceEvent(categoriaSave, httpServletResponse, categoriaSave.getId()));

			return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSave);
		}

		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
	}

	@ApiOperation(value = "Retorna um recurso de Categoria.")
	@PutMapping()
	public ResponseEntity<Categoria> update(@RequestBody @Valid CategoriaDTO categoriaDTO,
			HttpServletResponse httpServletResponse) {

		Categoria categoriaUpdate = null;

		if (categoriaDTO.getNome() != null && categoriaDTO.getNome().length() > 0 && categoriaDTO.getId() != 0) {

			categoriaUpdate = Categoria.valueOf(categoriaDTO);

			this.categoriaService.save(categoriaUpdate);

			this.applicationEventPublisher.publishEvent(
					new ReturnResourceEvent(categoriaUpdate, httpServletResponse, categoriaUpdate.getId()));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaUpdate);
	}

	@ApiOperation(value = "Retorna um recurso de Categoria.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deletar(@PathVariable("id") Integer idCategoria,
			HttpServletResponse httpServletResponse) {

		if (this.categoriaService.delete(idCategoria)) {

			return ResponseEntity.status(HttpStatus.OK).body(true);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
