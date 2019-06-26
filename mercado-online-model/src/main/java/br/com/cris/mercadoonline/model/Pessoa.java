package br.com.cris.mercadoonline.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="pessoa")
public class Pessoa implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	@Size(min=1, max=50)
	private String nome;

	@Column(nullable = false)
	@Size(min=1, max=50)
	private String sobrenome;
	
	@NotNull
	@Column(name="cpf_cnpj", nullable = false)
	private BigDecimal cpfCnpj;
	
	@NotNull
	@Column(name="data_nascimento", nullable = false)
	private LocalDate dataNascimento;
	
	@Column(name="tipo_usuario", nullable = false)
	private TipoUsuario tipoUsuario;
	
	private boolean ativo;
	
	@Embedded
	private Endereco endereco;
}
