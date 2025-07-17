# Identity Matching - Bitespeed Backend Task

This project implements an identity reconciliation solution as part of the Bitespeed backend assignment. It effectively identifies and links multiple contact entries of a customer across various orders using **email** or **phoneNumber**, consolidating contacts into primary and secondary relationships.

---

## 🚀 Live API Endpoint

**POST** `/identify`
🌐 **Live URL:** [https://identity-matching.up.railway.app/identify](https://identity-matching.up.railway.app/identify)

**Note:** Test the API using tools like Postman or Swagger/OpenAPI.

---

## ✨ Features

* Identity matching based on email and/or phone number
* Primary and secondary contacts reconciliation
* SQL database support
* RESTful API design
* Deployed on Railway

---

## 📌 API Documentation

### Identify Contact

* **URL:** `/identify`
* **Method:** `POST`
* **Content-Type:** `application/json`

**Request Body:**

```json
{
  "email"?: "string",
  "phoneNumber"?: "string"
}
```

**Note:** Fields marked with `?` are optional.

**Response:**

Returns details of the primary contact and associated secondary contacts, including their emails, phone numbers, and IDs.

```json
{
  "contact": {
    "primaryContactId": 1,
    "emails": ["george@hillvalley.edu"],
    "phoneNumbers": ["123456"],
    "secondaryContactIds": []
  }
}
```

**Example Request:**

```json
{
  "email": "lovely@hillvalley.edu",
  "phoneNumber": "123456"
}
```

---

## 🛠️ Running Locally

**Clone the repository:**

```bash
git clone https://github.com/YAMARTHI-SAI-KUMAR/identity-matching.git
cd identity-matching
```

**Build the project:**

```bash
./mvnw clean package
```

**Run the application:**

```bash
java -jar target/identity-matching-0.0.1-SNAPSHOT.jar
```

The API will run locally at: [http://localhost:8080](http://localhost:8080)

---

## ☁️ Deployment

**Platform:** Railway.app

**Procfile:**

```
web: java -jar target/identity-matching-0.0.1-SNAPSHOT.jar
```

**Dynamic Port Configuration:**

Ensure the following is set in `application.properties`:

```ini
server.port=${PORT:8080}
```

---

## 👤 Author

**Sai Kumar Yamarthi**

* [LinkedIn](https://www.linkedin.com/in/YAMARTHI-SAI-KUMAR)
* [GitHub](https://github.com/YAMARTHI-SAI-KUMAR)

---

## 📃 License

This project is created for assignment and demonstration purposes.
