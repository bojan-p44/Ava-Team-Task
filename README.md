# Ava-Team-Task
## Built With
 - [Maven](https://maven.apache.org/) - Dependency Management
 - [Lombok](https://projectlombok.org/) - for Intellij IDEA you have to install Lombok plugin !
 - [JDK-8](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) - Java™ Platform, Standard Edition Development Kit
 - [MySQL](https://www.mysql.com/) - Open-Source Relational Database Management System
 - [git](https://git-scm.com/) - Version control system
 - [Spring Boot](https://spring.io/projects/spring-boot) - Framework to ease the bootstrapping and development of new Spring Applications
 - [Swagger2] - API documentation
 
## External Tools Used
 - [Postman](https://www.postman.com/) - API Development Environment
 
## Running the Application locally
Steps to run application in local environment
 - Download the zip or clone the Git repository.
 - Unzip the zip file (if you downloaded one)
 - Change your MySql db username and password properties in application.properties file, port as well if you need
 - Execute sql/init_db.sql script
 - Be sure that you have Lombok plugin installed in your IDE
 - Open Intellj -> File  -> New -> Project From Existing sources -> Select this project from disk -> Click OK -> Import project from external model -> Chose Maven -> Click Finish
 - Choose the AvaApplication class (search for @SpringBootApplication)
 - Click run next to the class signature
Alternatively you can use the Spring Boot Maven plugin like so:
 `mvn spring-boot:run`


