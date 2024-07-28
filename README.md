# Locadora

Projeto em dupla com Nefi Lopez

## Funcionalidades

- Registro de clientes
- Registro de filmes
- Registro de empréstimos
- Listagem e edição de clientes, filmes e empréstimos

## Tecnologias Utilizadas

- Java
- JavaFX
- MySQL
  
## Pré-requisitos

- Java 8 ou superior

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
    
## Como Usar

1. Execute a aplicação conforme descrito na seção de instalação.
2. A interface gráfica será exibida, permitindo o registro, listagem e edição de clientes, filmes e empréstimos.
