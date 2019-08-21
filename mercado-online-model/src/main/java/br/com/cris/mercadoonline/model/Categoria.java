package br.com.cris.mercadoonline.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.cris.mercadoonline.dto.CategoriaDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 30, unique = true)
	private String nome;

	@JoinColumn(name = "ID_CATEGORIA_PAI")
	@ManyToOne()
	private Categoria categoriaPai;
	
	@Transient
	private static Categoria categoria;
	
	private Categoria() { }
	
	public static Categoria valueOf(CategoriaDTO categoriaDTO){
		
		categoria = Categoria.getInstance();
		
		categoria.setId(categoriaDTO.getId());
		categoria.setNome(categoriaDTO.getNome());
		categoria.setCategoriaPai(null);
		
		return categoria;
	}


	public static Categoria getInstance(){
		
		if ( categoria != null) {
			return categoria;
		}
		
		categoria = new Categoria();
		
		return categoria;
	}
}
