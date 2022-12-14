package com.nefi.mateus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.nefi.mateus.entity.Emprestimo;

public class EmprestimoDAO implements DAO<Emprestimo> {

	private ClienteDAO clienteDAO;

	private FilmeDAO filmeDAO;

	public EmprestimoDAO() {
		this.clienteDAO = new ClienteDAO();
		this.filmeDAO = new FilmeDAO();
	}

	@Override
	public Object get(Long id) {
		Emprestimo emprestimo = null;
		String sql = "select * from emprestimo where id = ?";

		// Recupera a conexão com o banco
		Connection conexao = null;

		// Criar uma preparação da consulta
		PreparedStatement stm = null;

		// Criar uma classe que guarde o retorno da operação
		ResultSet rset = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, id.intValue());
			rset = stm.executeQuery();

			while (rset.next()) {
				emprestimo = new Emprestimo();

				// atribui campo para atributo
				emprestimo.setId(rset.getLong("id"));
				emprestimo.setEmprestimo(rset.getDate("emprestimo"));
				emprestimo.setVencimento(rset.getDate("vencimento"));

				// buscando as chaves estrangeiras
				emprestimo.setCliente(this.clienteDAO.get(rset.getLong("fk_cliente_id")));
				emprestimo.setFilme(this.filmeDAO.get(rset.getLong("fk_filme_id")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return emprestimo;
	}

	@Override
	public List<Emprestimo> getAll() {
		List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();

		String sql = "select * from emprestimo";

		// Recupera a conexão com o banco
		Connection conexao = null;

		// Criar uma preparação da consulta
		PreparedStatement stm = null;

		// Criar uma classe que guarde o retorno da operação
		ResultSet rset = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			rset = stm.executeQuery();

			while (rset.next()) {
				Emprestimo emprestimo = new Emprestimo();

				// atribui campo para atributo
				emprestimo.setId(rset.getLong("id"));
				emprestimo.setEmprestimo(rset.getDate("emprestimo"));
				emprestimo.setVencimento(rset.getDate("vencimento"));

				// buscando as chaves estrangeiras
				emprestimo.setCliente(this.clienteDAO.get(rset.getLong("fk_cliente_id")));
				emprestimo.setFilme(this.filmeDAO.get(rset.getLong("fk_filme_id")));

				emprestimos.add(emprestimo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return emprestimos;
	}

	@Override
	public int save(Emprestimo emprestimo) {
		String sql = "insert into emprestimo (emprestimo, vencimento, fk_cliente_id, fk_filme_id)" + " values (?, ?, ?, ?)";

		// Recupera a conexão com o banco
		Connection conexao = null;

		// Criar uma preparação da consulta
		PreparedStatement stm = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setDate(1, emprestimo.getEmprestimo());
			stm.setDate(2, emprestimo.getVencimento());
			stm.setLong(3, emprestimo.getCliente().getId());
			stm.setLong(4, emprestimo.getFilme().getId());

			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public boolean update(Emprestimo emprestimo, String[] params) {
		String sql = "update emprestimo set emprestimo = ?, vencimento = ?, fk_cliente_id = ?, fk_filme_id = ? where id = ?";

		// Recupera a conexão com o banco
		Connection conexao = null;

		// Criar uma preparação da consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setDate(1, emprestimo.getEmprestimo());
			stm.setDate(2, emprestimo.getVencimento());
			stm.setLong(3, emprestimo.getCliente().getId());
			stm.setLong(4, emprestimo.getFilme().getId());
			stm.setLong(5, emprestimo.getId());

			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Emprestimo emprestimo) {
		String sql = "delete from emprestimo where id = ?";

		// Recupera a conexão com o banco
		Connection conexao = null;

		// Criar uma preparação da consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setLong(1, emprestimo.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
