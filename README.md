# Internal Transfers System

Simple REST API for transferring money between accounts using Spring Boot and PostgreSQL.

## Setup

You'll need:
- Java 17+
- PostgreSQL running locally
- Maven (or use `./mvnw`)

### Database Setup

Create the database in PostgreSQL:
```sql
CREATE DATABASE internal_transfers;
```

Then update `application.properties` with your postgres credentials:
```properties
spring.datasource.username=postgres
spring.datasource.password=your_password
```

If you are using a non-superuser PostgreSQL role, ensure the user has
sufficient privileges on the database:

```
GRANT ALL PRIVILEGES ON DATABASE internal_transfers TO <db_user>;
```

### Running
```bash
mvn clean install
mvn spring-boot:run
```

App runs on `http://localhost:8080`

## API Usage

**Create Account**
```bash
POST /accounts
{
  "account_id": 123,
  "initial_balance": "100.00"
}
```

**Get Balance**
```bash
GET /accounts/123
```

**Transfer Money**
```bash
POST /transactions
{
  "source_account_id": 123,
  "destination_account_id": 456,
  "amount": "50.00"
}
```

## Quick Test
```bash
# create two accounts
curl -X POST http://localhost:8080/accounts -H "Content-Type: application/json" -d '{"account_id": 123, "initial_balance": "100.00"}'
curl -X POST http://localhost:8080/accounts -H "Content-Type: application/json" -d '{"account_id": 456, "initial_balance": "50.00"}'

# transfer money
curl -X POST http://localhost:8080/transactions -H "Content-Type: application/json" -d '{"source_account_id": 123, "destination_account_id": 456, "amount": "25.00"}'

# check balances
curl http://localhost:8080/accounts/123
curl http://localhost:8080/accounts/456
```

## Notes

- Uses pessimistic locking to handle concurrent transfers
- Amounts stored with 5 decimal precision
- Tables are auto-created by Hibernate on startup
- No auth implemented (as per requirements)

## Troubleshooting

If postgres connection fails, check that:
- Database exists and postgres is running
- Username/password in application.properties are correct
- Port 5432 is accessible

If port 8080 is already taken, change it in application.properties: `server.port=8081`