package br.com.cris.mercadoonline.model;

public enum TipoResidencia {
	
	C(0, " CASA "), A(1, " APARTAMENTO ");
	
	private Integer id;
	
	private String descricao;

	TipoResidencia(Integer id, String descricao) {
		
		this.id = id;
		this.descricao = descricao;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
