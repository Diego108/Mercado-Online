package br.com.cris.mercadoonline.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Categoria.class)
public abstract class Categoria_ {

	public static volatile SingularAttribute<Categoria, String> nome;
	public static volatile SingularAttribute<Categoria, Integer> id;
	public static volatile SingularAttribute<Categoria, Categoria> categoriaPai;

	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String CATEGORIA_PAI = "categoriaPai";

}

