<br />
<p align="center">

<h3 align="center">E-commerce PoC with Axon Framework</h3>
</p>

## About The Project

This project implements eventual consistency, event sourcing, cqrs while using axon framework

### Built With

* [Maven](https://maven.apache.org/)

## Getting Started

When you clone this project firstly run;
```sh
  cd e-commerce-importer ; mvn clean install -o -Dmaven.test.skip=true
```
Download the [Axon Server](https://axoniq.io/product-overview/axon-server) then;
```sh
java -jar axonserver.jar
```

## Test

- Account Replay
```json5
{
"method": "GET",
"headers":{},
"uri": "http://localhost:9999/replay",
"query":{}
}
```

- Deposit Money
```json5
{
  "name": "Deposit Money",
  "request":{
    "method": "POST",
    "headers":{"content-type": "application/json"},
    "uri": "http://localhost:8084/deposit",
    "query":{},
    "body":{"userid": "afe3037c-7c32-44db-98c0-20de7a6fef12", "amount": 100}
  }
```

- Order Create
```json5
{
  "method": "POST",
  "headers":{"content-type": "application/json"},
  "uri": "http://localhost:8081",
  "query":{},
  "body":{"number": 2, "productid": "113b8692-a3ea-40ec-8dea-2c8f9800c3a8", "userid": "afe3037c-7c32-44db-98c0-20de7a6fef12"}
}
```

- Product Create
```json5
{
  "method": "GET",
  "headers":{},
  "uri": "http://localhost:8080",
  "query":{},
  "body":{"price": 3.4, "stock": 33, "name": "Product TEST", "description": "Bla bla bla"}
}
```

- User Create
```json5
{
  "method": "GET",
  "headers":{},
  "uri": "http://localhost:8084",
  "query":{},
  "body":{
    "name": "Test 1"
  }
}
```