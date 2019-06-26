package br.com.cris.mercadoonline.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Endereco.class)
public abstract class Endereco_ {

	public static volatile SingularAttribute<Endereco, String> complemento;
	public static volatile SingularAttribute<Endereco, Integer> numero;
	public static volatile SingularAttribute<Endereco, String> logradouro;
	public static volatile SingularAttribute<Endereco, TipoResidencia> tipoResidencia;
	public static volatile SingularAttribute<Endereco, String> referencia;

	public static final String COMPLEMENTO = "complemento";
	public static final String NUMERO = "numero";
	public static final String LOGRADOURO = "logradouro";
	public static final String TIPO_RESIDENCIA = "tipoResidencia";
	public static final String REFERENCIA = "referencia";

}

