# tool-rental

## What is this?
A Tool Rental Application REST API Microservice in Java with Postgres database

## Prerequisites
- Docker & Docker Compose
- Gradle
- Java 17

## Build and Test
The tests rely on a database so start the db container using this command:
```shell
docker compose up
```
The application can be packaged using:
```shell script
./gradlew clean build
```

This runs the Rest endpoint tests (that use RestAssured and Junit). The Test Reports are available under
`./build/reports/index.html`

And creates an executable jar located at
`./build/quarkus-app/quarkus-run.jar`

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.


