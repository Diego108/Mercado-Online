package br.com.cris.mercadoonline.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PessoaDTO {
	
	private String nome;

	private String sobrenome;
	
	private BigDecimal cpfCnpj;
	
	private LocalDate dataNascimento;
	
	private String tipoUsuario;
	
	private boolean ativo;
	
	private String logradouro;
	
	private String complemento;
	
	private String referencia;
	
	private String tipoResidencia;
	
	private Integer numero;
}
