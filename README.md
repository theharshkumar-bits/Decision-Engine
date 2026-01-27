📌 Decision Engine – Rule-Based Scoring System (Spring Boot)

A rule-driven decision engine built using Spring Boot, JPA, PostgreSQL, and Spring Expression Language (SpEL).
This system dynamically evaluates business rules stored in the database and calculates a final decision score at runtime.

🚀 Features Implemented
✅ Phase 1 – Core Backend

CRUD APIs for managing rules

PostgreSQL integration using Spring Data JPA

REST APIs with proper HTTP status handling

✅ Phase 2 – Rule Evaluation Engine

Dynamic rule evaluation using Spring Expression Language (SpEL)

Runtime scoring based on candidate attributes

Clean separation of Controller, Service, Engine layers

✅ Phase 2.1 – Performance Optimization

In-memory rule caching

Eliminated repeated DB hits during evaluation

Cache refresh support after rule updates

🧠 How It Works

Business rules are stored in the database

Each rule contains a SpEL expression (example):

#candidate.experience >= 5 ? 20 : 5


Candidate data is sent via API

The engine:

Parses expressions

Evaluates them at runtime

Generates a detailed decision report

🛠 Tech Stack

Java 17

Spring Boot

Spring Data JPA

Spring Expression Language (SpEL)

PostgreSQL

Maven

📂 Project Structure
src
└── main
├── java
│   └── com.example.decision_engine
│       ├── controller
│       ├── service
│       ├── engine
│       ├── model
│       ├── repository
│       └── cache
└── resources
└── application.properties

▶️ How to Run

Clone the repository

Configure PostgreSQL in application.properties

Run:

./mvnw spring-boot:run


Test APIs using Postman

📌 Upcoming Enhancements

Async rule evaluation

Rule priority & conflict resolution

Audit logging

Rule versioning

Redis-based caching

📬 Author

Harsh Kumar
Backend Developer | Java | Spring Boot