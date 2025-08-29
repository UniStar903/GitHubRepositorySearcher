# Backend Assignment - GitHub Repository Searcher

## 📌 Objective
Develop a **Spring Boot application** that allows users to search GitHub repositories via the **GitHub REST API**, store results in a **PostgreSQL database**, and provide APIs to retrieve stored repositories with optional filters.

---
## 📡 API Specifications

### 1. 🔍 Search GitHub Repositories
**Endpoint:**  
`POST /api/github/search`

**Request Body:**
```json
{
  "query": "spring boot",
  "language": "Java",
  "sort": "stars"
}
````

**Response:**

```json
{
  "message": "Repositories fetched and saved successfully",
  "repositories": [
    {
      "id": 123456,
      "name": "spring-boot-example",
      "description": "An example repository for Spring Boot",
      "owner": "user123",
      "language": "Java",
      "stars": 450,
      "forks": 120,
      "lastUpdated": "2024-01-01T12:00:00Z"
    }
  ]
}
```

---

### 2. 📂 Retrieve Stored Results

**Endpoint:**
`GET /api/github/repositories`

**Request Parameters (all optional):**

* `language`: Filter by programming language.
* `minStars`: Minimum stars count.
* `sort`: Sort by `stars`, `forks`, or `updated` (default: `stars`).

**Example Request:**
`GET /api/github/repositories?language=Java&minStars=100&sort=stars`

**Response:**

```json
{
  "repositories": [
    {
      "id": 123456,
      "name": "spring-boot-example",
      "description": "An example repository for Spring Boot",
      "owner": "user123",
      "language": "Java",
      "stars": 450,
      "forks": 120,
      "lastUpdated": "2024-01-01T12:00:00Z"
    }
  ]
}
```

---

## 🛠️ Tech Stack

* **Java 17+**
* **Spring Boot 3+**
* **PostgreSQL**
* **Spring Data JPA**
* **RestTemplate** (for GitHub API calls)

---

## ▶️ Running the Project

### 1. Clone the Repository

```bash
git clone <repo-url>
cd github-repository-searcher
```

### 2. Configure PostgreSQL

Update `application.properties`:

```properties
spring.datasource.password=your_password
```

### 3. Build and Run

```bash
./mvnw spring-boot:run
```

### 4. Test APIs

Use **Postman**:

```bash
# Search GitHub repos
POST http://localhost:8080/api/github/search

# Retrieve stored repos
GET http://localhost:8080/api/github/repositories?language=Java&minStars=100&sort=stars
```

---

## 📖 Notes

* GitHub API rate limits apply: **60 requests/hour (unauthenticated)** or **5000 requests/hour (authenticated with token)**.
* To authenticate, configure your GitHub token in application properties.
