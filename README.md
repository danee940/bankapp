# Banking Application

This is a simple banking web application built with Spring Boot, Spring Security, Thymeleaf, and PostgreSQL.

## Getting Started

These instructions will guide you to run this project on your local machine for development and testing purposes.

### Prerequisites

1. Java 11+
2. Maven
3. PostgreSQL
4. Git (Optional)

### Running the project locally

1. **Clone the repository to your local machine**:

You can clone the project using git or download it as a zip file.

- To clone, open your terminal and run:

  ```bash
  git clone https://github.com/your_username/banking_app.git
  ```
- If you've downloaded as a zip, extract the zip file.

2. **Set up PostgreSQL**:

You need to have PostgreSQL installed on your machine. Once installed, you will have to create a database named `banking_app`. The application will use this database to persist data.

3. **Update application.properties**:

In `src/main/resources/application.properties`, update the following properties to match your PostgreSQL setup:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/banking_app
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
```

4. **Initialize the database**:

Run the following SQL script to create the necessary tables and add some demo data:

```sql
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
  id SERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  authority VARCHAR(255) NOT NULL
);

CREATE TABLE accounts (
  id SERIAL PRIMARY KEY,
  number VARCHAR(255) NOT NULL,
  balance NUMERIC(10, 2) NOT NULL,
  currency VARCHAR(3) NOT NULL,
  user_id INT NOT NULL REFERENCES users(id)
);

CREATE TABLE transactions (
  id SERIAL PRIMARY KEY,
  source_account_id INT NOT NULL REFERENCES accounts(id),
  target_account_id INT NOT NULL REFERENCES accounts(id),
  amount NUMERIC(10, 2) NOT NULL,
  type VARCHAR(255) NOT NULL
);

INSERT INTO users(username, password, enabled) 
VALUES ('user1', '$2a$10$Y2FcSF2Lb6/HCpSyBcpeiOaAsOUgisRdHLPs4qAVDnoxxHGcX2MkO', true), --password1
       ('user2', '$2a$10$R.Sc4Hv1ggz2onkv6AzOiuNfNeinjaz5kWcOFA8LcqfX8aGE8RbQu', true), --password2
       ('user3', '$2a$10$YnKDnjLYT2f1yyn36MSq..Lzx1isfnaC9ffekbUabU71KRAQ8nd/W', true); --password3

INSERT INTO authorities(username, authority) 
VALUES ('user1', 'ROLE_USER'),
       ('user2', 'ROLE_USER'),
       ('user3', 'ROLE_USER');

INSERT INTO accounts(number, balance, currency, user_id)
VALUES ('123456', 5000.00, 'USD', 1),
       ('234567', 10000.00, 'USD', 2),
       ('345678', 15000.00, 'USD', 3);
```

5. **Build and run the application**:

Navigate to the root directory of the project (where the `pom.xml` resides) in the

terminal and run:

```bash
mvn spring-boot:run
```

This will start the application and it will be accessible at `http://localhost:8080`.

## Application Features

- Login: authentication by username and password
- Logout: logout of the user from the system
- Account balance: display the logged in user‘s accounts (account number, currency, current balance)
- Transfer: transfer between own (same currency) accounts (source, target, amount)
- Transaction history: list of transactions assigned to the selected account (source/target account, currency, amount, transaction type (credit or debit), available balance) 