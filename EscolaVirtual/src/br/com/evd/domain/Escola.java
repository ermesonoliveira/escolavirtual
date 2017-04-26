package br.com.evd.domain;

public class Escola {

	private int id;
	private Long matricula;
	private String nome;
	private String cnpj;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}@Override
	public String toString() {
		String a;
		a = "ID " + id +" \n MATRICULA: " + matricula +"\nNOME: " + nome + "\nCNPJ" + cnpj;
		return a;
	}

}
