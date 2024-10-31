# Repositório da Aplicação Bibliográfica

Este projeto é uma aplicação de gerenciamento de autores, artigos e revistas científicas, construída utilizando Spring Boot, JPA e MySQL. Este README fornecerá instruções sobre como configurar a aplicação, o banco de dados e como usar o Postman para realizar requisições.

## Configuração do Banco de Dados

1.  **Criar Tabelas**: As tabelas serão criadas automaticamente quando você iniciar a aplicação. Certifique-se de que o `spring.jpa.hibernate.ddl-auto` esteja configurado corretamente no arquivo `application.properties`.

## Configuração do `application.properties`

Localize o arquivo `src/main/resources/application.properties` e configure as seguintes propriedades:

properties

`spring.application.name=refe-bibliograficas`

`data sourcespring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost/refe-bibliograficas?createDatabaseIfNotExist=true&useSSL=false
spring.datasource.username=seu_user
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.show_sql=true
spring.jpa.properties.hibernate.format_sql=true`

## Para executar a aplicacao utilize o Postman.

A aplicação será iniciada e estará disponível em http://localhost:8080.

Após a aplicação estar em execução, você pode usar o Postman para fazer requisições à API: 
POST, GET, PUT e DELETE.



