<br />
<p align="center">

<h3 align="center">GRPC POC</h3>
</p>

## About The Project

This project implements grpc, protobuff concepts communication between the microservices.

### Built With

* [Maven](https://maven.apache.org/)
* Java 11

## Getting Started

When you clone this project firstly run;
```sh
  cd corona-microservices/importer ; mvn clean install -DskipTests
```
then run all services.

## Test
```
 -> GET localhost:8080/cases
 -> GET localhost:8080/corona
 -> GET localhost:8080/country
```
## Roadmap

  1. Add __calculation-service__ for case counts based by countries.
