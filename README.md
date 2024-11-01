# Assignment ToolBox3 Project
This project is developed as part of the Toolbox3 assignment and demonstrates proficiency in setting up and automating a Java Spring Boot application using Gradle. The project includes REST APIs for managing sensors, windows, and rooms within a building environment.

## Features
- API Management: Exposes RESTful APIs for sensors, windows, and rooms.
- Data Persistence: Utilizes JPA for database operations and entity management.
- Swagger Documentation: Easily accessible API documentation through Swagger UI.

## Project Setup
### Clone the Repository
To get started, clone the project to your local machine:
```
  git clone https://github.com/abed-mhd/Assignment_ToolBox3.git
  cd Assignment_ToolBox3
```
### Build the Project
The project can be fully built using a single Gradle command, which handles all dependencies, compilation, and JAR packaging.
```
   ./gradlew build
```
### Testing
Run unit tests to verify functionality:
```
   ./gradlew test
```

### Running the Application
To run the project, use either of the following commands:
1. Run using the bootRun task:
```
   ./gradlew bootRun
```
2. Run the JAR directly: First, package the application into a JAR file:
```
./gradlew assemble
```
Then run the JAR:
```
java -jar build/libs/Assignment-0.0.1-SNAPSHOT.jar
```
## Accessing the API
Once the application is running, navigate to the Swagger UI to test the APIs:
[**http://localhost:8080/swagger-ui/index.html**](http://localhost:8080/swagger-ui/index.html)

Here, you can explore and test the endpoints for managing sensors, windows, and rooms.
