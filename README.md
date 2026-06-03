# API Agencia de Viagens - GR02 SENAI

## Descricao
API RESTful desenvolvida com Java 21 e Spring Boot 3.2.5 para gerenciamento de destinos de viagem, desenvolvida como parte do Desafio GR02 da disciplina de Desenvolvimento de Sistemas Web - SENAI.

## Tecnologias
- Java 21
- Spring Boot 3.2.5
- Maven 3.9.16

## Como executar
1. Clone o repositorio
2. Acesse a pasta do projeto
3. Execute: mvn spring-boot:run
4. A API estara disponivel em http://localhost:8080

## Endpoints e Como Testar

### 1. Cadastrar Destino
**POST** /destinos
```json
{
    "nome": "Rio de Janeiro",
    "localizacao": "Rio de Janeiro",
    "descricao": "Cidade Maravilhosa, lar do Cristo Redentor e do Carnaval",
    "preco": 2500.00
}
```
Retorna: 201 Created

### 2. Listar Todos os Destinos
**GET** /destinos
Retorna: 200 OK com lista de destinos

### 3. Pesquisar Destinos
**GET** /destinos/pesquisar?termo=rio
Retorna: 200 OK com destinos filtrados por nome ou localizacao

### 4. Buscar Destino por ID
**GET** /destinos/1
Retorna: 200 OK com detalhes do destino

### 5. Avaliar Destino
**PATCH** /destinos/1/avaliar
```json
{
    "nota": 10
}
```
Retorna: 200 OK com nova media calculada

### 6. Excluir Destino
**DELETE** /destinos/1
Retorna: 204 No Content

## Estrutura do Projeto
src/
  main/
    java/com/agencia/viagens/
      controller/   - Endpoints REST
      service/      - Logica de negocio
      model/        - Entidades
      dto/          - Objetos de transferencia
      exception/    - Tratamento de erros
