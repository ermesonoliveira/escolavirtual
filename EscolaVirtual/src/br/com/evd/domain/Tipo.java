package br.com.evd.domain;

public class Tipo {

	private int id;
	private String descricao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
		
	}
	@Override
	public String toString() {
		String a;
		a = id + descricao ;
		return a;
	}

}
