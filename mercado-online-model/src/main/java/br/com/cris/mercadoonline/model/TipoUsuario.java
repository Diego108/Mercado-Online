package br.com.cris.mercadoonline.model;

public enum TipoUsuario {

	PF(0, " Pessoa Fisíca "), PJ(1, " Pessoa Juridíca ");
	
	private Integer id;
	
	private String descricao;

	TipoUsuario(Integer id, String descricao) {
		
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
