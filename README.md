# Sports Bet Tracker API

A RESTful backend API for tracking sports bets, built with Java 17, Spring Boot, and PostgreSQL.

Built as a portfolio project to demonstrate Spring Boot, JPA, and REST API development in the sports betting domain.

## Tech Stack

- Java 17
- Spring Boot 4.1
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- JUnit 5 + Mockito

## Getting Started

### Prerequisites
- Java 17+
- PostgreSQL running locally

### Setup

1. Create the database:
```sql
CREATE DATABASE bettracker;
```

2. Update `src/main/resources/application.properties` with your PostgreSQL credentials.

Set your PostgreSQL password as an environment variable:
```bash
export DB_PASSWORD=yourpassword
```

3. Run the app:
```bash
./mvnw spring-boot:run
```

App starts on `http://localhost:8080`

## API Endpoints

### Place a bet
```bash
curl -X POST http://localhost:8080/api/bets \
  -H "Content-Type: application/json" \
  -d '{"sport":"football","team":"Real Madrid","odds":2.5,"stake":20.0}'
```

### Get all bets
```bash
curl http://localhost:8080/api/bets
```

### Filter by sport
```bash
curl http://localhost:8080/api/bets?sport=football
```

### Filter by status
```bash
curl http://localhost:8080/api/bets?status=PENDING
```

### Get a single bet
```bash
curl http://localhost:8080/api/bets/1
```

### Settle a bet (WON / LOST)
```bash
curl -X PUT "http://localhost:8080/api/bets/1/settle?result=WON"
```

### Get stats
```bash
curl http://localhost:8080/api/bets/stats
```

**Example stats response:**
```json
{
  "totalStaked": 35.0,
  "totalProfit": 15.0,
  "winRate": 50.0,
  "totalBets": 2,
  "pendingBets": 0
}
```

## Running Tests
```bash
./mvnw test
```