# 🏦 ParaBank Test Automation

End-to-end automation tests for [ParaBank](https://parabank.parasoft.com) using Java, Selenium WebDriver, and TestNG with Page Object Model (POM).

## Tech Stack

| Tool | Version |
|---|---|
| Java | 23 |
| Selenium WebDriver | 4.18.1 |
| TestNG | 7.9.0 |
| Maven | Latest |

## Project Structure

src/test/java/
├── pages/
│   ├── LoginPage.java
│   ├── RegisterPage.java
│   ├── AccountPage.java
│   ├── TransferPage.java
│   └── OverviewPage.java
└── paraBankTest.java

## Test Cases

| Test | Description |
|---|---|
| `testSuccessfulLogin` | Login with valid credentials |
| `testOpenNewAccount` | Open a new savings account |
| `testTransferFunds` | Transfer funds and verify balance |

## Dynamic Test Data

Each test run registers a **unique user** using `System.currentTimeMillis()` — no hardcoded credentials!

## How to Run

1. Clone the repo:
```bash
git clone https://github.com/MousavianHanieh/parabank-automation.git
```
2. Open in IntelliJ IDEA
3. Run `paraBankTest.java` with TestNG

## Author

**Hanieh Mousavian** — QA Engineer  
[LinkedIn](https://www.linkedin.com/in/hanieh-rabani) | [GitHub](https://github.com/MousavianHanieh)
