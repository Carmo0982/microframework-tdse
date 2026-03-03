# Microframework TDSE

A lightweight Java web framework built from scratch that enables developers to build REST services and serve static files using a simple, expressive API based on lambda functions.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You need the following tools installed:

* **Java 21**
```
https://www.oracle.com/java/technologies/downloads/#java21
```

* **Maven 3.x**
```
https://maven.apache.org/download.cgi
```

* **Git**
```
https://git-scm.com/downloads
```

### Installing

1. Clone the repository

```
git clone https://github.com/your-username/microframework-tdse.git
```

2. Navigate into the project folder

```
cd microframework-tdse
```

3. Build the project with Maven

```
mvn clean package
```

4. Run the example application (`MathServices`) from your IDE by executing the `main` method in:

```
com.tdse.lab05.appexamples.MathServices
```

Or from the terminal:

```
mvn exec:java -Dexec.mainClass="com.tdse.lab05.appexamples.MathServices"
```

The server will start on port **35000**. You can now open your browser and visit:

```
http://localhost:35000/index.html
http://localhost:35000/pi
http://localhost:35000/hello?name=Pedro
```

## Running the tests

### Break down into end to end tests

These tests verify that the REST endpoints and static file serving respond correctly.

Open a browser or use `curl` after starting `MathServices`:

```
curl http://localhost:35000/pi
# Expected: 3.141592653589793

curl http://localhost:35000/hello?name=Pedro
# Expected: Hello Pedro

curl http://localhost:35000/index.html
# Expected: HTML page content
```

### And coding style tests

The project uses JUnit 5 (via Spring Boot Test). Run the test suite with:

```
mvn test
```

## Deployment

To run this on any machine:

1. Build the JAR:
```
mvn clean package
```

2. Run the generated JAR:
```
java -cp target/lab05-0.0.1-SNAPSHOT.jar com.tdse.lab05.appexamples.MathServices
```

Make sure port `35000` is open on the target machine.

## Built With

* [Java](https://www.oracle.com/java/) - Core language
* [Maven](https://maven.apache.org/) - Dependency Management and build tool
* [JUnit 5](https://junit.org/junit5/) - Testing framework
* [Spring Boot](https://spring.io/projects/spring-boot) - Project scaffolding

## Contributing

This project was developed as an academic lab assignment. 

## Versioning

Current version: **0.0.1-SNAPSHOT**

This project uses [SemVer](http://semver.org/) for versioning.

## Authors

* **Santiago Carmona Pineda** 


## License

This project is licensed under the MIT License.

## Acknowledgments

* Microframework — TDSE Course, Escuela Colombiana de Ingeniería Julio Garavito
* Inspiration from microframeworks like Spark Java and Express.js
* HTTP/1.1 specification (RFC 7230)
