package com.isaac.agenda.domain;

public class Contato {

	private Long codContato;
	private String nome;
	private Long telefone;

	public Contato() {
	}

	public Contato(Long codContato, String nome, Long telefone) {
		this.codContato = codContato;
		this.nome = nome;
		this.telefone = telefone;
	}

	public Long getCodContato() {
		return codContato;
	}

	public void setCodContato(Long codContato) {
		this.codContato = codContato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Contato [Código: " + codContato + ", Nome: " + nome + ", Telefone: " + telefone + "]";
	}
}
