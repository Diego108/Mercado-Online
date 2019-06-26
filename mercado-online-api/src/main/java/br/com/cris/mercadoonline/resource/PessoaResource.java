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

import br.com.cris.mercadoonline.dto.PessoaDTO;
import br.com.cris.mercadoonline.event.ReturnResourceEvent;
import br.com.cris.mercadoonline.model.Pessoa;
import br.com.cris.mercadoonline.service.PessoaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController()
@RequestMapping("/pessoas")
@Api(value = "Eventos API REST - Pessoa Resource")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	private PessoaDTO pessoaDTO;
	
	@ApiOperation(value = "Retorna uma lista de recurso Pessoa.")
	@GetMapping()
	public ResponseEntity<List<Pessoa>> findAll(){
		
		List<Pessoa> pessoas = this.pessoaService.findAll();
		
		if (pessoas.size() > 0) {

			return ResponseEntity.ok().body(pessoas);
		} else {

			return ResponseEntity.notFound().build();
		}
	}	
	
	@ApiOperation(value = "Retorna um recurso Pessoa.")
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDTO> findById(@PathVariable(value="id") Integer id){
		
		Optional<Pessoa> pessoa = this.pessoaService.findById(id);
		
		if (pessoa.isPresent()) {

			this.setPessoaDTO(PessoaDTO.builder().nome(pessoa.get().getNome()).sobrenome(pessoa.get().getSobrenome())
					.cpfCnpj(pessoa.get().getCpfCnpj()).dataNascimento(pessoa.get().getDataNascimento())
					.ativo(pessoa.get().isAtivo()).logradouro(pessoa.get().getEndereco().getLogradouro())
					.numero(pessoa.get().getEndereco().getNumero())
					.tipoResidencia(pessoa.get().getEndereco().getTipoResidencia().getDescricao())
					.tipoUsuario(pessoa.get().getTipoUsuario().getDescricao()).build());

			return ResponseEntity.ok().body(this.getPessoaDTO());
		} else {

			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Salva e retorna o recurso salvo na base de dados.")
	@PostMapping("/salvar")
	public ResponseEntity<Pessoa> save(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response){

		Pessoa pessoaSalva = this.pessoaService.save(pessoa);
		
		this.applicationEventPublisher.publishEvent(new ReturnResourceEvent(this, response, pessoaSalva.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@ApiOperation(value = "Deleta o recurso na base de dados.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Pessoa> delete(@PathVariable(value="id") Integer id){

		this.pessoaService.delete(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	public PessoaDTO getPessoaDTO() {
		return pessoaDTO;
	}

	public void setPessoaDTO(PessoaDTO pessoaDTO) {
		this.pessoaDTO = pessoaDTO;
	}
}
