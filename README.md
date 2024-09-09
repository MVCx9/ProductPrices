# ProductPrices

## Description
ProductPrices is a Spring Boot application for managing product prices based on brand, product, and application date. It provides RESTful APIs to query and manage product prices.

## Table of Contents
- [Description](#description)
- [Features](#features)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Running Tests](#running-tests)
- [Swagger UI Documentation](#swagger-ui-documentation)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

## Features
- Manage product prices
- Query prices based on brand, product, and application date
- RESTful API endpoints
- Swagger UI for API documentation

## Installation
1. Clone the repository:

```bash
git clone https://github.com/MVCx9/ProductPrices.git
cd ProductPrices
```

2. Ensure you have Java 21 and Maven installed.

3. Build the project:
```bash
mvn clean install
```

## Running the Application
1. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

2. The application will start on http://localhost:8080.

## Running Tests
### To run all tests:
```bash
mvn test
```

### Run Tests with JaCoCo: 
Execute the following Maven command to run your tests and generate the JaCoCo report.  
```bash
mvn clean test
```
View JaCoCo Report: After running the tests, the JaCoCo report will be generated in the ***target/site/jacoco*** directory. 

Open the **index.html** file in a web browser to view the detailed coverage report.

open ***target/site/jacoco/index.html*** in a web browser.


## Swagger UI Documentation
To use Swagger UI and explore API documentation, follow these steps:

1. **Start the Application**:
   Ensure your Spring Boot application is running. You can start it using:
   ```bash
   mvn spring-boot:run
   ```

2. **Open Swagger UI**:
   Open your web browser and navigate to:

   http://localhost:8080/swagger-ui.html
   

3. **Explore API Documentation**:
    - You will see a list of all available endpoints, grouped by their controllers.
    - Each endpoint will have a description, parameters, and response details.

4. **Try Endpoints**:
    - Click on an endpoint to expand its details.
    - You will see a "Try it out" button. Click it to enable input fields for the endpoint parameters.
    - Fill in the required parameters.
    - Click the "Execute" button to send a request to the endpoint.

5. **View Responses**:
    - After executing the request, you will see the response details, including the status code, response body, and headers.

This allows you to interactively test your API endpoints directly from the Swagger UI interface.

## Technologies Used
- Java 21
- Spring Boot
- Maven
- H2 Database
- Swagger UI

## Contributing
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add new feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a Pull Request.

## License
This project is licensed under the MIT License. 