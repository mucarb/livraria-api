package com.ribeiro.livraria.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity /* Definindo que esta classe é uma entidade na base de dados */
public class Livro implements Serializable { /* Serializando objetos dessa classe para utilizar externamente do código fonte */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) /* atribuir chaves primárias para a entidade */
	private Integer id;
	
	@NotEmpty(message = "Campo TITULO é requerido!")
	@Length(min = 3, max = 100, message = "O campo TITULO deve ter entre 3 e 50 caracteres!")
	private String titulo;
	
	@NotEmpty(message = "Campo NOME DO AUTOR é requerido!")
	@Length(min = 3, max = 100, message = "O campo NOME DO AUTOR deve ter entre 3 e 50 caracteres!")
	private String nomeAutor;

	@NotEmpty(message = "Campo TEXTO é requerido!")
	@Length(min = 10, max = 2000000, message = "O campo TEXTO deve ter entre 10 e 2000000 caracteres!")
	private String texto;

	@JsonIgnore /* Protegendo contra serializaçao de categoria, uma vez que a categoria ja foi serizalizada, evitando looping infinito */
	@ManyToOne /* realacao de muitos para um (Um ou Mais Livros é associado Uma Categoria )*/
	@JoinColumn(name = "categoria_id") /* definindo junção com categoria */
	private Categoria categoria;

	public Livro() {
	}

	public Livro(Integer id, String titulo, String nomeAutor, String texto, Categoria categoria) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.nomeAutor = nomeAutor;
		this.texto = texto;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		return Objects.equals(id, other.id);
	}
}
