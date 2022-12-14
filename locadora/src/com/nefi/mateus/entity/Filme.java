package com.nefi.mateus.entity;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Filme {
	
	private Long id;
	private String nome;
	private String produtora;
	private Date lancamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProdutora() {
		return produtora;
	}

	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}

	public Date getLancamento() {
		return lancamento;
	}

	@Override
	public String toString() {
		return getNome();
	}

	public void setLancamento(Date lancamento) {
		this.lancamento = lancamento;
	}
	
	public String getLancamentoFormatado() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.lancamento.toLocalDate().format(dataFormatada).toString();
	}
}
