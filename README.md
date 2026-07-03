# SkillBridge - Workshop Management Platform

SkillBridge is a full-stack workshop management platform designed to simplify the organization and participation of technical workshops. The application provides separate interfaces for learners and mentors, enabling mentors to create and manage workshops while allowing learners to discover, enroll in, and track workshops through an intuitive web application.

---

## Features

### Authentication
- Secure user registration and login
- Role-based authentication for Learners and Mentors
- User profile management

### Learner
- Register and log in
- Browse available workshops
- View workshop details
- Enroll in workshops
- View enrolled workshops
- Update profile information

### Mentor
- Register and log in
- Create workshops
- Update workshop details
- Manage workshop schedules
- View learner enrollments
- Access mentor dashboard

---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- REST APIs

### Frontend
- Angular
- TypeScript
- HTML
- CSS

### Database
- MySQL

### Tools
- Maven
- Git
- GitHub

---

## Architecture

```
Angular Frontend
        │
        │ REST API Calls
        ▼
Spring Boot Backend
        │
Spring Data JPA / Hibernate
        │
        ▼
      MySQL
```

---

## Core Functionalities

- User Registration & Login
- Role-Based Authentication
- Workshop Creation & Management
- Workshop Enrollment
- Mentor Dashboard
- Learner Dashboard
- CRUD Operations
- REST API Integration
- Database Management using Hibernate

---

## REST APIs

The application exposes REST APIs for:

- Authentication
- Learner Management
- Mentor Management
- Workshop Management
- Enrollment Management

---

## Project Structure

```
SkillBridge
│
├── SkillBridge-Backend
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   └── resources
│   │   └── test
│   ├── pom.xml
│   └── ...
│
├── SkillBridge-Frontend
│   ├── src
│   ├── angular.json
│   ├── package.json
│   └── ...
│
└── README.md
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL
- Node.js
- Angular CLI

---

### Clone the Repository

```bash
git clone https://github.com/riddhidayma/Skillbridge.git

cd Skillbridge
```

---

### Backend Setup

Navigate to the backend project:

```bash
cd SkillBridge-Backend
```

Configure your MySQL database credentials in:

```
src/main/resources/application.properties
```

Build and run the backend:

```bash
mvn clean install

mvn spring-boot:run
```

The backend server will start on:

```
http://localhost:8080
```

---

### Frontend Setup

Open another terminal and navigate to the frontend:

```bash
cd SkillBridge-Frontend
```

Install dependencies:

```bash
npm install
```

Run the Angular application:

```bash
ng serve
```

The application will be available at:

```
http://localhost:4200
```

> Ensure the backend server is running before starting the frontend.

---

## Learning Outcomes

Through this project, I gained practical experience in:

- Building RESTful APIs using Spring Boot
- Developing layered backend architecture
- Using Hibernate and Spring Data JPA for database operations
- Designing relational database schemas with MySQL
- Developing responsive Angular components
- Integrating frontend and backend applications
- Implementing role-based application workflows
- Managing source code using Git and GitHub

---

## Future Enhancements

- Email notifications
- Workshop reminders
- Attendance tracking
- Workshop ratings and feedback
- File uploads for workshop resources
- Search and filtering
- Docker containerization
- CI/CD pipeline

---

## Author

**Riddhi Dayma**

- GitHub: https://github.com/riddhidayma
- LinkedIn: https://www.linkedin.com/in/riddhidayma
