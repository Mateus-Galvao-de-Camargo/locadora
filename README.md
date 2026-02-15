# Locadora

Feito junto com Nefi Lopez, @nefilj no Instagram.

## Funcionalidades

- Registro de clientes
- Registro de filmes
- Registro de empréstimos
- Listagem e edição de clientes, filmes e empréstimos

## Tecnologias Utilizadas

- Java - backend
- JavaFX - frontend
- MySQL - banco de dados
  
## Pré-requisitos

- Java 8

## Instalação

1. Clone o repositório:
    ```sh
    git clone https://github.com/Mateus-Galvao-de-Camargo/locadora.git
    ```
2. Configure o banco de dados no arquivo `locadora/src/com/nefi/mateus/dao/Conexao.java`.

3. Crie o banco de dados que está no arquivo `locadora/sql/script_locadora.sql`.

4. Navegue até o diretório do projeto:
    ```sh
    cd locadora
    ```
5. Compile o projeto:
    ```sh
    javac -d bin -sourcepath src $(find src -name '*.java')
    ```
6. Execute o projeto:
    ```sh
    java -cp bin com.nefi.mateus.menu.Menu
    ```
