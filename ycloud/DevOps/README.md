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

Systemd:\
Add .service file to ``/etc/systemd/system/`` \
Create new file, for example ``usermanager.service``\
Put text below to file and change data in {} to your custom
```
[Unit]
Description=DevOps project with Spring Boot and OpenAPI 3.0
After=syslog.target

[Service]
User={user_name}
ExecStart={path_where_store_jar_file}/devopsproject-1.0.jar
SuccessExitStatus=143

[Install]
WantedBy=multi-user.target
```
Start: 
```
sudo systemctl enable {servicename}.service
sudo systemctl start {servicename}.service
```
Check the status
```
sudo systemctl status {servicename}.service
```

Go to ``localhost:9090/`` and enjoy! 


