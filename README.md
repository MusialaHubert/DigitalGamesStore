# DigitalGamesStore

This project is a backend API for an online game store's catalogue, built using Java Spring and a PostgreSQL database. The API allows visitors to browse and search the games, while store admins can add, update, and delete games.

## Architecture
The API is built using a monolithic architecture, with all functionality contained in a single Spring Boot application. The application is designed using a layered architecture, with the following layers:

* Controllers: responsible for handling incoming requests and sending responses.
* Services: responsible for implementing the business logic of the application.
* Repositories: responsible for communicating with the database to perform CRUD operations.
* Entities: responsible for defining the data models used in the application.

## Storage
The API uses a PostgreSQL database to store game and user information. The database is designed using a normalized schema to ensure data consistency and to reduce data redundancy.

## Testing
The API is covered with automated tests. The tests are written using JUnit and Mockito, and they ensure that the API functions work correctly.
