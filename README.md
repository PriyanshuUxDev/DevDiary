# 🚀 DevDiary — AI-Powered Developer Blog API

**DevDiary** is a production-style REST API for a modern blogging platform built with **Java 21 and Spring Boot**. It combines traditional backend engineering with **Generative AI** to automatically summarize and tag blog posts and provides a lightweight **Retrieval-Augmented Generation (RAG)** chatbot that answers questions using actual blog content with source citations.

🔗 **Live API:** https://devdiary-production-6c12.up.railway.app

---

## ✨ Highlights

* 🔐 **Secure Authentication** — User registration with Spring Security and BCrypt password hashing
* 📝 **Blog Management** — Complete CRUD operations for blog posts
* 🗂️ **Category Management** — Relational category and post associations
* 🤖 **AI Summarization** — Generates concise 1–2 sentence summaries using Google Gemini
* 🏷️ **AI Auto-Tagging** — Generates structured JSON tags from post content
* 💬 **AI RAG Chatbot** — Answers natural-language questions using relevant blog content as context
* 📚 **Source Citations** — AI responses include the blog posts used as sources
* 📊 **Analytics** — Aggregated statistics using Spring Data JPA queries
* 🛡️ **Centralized Error Handling** — Consistent API error responses using `@RestControllerAdvice`
* ✅ **Validation** — Field-level request validation with meaningful error messages
* ☁️ **Cloud Deployment** — Deployed with Railway and connected to a MySQL database

---

# 🧠 AI & RAG Architecture

One of the core features of DevDiary is its AI-powered question-answering system.

Instead of sending a user's question directly to Gemini, the API first searches the existing blog posts for relevant content.

```text
User Question
      │
      ▼
┌─────────────────────┐
│  /api/gemini/ask    │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Keyword Retrieval   │
│ from Blog Posts     │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│ Relevant Post       │
│ Content as Context  │
└──────────┬──────────┘
           │
           ▼
┌─────────────────────┐
│   Google Gemini     │
│   gemini-2.5-flash  │
└──────────┬──────────┘
           │
           ▼
┌──────────────────────────┐
│ Answer + Source Articles │
└──────────────────────────┘
```

### Example

**Question:**

```text
What posts mention Spider-Man?
```

**Response:**

```json
{
  "question": "what posts mention Spider-Man?",
  "answer": "Spider-Man is discussed in the post about Spider-Man: Brand New Day...",
  "sources": [
    "Spider-Man: Brand New Day"
  ]
}
```

> **Current implementation:** retrieval uses keyword matching.
> **Planned improvement:** semantic search using embeddings and a vector database.

---

# 🏗️ Architecture

DevDiary follows a clean layered backend architecture:

```text
                    Client
                      │
                      ▼
                REST Controllers
                      │
                      ▼
                   Services
                      │
            ┌─────────┴─────────┐
            ▼                   ▼
       Repositories        Gemini Service
            │                   │
            ▼                   ▼
         MySQL              Gemini API
```

### Backend Layers

| Layer                 | Responsibility                          |
| --------------------- | --------------------------------------- |
| **Controller**        | Handles HTTP requests and responses     |
| **Service**           | Contains business logic                 |
| **Repository**        | Database access using Spring Data JPA   |
| **Entity**            | Database-mapped domain models           |
| **DTO**               | Controls API request/response structure |
| **Exception Handler** | Centralized API error handling          |
| **Security**          | Authentication and password protection  |
| **AI Service**        | Gemini integration and prompt handling  |

DTOs are used to prevent internal database fields from being exposed directly through the API.

For example, a user's password is **never returned in API responses**.

---

# 🛠️ Tech Stack

### Backend

* **Java 21**
* **Spring Boot**
* **Spring MVC**
* **Spring Data JPA**
* **Spring Security**
* **Hibernate ORM**

### Database

* **MySQL**

### Artificial Intelligence

* **Google Gemini API**
* **gemini-2.5-flash**
* AI summarization
* AI tagging
* Retrieval-Augmented Generation (RAG-lite)

### Development & Deployment

* **Maven**
* **Lombok**
* **Postman**
* **Git / GitHub**
* **Railway**

---

# 📌 Core Features

## 🔐 Authentication

Users can register through the REST API.

### Endpoint

```http
POST /api/auth/register
```

### Request

```json
{
  "userName": "ansh",
  "email": "ansh@gmail.com",
  "password": "test1234"
}
```

Passwords are hashed using **BCrypt** before being stored in the database.

---

# 🗂️ Categories

### Create Category

```http
POST /api/category
```

```json
{
  "name": "Tech",
  "description": "Technology related posts"
}
```

### Get Categories

```http
GET /api/category
```

---

# 📝 Blog Posts

DevDiary provides complete blog post management.

| Method   | Endpoint                   | Description         |
| -------- | -------------------------- | ------------------- |
| `POST`   | `/api/post`                | Create a post       |
| `GET`    | `/api/post`                | Get all posts       |
| `GET`    | `/api/post/{id}`           | Get post by ID      |
| `DELETE` | `/api/post/{id}`           | Delete a post       |
| `POST`   | `/api/post/{id}/summarize` | Generate AI summary |
| `POST`   | `/api/post/{id}/tags`      | Generate AI tags    |

### Create Post

```json
{
  "title": "Spider-Man: Brand New Day",
  "content": "Spider-Man: Brand New Day follows Peter Parker...",
  "userId": 2,
  "categoryId": 1
}
```

### Example Response

```json
{
  "id": 1,
  "title": "Spider-Man: Brand New Day",
  "content": "...",
  "summary": "AI-generated summary...",
  "categoryName": "Movie",
  "authorUserName": "priya",
  "tags": [
    "Spider-Man",
    "Superhero",
    "Identity"
  ]
}
```

---

# 🤖 AI Features

## AI Summarization

Generate a concise summary for an existing post:

```http
POST /api/post/{id}/summarize
```

Gemini analyzes the post content and generates a **1–2 sentence summary**.

---

## 🏷️ AI Auto-Tagging

Generate tags automatically:

```http
POST /api/post/{id}/tags
```

Gemini analyzes the content and returns structured tags.

Example:

```json
[
  "Spider-Man",
  "Superhero",
  "Marvel",
  "Identity"
]
```

---

# 💬 AI Chatbot

DevDiary includes an AI-powered question-answering endpoint.

```http
POST /api/gemini/ask
```

### Request

```json
{
  "question": "what posts mention Spider-Man?"
}
```

### Response

```json
{
  "question": "what posts mention Spider-Man?",
  "answer": "...",
  "sources": [
    "Spider-Man: Brand New Day"
  ]
}
```

The system retrieves relevant blog posts first and passes their content to Gemini as context.

This makes the chatbot **grounded in the application's own data** rather than relying only on the model's general knowledge.

---

# 📊 Analytics

DevDiary provides aggregated blogging statistics through Spring Data JPA queries.

```http
GET /api/analytics
```

The endpoint provides:

* 📂 Posts per category
* 👤 Top authors
* 📅 Posts per day

These statistics are calculated using database-level aggregate queries rather than loading all records into application memory.

---

# 🛡️ Error Handling & Validation

The API uses centralized exception handling through:

```java
@RestControllerAdvice
```

This provides consistent error responses across the application.

The API also supports field-level validation for invalid requests.

Example:

```text
Invalid email
Missing required field
Invalid post ID
Invalid category
```

---

# ☁️ Deployment

DevDiary is deployed on **Railway** with:

* Spring Boot application
* Provisioned MySQL database
* Environment-based configuration
* Gemini API integration

🔗 **Live API**

https://devdiary-production-6c12.up.railway.app

Sensitive credentials are **not committed to source control**.

The following values are configured as environment variables:

```text
DB_URL
DB_PASSWORD
GEMINI_API_KEY
```

---

# 💻 Running Locally

## Prerequisites

Make sure you have:

* Java 21
* Maven
* MySQL
* Gemini API key

---

## 1. Clone the Repository

```bash
git clone https://github.com/PriyanshuUxDev/DevDiary.git

cd DevDiary
```

---

## 2. Configure Environment Variables

Set the following environment variables:

```text
DB_URL=jdbc:mysql://localhost:3306/devdiary
DB_PASSWORD=<your-mysql-password>
GEMINI_API_KEY=<your-gemini-api-key>
```

> Never hardcode API keys, database passwords, or other secrets into `application.properties` or source code.

---

## 3. Run the Application

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The API will be available at:

```text
http://localhost:8080
```

---

# 📮 Postman Collection

A complete Postman collection covering the API endpoints is included in the repository.

```text
DevDiary.postman_collection.json
```

You can import this collection into Postman to test the API locally or against the deployed instance.

---

# 🔌 API Overview

| Feature               | Endpoint                   | Method   |
| --------------------- | -------------------------- | -------- |
| Register              | `/api/auth/register`       | `POST`   |
| Create Category       | `/api/category`            | `POST`   |
| Get Categories        | `/api/category`            | `GET`    |
| Create Post           | `/api/post`                | `POST`   |
| Get Posts             | `/api/post`                | `GET`    |
| Get Post              | `/api/post/{id}`           | `GET`    |
| Delete Post           | `/api/post/{id}`           | `DELETE` |
| Generate Summary      | `/api/post/{id}/summarize` | `POST`   |
| Generate Tags         | `/api/post/{id}/tags`      | `POST`   |
| AI Question Answering | `/api/gemini/ask`          | `POST`   |
| Analytics             | `/api/analytics`           | `GET`    |

---

# ⚠️ Known Limitations

### 1. Keyword-Based Retrieval

The chatbot currently retrieves posts using keyword matching.

This works for straightforward queries but is less effective for semantic questions.

**Planned improvement:**

```text
Keyword Search
      ↓
Text Embeddings
      ↓
Vector Database
      ↓
Semantic Retrieval
      ↓
Gemini
```

Potential technologies include:

* pgvector
* Pinecone
* Qdrant
* Chroma

---

### 2. Gemini Rate Limits

The Gemini free tier has request limits, which can affect repeated AI requests during heavy testing.

---

### 3. Category Authorization

Category creation is currently not restricted to administrators.

A future version can introduce role-based authorization:

```text
USER
 └── Read/Create Posts

ADMIN
 ├── Manage Categories
 ├── Manage Users
 └── Manage Posts
```

---

# 🔮 Future Improvements

The project can be extended with:

* 🔐 JWT-based authentication
* 👥 Role-based authorization
* 🧠 Embedding-based semantic search
* 🗄️ Vector database integration
* 💾 Redis caching
* 🔍 Advanced post search and filtering
* 📄 Pagination and sorting
* 🧪 Automated unit & integration testing
* 🐳 Docker containerization
* ⚙️ CI/CD with GitHub Actions
* 📈 Production monitoring and logging
* 🖥️ Frontend dashboard
* 📚 Swagger / OpenAPI documentation

---

# 📚 What This Project Demonstrates

DevDiary was built as a hands-on backend and AI engineering project covering:

* REST API development
* Spring Boot architecture
* Spring Data JPA
* Hibernate ORM
* Entity relationships
* DTO-based API design
* Spring Security
* BCrypt password hashing
* Database aggregation
* Exception handling
* Request validation
* External LLM API integration
* Prompt engineering
* AI-generated structured output
* Retrieval-Augmented Generation concepts
* Cloud deployment
* Environment-based configuration
* API testing with Postman

---

# 👨‍💻 Author

**Priyanshu Kardam**

B.Tech Computer Science Engineering

Interested in **Backend Engineering, AI Engineering, Generative AI, and intelligent backend systems.**

🔗 **GitHub:**
https://github.com/PriyanshuUxDev

---

## ⭐ If You Found This Project Interesting

Feel free to explore the repository, test the deployed API, or use the architecture as a starting point for building AI-powered backend applications.

**Built with Java ☕ + Spring Boot 🌱 + MySQL 🗄️ + Gemini 🤖**
