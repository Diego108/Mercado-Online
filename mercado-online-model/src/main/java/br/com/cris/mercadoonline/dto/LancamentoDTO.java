package br.com.cris.mercadoonline.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LancamentoDTO {

	private String descricao;
	
	private LocalDate dataVencimento;
	
	private LocalDate dataPagamento;
	
	private BigDecimal valor;
	
	private String observacao;
	
	private PessoaDTO pessoa;
	
	private CategoriaDTO categoria;
}
