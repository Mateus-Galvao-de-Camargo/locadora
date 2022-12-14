-- Utilizar em um banco MYsql 8.0.31 nomeado "locadora" e usando UTF-8

-- Cria a tabela de cliente
CREATE TABLE cliente(
id INT(6) NOT NULL AUTO_INCREMENT,
nome VARCHAR(90) NOT NULL,
endereco VARCHAR(150) NOT NULL,
cpf VARCHAR(14) NOT NULL,
email VARCHAR(45) NULL,
telefone VARCHAR(45) NOT NULL,
PRIMARY KEY(id, nome)
);
--

-- Cria a tabela de filme
CREATE TABLE filme(
id INT(6) NOT NULL AUTO_INCREMENT,
nome VARCHAR(120) NOT NULL,
produtora VARCHAR(120) NOT NULL,
lancamento DATE NOT NULL,
PRIMARY KEY(id, nome)
);
--

-- Cria a tabela de emprestimo
CREATE TABLE emprestimo(
id INT(8) NOT NULL AUTO_INCREMENT,
emprestimo DATE NOT NULL,
vencimento DATE NOT NULL,
fk_cliente INT(6) NOT NULL,
fk_filme INT(6) NOT NULL,
FOREIGN KEY(fk_cliente) REFERENCES cliente(id),
FOREIGN KEY(fk_filme) REFERENCES filme(id),
PRIMARY KEY(id)
);
--