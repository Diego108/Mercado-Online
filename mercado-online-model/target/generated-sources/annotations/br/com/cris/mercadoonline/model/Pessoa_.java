package br.com.cris.mercadoonline.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pessoa.class)
public abstract class Pessoa_ {

	public static volatile SingularAttribute<Pessoa, Boolean> ativo;
	public static volatile SingularAttribute<Pessoa, Endereco> endereco;
	public static volatile SingularAttribute<Pessoa, String> nome;
	public static volatile SingularAttribute<Pessoa, TipoUsuario> tipoUsuario;
	public static volatile SingularAttribute<Pessoa, Integer> id;
	public static volatile SingularAttribute<Pessoa, BigDecimal> cpfCnpj;
	public static volatile SingularAttribute<Pessoa, String> sobrenome;
	public static volatile SingularAttribute<Pessoa, LocalDate> dataNascimento;

	public static final String ATIVO = "ativo";
	public static final String ENDERECO = "endereco";
	public static final String NOME = "nome";
	public static final String TIPO_USUARIO = "tipoUsuario";
	public static final String ID = "id";
	public static final String CPF_CNPJ = "cpfCnpj";
	public static final String SOBRENOME = "sobrenome";
	public static final String DATA_NASCIMENTO = "dataNascimento";

}

