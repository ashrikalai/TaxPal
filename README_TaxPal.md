# TaxPal - Income Tax Estimator and Investment Planner 🧾

TaxPal is a Spring Boot-based RESTful API for estimating income tax and managing user investment records. It supports operations like tax calculation, saving user financial data, and retrieving tax by user email.

---

## 🛠️ Tech Stack

- **Backend**: Java + Spring Boot
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Others**: JPA, REST APIs, CORS

---

## 🔗 API Endpoints

### 1. Estimate Tax (without saving)
```
GET /api/tax/estimate?income=600000&investment=150000
```
✅ Returns the estimated tax based on input.

---

### 2. Save User Income and Investment
```
POST /api/tax/save
```
#### Request Body (JSON):
```json
{
  "email": "john@example.com",
  "income": 600000,
  "investment": 150000,
  "otherSources": 0
}
```
✅ Stores user's income, investment, and other sources of income.

---

### 3. Get All User Records
```
GET /api/tax/all
```
✅ Returns a list of all user income entries.

---

### 4. Get Tax for Specific User
```
GET /api/tax/user-tax?email=john@example.com
```
✅ Returns the estimated tax for the saved user.

---

### 5. Save via Alternative Endpoint
```
POST /api/tax/user-income
```
✅ Also stores user income data (same request format as `/save`).

---

### 6. Get Specific User’s Income Record
```
GET /api/tax/user-income/{email}
```
✅ Returns the full income record of a specific user.

---

## 💾 Database Schema (PostgreSQL)

Table: `user_income`

| Column         | Type    | Constraints     |
|----------------|---------|-----------------|
| id             | SERIAL  | PRIMARY KEY     |
| email          | TEXT    | NOT NULL        |
| income         | DOUBLE  | NOT NULL        |
| investment     | DOUBLE  | NOT NULL        |
| other_sources  | DOUBLE  | NOT NULL        |

---

## 🚀 Getting Started

### 1. Clone the repository
```bash
git clone https://github.com/your-username/taxpal.git
cd taxpal
```

### 2. Configure PostgreSQL
Update `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taxpaldb
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the project
```bash
./mvnw spring-boot:run
```

---

## 📫 Author

- 👩‍💻 Ashritha G

---

## 📄 License

This project is licensed under the MIT License.