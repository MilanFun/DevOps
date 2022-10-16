# DevOps
DevOps project using Spring Boot and OpenAPI 3.0
Clone this repository.
```
git clone https://github.com/MilanFun/DevOps.git
```

To start the project, you need a MySQL Database. Install and configure it.

Create a database - users:
```
CREATE DATABASE users;
```
Switch database to new:
```
USE uesrs;
```
Create a table of user with columns:
```
CREATE TABLE table_name (
    id int,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    email VARCHAR(50),
    age int
);
```
Configure application.properties with JDBC property for connecting to database:
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/users
spring.datasource.username="your_username"
spring.datasource.password="your_password"
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

Compile project:
```
mvn clean install
```
