<h1 align="center">Workshop Spring Boot</h1>

<h3 align="center">This is a product order control project for study purpose</h3>

## Goals
- Create Spring Boot Java project
- Implement domain model
- Structuring logical layers: resource, service, repository
- Configure test database (H2)
- Populate the database
- CRUD - Create, Retrieve, Update, Delete
- Exception Handling

## Domain Model
<div align="center"> 
  
  ![image](https://github.com/vinicius-je/workshop-spring-boot/assets/67986109/96aea3a5-d4b0-475c-8af1-6d2777ce9070)
</div>

## Next Steps
- [x] Validation
- [x] Documentation
- [x] Encrypt password
- [x] Authentication


## Business rule
- In order class will only be changed to PAID if there is payment
- Only admin user can:
  - create new categories
  - create new products
  - change order status

## Tech
- Java 17
- SpringBoot
- JPA
- H2 (test database)
- MySQL
- Bean Validation
- Swagger
- intelliJ IDEA

## How to run the project
- Clone this repository in your machine and run the project

  API works on [localhost:8080](http://localhost:8080)

  Swagger can be visualized in [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
