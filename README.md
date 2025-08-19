# Image Upload and Management Backend

## About The Project

I built this project to create a secure and scalable backend for handling image uploads. The goal was to develop a self-contained service that could be easily integrated with any of my frontend applications needing image storage. This project handles the complexities of file storage and user authentication as well as the users usage analytics displayed on a dashboard. 



### Built With

This section lists the major frameworks and libraries used to bootstrap the project.

* [Java](https://www.java.com/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [Docker](https://www.docker.com/)
* [Spring Security](https://spring.io/projects/spring-security)
* [JWT (JSON Web Token)](https://jwt.io/)



## Getting Started

To get a local copy up and running, follow these simple example steps.

### Prerequisites

Ensure you have the following software installed on your local machine.

* Java Development Kit (JDK) 11 or later
* Apache Maven
* Docker and Docker Compose

### Installation

1.  **Clone the repository**
    ```sh
    git clone [https://github.com/Lqgistics/imgbackend.git](https://github.com/Lqgistics/imgbackend.git)
    ```
2.  **Configure Environment Variables**
    Create a `.env` file in the root directory of the project. This file should contain the necessary configuration for the database connection and JWT secret. Here is an example:
    ```env
    DB_URL=jdbc:postgresql://db:5432/mydatabase
    DB_USERNAME=user
    DB_PASSWORD=password
    JWT_SECRET=your-jwt-secret
    ```
3.  **Build the application**
    Use the Maven wrapper to build the project and create a JAR file.
    ```sh
    ./mvnw clean install
    ```
4.  **Run with Docker Compose**
    This command will build the Docker images and start the application and the database containers.
    ```sh
    docker-compose up --build
    ```



## Usage

Once the application is running, you can interact with the API endpoints. The service will be available at `http://localhost:8080`.

### Authentication

* **Register a new user**
    `POST /api/auth/register`
* **Login to get a JWT**
    `POST /api/auth/login`

### Image Management

* **Upload an image** (Requires authentication)
    `POST /api/images/upload`
* **Get an image by ID**
    `GET /api/images/{id}`

### Dashboard

* **Get application statistics**
    `GET /api/dashboard/stats`

For more detailed examples, please refer to the source code in the `src/main/java/com/aman/zen/` directory.



## Roadmap

- Implement user roles and permissions for more granular access control.
- Add support for different image storage providers (e.g., AWS S3, Google Cloud Storage).



