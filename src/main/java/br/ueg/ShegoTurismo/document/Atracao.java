package br.ueg.ShegoTurismo.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import br.ueg.ShegoTurismo.document.AtracaoCategoria;;

@Document
public class Atracao {
	
	@Id
	private String id;
	private String nome;
	private String descricao;
	private String bairro;
	private String cidade;
	private List<AtracaoCategoria> categorias;
	
	public Atracao(String id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public List<AtracaoCategoria> getCategorias() {
		return categorias;
	}
	public void setCategorias(List<AtracaoCategoria> categorias) {
		this.categorias = categorias;
	}
	
	
}
