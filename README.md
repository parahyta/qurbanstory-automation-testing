# Qurban Story Automation Testing

End-to-end automation testing project for Qurban Story using Selenium WebDriver, Cucumber BDD, Maven, and Page Object Model (POM).

## Team Members

| Name                       | Student ID         |
| -------------------------- | ------------------ |
| Syakira Zahratul Firdaus   | 24/541424/SV/24904 |
| Okta Alshina Arva Parahyta | 24/535154/SV/24161 |
| Nawwaf Zayyan Musyafa      | 24/540567/SV/24877 |
| Della Nurizki              | 24/541430/SV/24905 |

## Technologies

* Java 21
* Maven
* Selenium WebDriver
* Cucumber BDD
* JUnit 5
* Page Object Model (POM)

## Testing Scope

This project focuses on Black-Box Testing and BDD automation testing for:

* Landing Page
* Login Page
* Product Page
* Checkout Page
* Invoice Page

## Testing Techniques

### Equivalence Partitioning (EP)

Implemented on checkout phone number validation:

* Valid phone number input
* Invalid phone number containing non-numeric characters

### Boundary Value Analysis (BVA)

Implemented on checkout phone number validation:

* Minimum boundary (9 digits)
* Below minimum boundary (8 digits)
* Maximum boundary (15 digits)
* Above maximum boundary (16 digits)

## Implemented Test Cases

### Landing Page

* TC-LND-01 Navigation to Product Page
* TC-LND-02 Navigation to Login Page

### Authentication

* TC-LGN-01 Login using Google OAuth

### Product Validation

* TC-PRD-01 View Product Detail
* TC-PRD-02 Out of Stock Validation

### Checkout Validation

* TC-CHK-01 to TC-CHK-06 Phone Number Validation

### Participant Quota

* TC-QTY-01 to TC-QTY-04 Participant Validation

### Invoice Validation

* TC-INV-01 Successful Payment
* TC-INV-02 Invoice Detail Validation
* TC-INV-03 Expired Invoice Validation

## Project Structure

```text
src/test/java
├── pages
├── stepdefinitions
├── runners
└── utils

src/test/resources
└── features
```

## Prerequisites

Before running the automation:

1. Install Java 21 or later.
2. Install Maven.
3. Install Google Chrome.
4. Export authentication cookies if required for checkout and invoice testing.
5. Place the cookie file according to project configuration.

## How to Run

Run all test scenarios:

```bash
mvn clean test
```

## Test Result

Current test suite execution:

```text
Tests Run : 18
Failures  : 0
Errors    : 0
Skipped   : 0
```
