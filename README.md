# Identity Matching 

This project implements an identity reconciliation service for the Bitespeed Backend Task.  
It identifies and links multiple customer contact records using email and/or phoneNumber, creating primary–secondary contact hierarchies to unify customer identities across multiple orders.

---

## Live API Deployment (Render)

Base URL:  
https://identity-matching.onrender.com

### Primary API Endpoint
```
POST /identify
```

Use Postman, Thunder Client, or cURL to test the API.

---

## Important Note About Opening in Browser

If you open this link directly in your browser:  
```
https://identity-matching.onrender.com
```

You will see:

```
404 Not Found
```

This is **expected** and **normal**.

Why?

- Browsers send a **GET** request.
- This project does **NOT** have a `GET /` endpoint.
- Only `POST /identify` exists.

Therefore, the backend will always return `404 Not Found` when accessed directly in a browser.  
To test the service correctly, you must send a **POST request** to:

```
https://identity-matching.onrender.com/identify
```

with JSON body.

---

## Features

- Identity matching using email and/or phoneNumber  
- Primary and secondary contact consolidation  
- SQL database support  
- RESTful API architecture  
- Fully deployable on Render  
- Dockerized backend  
- Clean and reliable response structure  

---

## API Documentation

### Endpoint: POST /identify

### Request Body
```json
{
  "email": "string (optional)",
  "phoneNumber": "string (optional)"
}
```

You must provide at least one field: email or phoneNumber.

### Sample Response
```json
{
  "contact": {
    "primaryContactId": 1,
    "emails": [
      "abc@example.com",
      "another@mail.com"
    ],
    "phoneNumbers": [
      "1234567890"
    ],
    "secondaryContactIds": [4, 8]
  }
}
```

### Example Request
```json
{
  "email": "lovely@hillvalley.edu",
  "phoneNumber": "123456"
}
```

---

## Running Locally

### 1. Clone the repository
```bash
git clone https://github.com/YAMARTHI-SAI-KUMAR/identity-matching.git
cd identity-matching
```

### 2. Build the application
Using Maven wrapper:
```bash
./mvnw clean package -DskipTests
```

Windows:
```bash
mvnw.cmd clean package -DskipTests
```

### 3. Run the application
```bash
java -jar target/identity-matching-0.0.1-SNAPSHOT.jar
```

### Local URL
```
http://localhost:8080
```

---

## Docker Support (Render Deployment)

This project includes a multi-stage Dockerfile for deployment.

### Build Docker image
```bash
docker build -t identity-matching .
```

### Run Docker container
```bash
docker run -p 8080:8080 identity-matching
```

---

## Deployment (Render)

Render uses the Dockerfile directly to build and run the service.

Ensure the following is set in `application.properties`:

```
server.port=${PORT:8080}
```

Render assigns the `$PORT` during deployment.

---

## Tech Stack

- Java 17  
- Spring Boot  
- Spring Data JPA  
- SQL / H2  
- Maven  
- Docker  
- Render  
- REST API  

---

## Author

**Sai Kumar Yamarthi**  
GitHub: https://github.com/YAMARTHI-SAI-KUMAR  
LinkedIn: https://www.linkedin.com/in/YAMARTHI-SAI-KUMAR

---

## License

This project is created for assignment and demonstration purposes.
