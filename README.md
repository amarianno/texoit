# Texo IT - Exercício

### Desenvolva uma API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.


* Fiz o exercício com Java 11, Spring boot com JPA, H2, Rest repositorys, opencsv e RestAssured para o teste
* Usei conceitos de arquitetura hexagonal separando o domain do da infra e usando ports e adapters.
* Toda a parte de infra está no pacote *br.com.textoit.teste.movielist.infra*
* Toda a parte do domain está no pacote *br.com.textoit.teste.movielist.domain*
* A api está no pacote *br.com.textoit.teste.movielist.adapters* com seu Controller, DTOs e configurações


# Sobre a solução

 * no startup o banco H2 é carregado com os dados do CSV
 * Quando há mais de um producer, estou carregando uma linha na base de dados pra cada um deles;
 * O único teste faz uma chamada na API e traz os dados conforme o que foi passado na especificação;
 * O banco de dados poderá ser acessado [nesse link](http://localhost:8080/h2-console)
 * Implementado com base no nível 2 de maturidade de Richardson, uma api GET acessada através da URI [http://localhost:8080/filme/intervalos](http://localhost:8080/filme/intervalos)
 * Não usei lombok para fins didáticos mas normalmente uso nos projetos;

# Rodar o projeto

Na pasta do projeto rodar o comando abaixo que vai rodar o teste e gerar um jar

```
mvn clean install
```

Acessar a pasta target e executar o comando abaixo

```
java -jar movie-list.jar
```

