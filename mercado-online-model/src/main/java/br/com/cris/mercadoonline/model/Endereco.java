package br.com.cris.mercadoonline.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class Endereco {
	
	private String logradouro;
	
	private String complemento;
	
	private String referencia;
	
	@Column(name="tipo_residencia")
	private TipoResidencia tipoResidencia;
	
	private Integer numero;
}
