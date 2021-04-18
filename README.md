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

Os endpoints também podem ser acessados em  
> http://localhost:8080/swagger/index.html

* Criação de uma conta   
``` 
POST /accounts

# request body    
{ 
  "document_number": "12345678900" 
} 
```
* Consulta de informações de uma conta
```
GET /accounts/:accountId 

# response Body: 
{ 
  "account_id": 1, 
  "document_number": "12345678900" 
} 
```
* Criação de uma transação
```
POST /transactions
 
# request Body: 
{ 
  "account_id": 1, 
  "operation_type_id": 4, 
  "amount": 123.45 
}
```

### Configurações para execução

#### Inicialização do banco de dados
Para efetuar a criação das tabelas de controle do Flyway em um banco vazio adicione a configuração `spring.flyway.baseline-on-migrate: true` no application.yaml ou execute o comando `make initialize-flyway`.  
Após a inicialização do flyway, a cada execução as migrações serão validadas e/ou executadas automaticamente.  

#### Comandos 

```
# build
> make build

# executar testes
> make test

# executar aplicacao
> make run

# executar flyway diretamente
make local-migration 
 
```

