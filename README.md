# Java Birds API

## Overview

This project demonstrates a simple RESTful API for managing birds and their sightings. It is built with Spring Boot and utilizes the following features:

- **OpenAPI Specification**: Integrated API documentation using the OpenAPI specification. The API docs are accessible after starting the application at:
  [Swagger UI](http://localhost:8080/swagger-ui/index.html?configUrl=/docs/swagger-config)

- **JPA Specification API**: Utilizes the JPA Specification API for dynamic query construction, allowing for flexible searching of entities based on various criteria.

- **Layered Architecture**: Adheres to the classic Controller-Service-Repository pattern for clear separation of concerns and maintainability.

## Opinionated Observations

### Pagination
- Implemented pagination for all `findAll` query operations to prevent performance issues due to large datasets. This is based on the best practice that we should never retrieve all entries of a large table in a single query.

### HTTP Status Codes
- Opted to use HTTP status `204 No Content` for empty responses to avoid confusion commonly associated with `404 Not Found`. This decision was made based on practical experiences where developers might misinterpret `404` as an indication of an unavailable API rather than a mere absence of the resource.

### Data Deletion
- Employed physical deletion rather than soft deletion to simplify the implementation. This approach was chosen for its straightforwardness in a basic API scenario.

### Non-functional Requirements
- Omitted advanced features such as auditing and logging due to time constraints. While recognized as important for production applications, these were not included as they were not explicitly requested. Typically, functionalities like these would be implemented within a dedicated orchestrator service to comply with the single-responsibility principle.

## Getting Started

To get the application running locally:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/yourusername/java-birds-api.git

2. **Navigate to the project directory:**
   ```bash
   cd java-birds-api

3. **Run the application with Docker Compose:**
   ```bash
   docker-compose up --build

4. **Try out the API:**

   http://localhost:8080/swagger-ui/index.html?configUrl=/docs/swagger-config