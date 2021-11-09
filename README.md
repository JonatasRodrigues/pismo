[![CircleCI](https://circleci.com/gh/JonatasRodrigues/pismo.svg?style=svg)](https://circleci.com/gh/JonatasRodrigues/pismo)
[![codecov](https://codecov.io/gh/JonatasRodrigues/pismo/branch/main/graph/badge.svg)](https://codecov.io/gh/JonatasRodrigues/pismo)

# Pismo

Aplicação para realizar transações vinculada a uma conta de cliente.

# Tecnologias

<li>Java 8</li>
<li>SpringBoot</li>
<li>SpringJPA</li>
<li>Spring Actuator</li>
<li>Maven 3.5.2</li>
<li>Swagger</li>
<li>Docker e Docker-Compose</li>
<li>Mysql v. 14 14 for linux</li>
<li>JUnit</li>
<li>CircleCi</li>

# Instruções

O comando deverá ser executado via terminal.
Fazer o checkout via GitHub e na raiz do projeto executar o comando abaixo para criar as imagens e os conteiners Java e Mysql-server.

- docker-compose up --build

A partir de agora você poderá acessar os serviços.


## Endpoints

### criar uma nova conta
- POST -> http://localhost:8080/api/v1/accounts
- Request body 

```
{ 
  "document_number": "123456789001" 
}
```

- Response body - StatusCode: 201
``` 
{
    "account_id": 1,
    "document_number": "123456789001"
}
```
### consultar uma conta
- GET -> http://localhost:8080/api/v1/accounts/{id}
- Response body - StatusCode: 200

``` 
{
    "account_id": 1,
    "document_number": "123456789001"
}
```

### criar uma nova transação
- POST -> http://localhost:8080/api/v1/transactions
- Request body
```
{
  "account_id": 1, 
  "operation_type_id": 4, 
  "amount": 12.50
}
```

- Response body - StatusCode: 201
```
{
    "amount": 12.50,
    "account_id": 1,
    "operation_type_id": 4,
    "transaction_id": 2
}
```

## Swagger
- http://localhost:8080/swagger-ui.html

## Actuator
- http://localhost:8080/actuator


