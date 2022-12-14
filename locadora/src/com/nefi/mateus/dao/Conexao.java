package com.nefi.mateus.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static final String LOGIN_BANCO = "root";

	private static final String SENHA_BANCO = "";

	private static final String URL_BANCO = "jdbc:mysql://localhost:3306/locadora?autoReconnect=true&useSSL=false";

	public Connection getConnection() {
		Connection conexao = null;
		try {
			// Em caso de uso de mysql anterior a versão 8.0 substitua a atual classe driver pela seguinte: 'com.mysql.jdbc.Driver', em caso de uso apartir do mysql 8.0 a classe é "com.mysql.cj.jdbc.Driver"
			// Para usar outra versão do mysql troque a biblioteca de referência que está na pasta lib, por padrão a ativa é o mysql-connector-j-8.0.31.jar
			// Por conveniencia já está incluso o connector mysql/j para mysql 5.1.49 na pasta lib
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(Conexao.URL_BANCO, Conexao.LOGIN_BANCO, Conexao.SENHA_BANCO);
		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados. Erro: " + e);
		} catch (ClassNotFoundException e) {
			System.out.println("Não foi possível carregar a classe JDBC MySQL.\n Verifique na classe com/nefi/mateus/dao/Conexao.java\n Erro: " + e);
		} catch (Exception e) {
			System.out.println("Erro geral. Erro: " + e);
		}
		return conexao;
	}

}
