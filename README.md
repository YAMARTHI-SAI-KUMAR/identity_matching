# Identity Matching - Bitespeed Backend Task

This project implements a solution for identity matching as part of the Bitespeed backend assignment. It identifies and links a customer's multiple contact entries across different orders, using either **email** or **phoneNumber** to consolidate them.

---

## 🚀 Hosted API Endpoint

**POST** `/identify`  
🌐 **URL:** [# Identity Matching - Bitespeed Backend Task

This project implements a solution for identity reconciliation as part of the Bitespeed backend assignment. It identifies and links a customer's multiple contact entries across different orders, using either **email** or **phoneNumber** to consolidate them.

---

## Features

- Identity matching using email and phone number
- Handles primary and secondary contacts logic
- SQL database support
- RESTful endpoints
- Deployed live on Railway

Live Demo
The /identify endpoint is deployed and live at:
https://identity-matching.up.railway.app/identify

You need to check it on postman/swaggers/openapi

Note: The API accepts JSON in the request body.


---
## API Endpoints

### Identify Contact

- **URL:** `/identify`
- **Method:** `POST`
- **Content-Type:** `application/json`
- **Request Body:**
  ```json
{
	"email"?: string,
	"phoneNumber"?: number
}
Note: Question mark represents optional fields

  Response:
Returns the primary contact and all linked secondary contacts with their emails and phone numbers.


	{
		"contact":{
			"primaryContatctId": number,
			"emails": string[], // first element being email of primary contact 
			"phoneNumbers": string[], // first element being phoneNumber of primary contact
			"secondaryContactIds": number[] // Array of all Contact IDs that are "secondary" to the primary contact
		}
	}


 For example

 json request

 {
	"email": "lovely@hillvalley.edu",
	"phoneNumber": "123456"
}

response

{
    "contact": {
        "primaryContactId": 1,
        "emails": [
            "george@hillvalley.edu",
          
        ],
        "phoneNumbers": [
            "1234"
        ],
        "secondaryContactIds": [
            
        ]
    }
}


Running Locally
Clone the repository:

bash
Copy
Edit
git clone https://github.com/YAMARTHI-SAI-KUMAR/identity-matching.git
cd identity-matching

Build the project:

bash
Copy
Edit
./mvnw clean package


Run the application:

bash
Copy
Edit
java -jar target/identity-matching-0.0.1-SNAPSHOT.jar


The API will be available at http://localhost:8080 (or another port as set in application.properties)




Deployment
Platform: Railway.app

Procfile:

pgsql
Copy
Edit
web: java -jar target/identity-matching-0.0.1-SNAPSHOT.jar
Dynamic Port:
In application.properties, ensure:

ini
Copy
Edit
server.port=${PORT:8080}

Deployment
Platform: Railway.app

Procfile:

pgsql
Copy
Edit
web: java -jar target/identity-matching-0.0.1-SNAPSHOT.jar
Dynamic Port:
In application.properties, ensure:

ini
Copy
Edit
server.port=${PORT:8080}



Author
Sai Kumar Yamarthi

LinkedIn

GitHub

License
This project is for assignment/demo purposes.

yaml
Copy
Edit

---

**You can copy-paste this as your `README.md`.**  
Feel free to edit links, author info, and description as needed!

Let me know if you want a more detailed/shorter/technical or recruiter-friendly version!










