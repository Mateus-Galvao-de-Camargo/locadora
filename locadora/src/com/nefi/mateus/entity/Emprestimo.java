package com.nefi.mateus.entity;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
	
	private Long id;
	private Date emprestimo;
	private Date vencimento;
	private Cliente cliente;
	private Filme filme;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Date emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public String getEmprestimoFormatado() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.emprestimo.toLocalDate().format(dataFormatada).toString();
	}
	
	public String getVencimentoFormatado() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.vencimento.toLocalDate().format(dataFormatada).toString();
	}
}
