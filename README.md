![GitHub language count](https://img.shields.io/github/languages/count/souzafcharles/Spring-Boot-Financial-Investment-Aggregator)
![GitHub top language](https://img.shields.io/github/languages/top/souzafcharles/Spring-Boot-Financial-Investment-Aggregator)
![GitHub](https://img.shields.io/github/license/souzafcharles/Spring-Boot-Financial-Investment-Aggregator)
![GitHub last commit](https://img.shields.io/github/last-commit/souzafcharles/Spring-Boot-Financial-Investment-Aggregator)

# Spring Boot | Financial Investment Aggregator

---

☕ Project developed based on tutorials by **Bruno Garcia** - [Build & Run](https://www.youtube.com/playlist?list=PLxCh3SsamNs62j6T7bv6f1_1j9H9pEzkC).

---

## Introduction:

<p align="justify">
This introduction details the development of the Financial Investment Aggregator system, a sophisticated backend application developed using the <code>Java Spring</code> framework. The purpose of this system is to streamline the management of users, accounts, stock investments, and billing information, thereby providing an integrated solution for financial investment aggregation. It encompasses various core components that facilitate robust functionalities, including managing user data, performing CRUD operations on accounts and stocks, and integrating billing details. The architecture adheres to <code>RESTful</code> principles to ensure efficient interaction with these datasets and supports scalability and maintainability in system operations.
</p>

<p align="justify">
The Financial Investment Aggregator is configured with <code>Spring Boot</code> and integrates a <code>MySQL</code> database. This connection enables seamless data persistence for entities such as User, Account, Stock, and BillingAddress. The entities are designed with relationships that reflect the real-world correlations among financial data, leveraging techniques such as <code>@OneToOne</code>, <code>@OneToMany</code>, and <code>@ManyToMany</code>. <code>Spring Data JPA</code> further simplifies database interaction, while validation mechanisms implemented via <code>Spring Validation</code> guarantee data integrity and prevent inconsistencies. The system also incorporates pagination functionality for the efficient retrieval and presentation of data.
</p>

<p align="justify">
The system architecture relies on <code>Java Spring</code>'s dependency injection and inversion of control principles to ensure loose coupling between components. Additionally, it employs <code>OpenFeign</code> for integration with external APIs, allowing the retrieval of stock market prices and other essential data. This integration enhances the system's capability to provide real-time insights into financial investments. As a robust backend application, the Financial Investment Aggregator is designed to serve as the foundation for a comprehensive financial management system, offering reliability, scalability, and ease of integration with other systems.
</p>

---

## Project Stack:

| Technology       | Version  | Description                                       |
|------------------|----------|---------------------------------------------------|
| 📐 IntelliJ IDEA | `2024.3` | Integrated Development Environment (IDE)          |
| ☕ Java           | `21`     | Backend programming language                      |
| 🌱 Spring Boot   | `3.4.4`  | Framework for creating Spring applications        |
| 🐦 Maven         | `3.9.9`  | Build automation and dependency management tool   |
| 🐬 MySQL         | `9.2.0`  | Open-source relational database management system |
| 👩‍🚀 Postman    | `11.19`  | API testing and development tool                  |

---

## Dependencies:

| Dependency         | Category | Description                                                               |
|--------------------|----------|---------------------------------------------------------------------------|
| 🌐 Spring Web      | Web      | Build web, including RESTful, applications using Spring MVC               |
| 💾 Spring Data JPA | SQL      | Simplifies database interactions using JPA with Spring Data and Hibernate |
| 🐬 MySQL Driver    | SQL      | Provides connectivity between Java applications and MySQL databases       |

---

## Entity Diagram:

![Model Diagram](https://github.com/souzafcharles/Spring-Boot-Financial-Investment-Aggregator/blob/main/image/financial-investment-aggregator-entities.png)

## Project Logic Layered Architecture:

![Layered Architecture](https://github.com/souzafcharles/Spring-Boot-Financial-Investment-Aggregator/blob/main/image/logic-layered-architecture.png)