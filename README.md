# API Control

This is a simple Spring Boot application that demonstrates how to implement various HTTP methods (GET, POST, PUT, DELETE) in a RESTful API.

## Spring Boot
Spring Boot is an open-source Java-based framework that is used for creating stand-alone, production-grade Spring-based applications. It is built on top of the popular Spring framework and provides a streamlined way to develop and deploy Spring applications with minimal configuration.

One of the key features of Spring Boot is its opinionated approach to configuration. It provides sensible defaults and auto-configuration options, which greatly simplify the development process and reduce the need for manual configuration.

Spring Boot also provides a powerful set of tools for building and testing applications, including embedded servers, a command-line interface, and an extensive set of libraries and plugins. These tools make it easy to develop, test, and deploy Spring applications quickly and efficiently.

Overall, Spring Boot is an excellent choice for developers who want to build modern, scalable, and maintainable applications using the Spring framework.

## Prerequisites

Before running the application, you should have the following installed on your machine:

* Java 11 or higher
* Maven 3.6 or higher

## Running the Application

To run the application, navigate to the root directory and execute the following command:

```bash
mvn spring-boot:run

```

This will start the application on port 8080.

## Endpoints

The application provides the following endpoints:

### GET /control

This endpoint returns a simple greeting message.

*Example*:

**GET /control**

```vbnet

```

### POST /control

This endpoint accepts a JSON payload and stores the message in memory.

*Example*:

**POST /control**

```json
{
    "parkingSpotNumber": "10201",
    "licensePlateCar": "RREX89",
    "brandCar": "Mercedes",
    "modelCar": "CLA200",
    "colorCar": "Branco",
    "responsibleName": "Igor Cardoso",
    "apartment": "5022",
    "block": "A"
}
```

### GET /control/{id}

This endpoint retrieves a message by ID.

*Example*:

**GET /control/{id}**

```json
```

### PUT /control/{id}

This endpoint updates a message by ID.

*Example*:

**PUT /control/{id}**

```json
```

### DELETE /control/{id}

This endpoint deletes a message by ID.

*Example*:

**GET /control/{id}**

```json

```

## Conclusion

This simple Spring Boot application provides a basic example of how to implement HTTP methods in a RESTful API. It can be used as a starting point for more complex applications.

## Author

* **Igor Cardoso** - [igorxcardoso](https://github.com/igorxcardoso)

## License

This project is licensed under an MIT license - see file [LICENSE.md](LICENSE.md) for more details.
