package br.com.cris.mercadoonline.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.cris.mercadoonline.model.Categoria;
import br.com.cris.mercadoonline.model.Pessoa;
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
	
	private Pessoa pessoa;
	
	private Categoria categoria;
}
