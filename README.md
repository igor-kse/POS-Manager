[![Codacy Badge](https://app.codacy.com/project/badge/Grade/adb31c32686e4cccb8d9526db5218f7b)](https://www.codacy.com/gh/Igor-K39/POS-Manager/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Igor-K39/POS-Manager&amp;utm_campaign=Badge_Grade)

## POS-manager - the bank device support software (updating)
Spring Boot application for organizing bank devices support based on user/admin roles. 
The application is designed to store information about bank devices and its support 
by employees. It's assumed that when a support engineer accepts a request from any contractor 
(the information about contractors is stored as well), he creates a request in the software 
with the information about what supposedly happened, including request type and status, 
and important type. The engineer who is assigned should solve the issue and update 
the request.

### Heroku link (swagger ui): https://pos-manager-project.herokuapp.com/swagger-ui/

## Tech stack
Spring boot 2.5, Spring Data JPA (Hibernate) REST API (Jackson), Maven, PostgreSQL, Model Mapper, Spring Security, 
JSON Web Tokens, Lombok, Swagger API

## Domain model
![domain_model.png](https://raw.githubusercontent.com/Igor-K39/POS-Manager/main/domain_model.PNG)

...to be updated