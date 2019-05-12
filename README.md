# Sondeo
A pet project to learn Micronaut.

[![Build Status](https://travis-ci.org/paucls/sondeo.svg?branch=master)](https://travis-ci.org/paucls/sondeo)

## Build and run docker image
```
docker build -t paucls/sondeo .
docker run -p 127.0.0.1:8080:8080/tcp paucls/sondeo
```

## TODO
- Write integration test for repository implementation.

## References
- Micronaut documentation
https://docs.micronaut.io/latest/guide/index.html
- Testing with JUnit 5
https://micronaut-projects.github.io/micronaut-test/latest/guide/index.html#junit5
- Access a database with JPA and Hibernate
https://guides.micronaut.io/micronaut-data-access-jpa-hibernate/guide/index.html
- Testing RESTful Services in Kotlin with Rest Assured, Ryan Harrison
https://ryanharrison.co.uk/2019/02/10/testing-restful-services-kotlin-with-rest-assured.html
