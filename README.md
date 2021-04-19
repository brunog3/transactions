# Transactions

Este é um projeto baseado em [Spring Boot](https://spring.io/projects/spring-boot) e responsável por gerenciar a criação de Contas e Transações.

### Pré-requisitos

* [Java 11 (OpenJDK)](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=openj9)
* [Maven](https://maven.apache.org/download.cgi)
* [Postgresql](https://www.postgresql.org/)

### Dependências

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Lombok](https://projectlombok.org/)
* [Flyway](https://flywaydb.org/)
* [Swagger](https://swagger.io/)

### Endpoints

Os endpoints também podem ser acessados no Swagger a partir do endereço:  
> http://localhost:8080/swagger/index.html

##### Criação de uma conta   
``` 
POST /accounts

# request body    
{ 
  "document_number": "12345678900" 
} 
```
##### Consulta de informações de uma conta
```
GET /accounts/:accountId 

# response body: 
{ 
  "account_id": 1, 
  "document_number": "12345678900" 
} 
```
##### Criação de uma transação
```
POST /transactions
 
# request body: 
{ 
  "account_id": 1, 
  "operation_type_id": 4, 
  "amount": 123.45 
}
```

### Configurações para execução

#### Inicialização do banco de dados PostgreSQL utilizando Docker
O banco de dados pode ser iniciado utilizando o comando `make start-database` e o volume do banco de dados deve ser alterado no arquivo [docker-compose.db.yml](environment/docker-compose.db.yml).
As migrações serão validadas/executadas automaticamente na inicialização da aplicação pelo Flyway e os arquivos se encontram em [src/main/resources/db/migration](src/main/resources/db/migration).  

#### Comandos 
Um conjunto de comandos foi definido utilizando o utilitário [Make](https://www.gnu.org/software/make/) e estão disponíveis no arquivo [Makefile](Makefile)

```
# build
> make build

# executar testes
> make test

# inicializar banco de dados
> make start-database

# interromper banco de dados
> make stop-database

# executar aplicacao
> make run

# executar flyway diretamente
> make local-migration 
 
# gerar imagem docker
> make docker-build

# executar aplicacao utilizando docker
> make docker-run
```

