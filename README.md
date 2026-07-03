# SkillBridge - Workshop Management Platform

SkillBridge is a full-stack workshop management platform that streamlines the process of organizing, managing, and participating in technical workshops. The application supports multiple user roles with secure authentication, enabling mentors to conduct workshops while allowing learners to discover and enroll in them through an intuitive interface.

---

## Features

### Authentication
- Secure user registration and login
- Role-based authorization
- Session management

### Learner
- Browse available workshops
- View workshop details
- Enroll in workshops
- View enrolled workshops
- Update profile information

### Mentor
- Create workshops
- Update workshop information
- Manage workshop schedules
- View enrolled learners
- Personal dashboard

### Admin
- Manage users
- Manage workshops
- Monitor platform activities

---

## Tech Stack

### Backend
- Java
- Spring Boot
- Spring MVC
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
- Git
- GitHub
- Maven

---

## Architecture

```
Angular Frontend
        │
 REST API Communication
        │
Spring Boot Backend
        │
 Hibernate / JPA
        │
      MySQL
```

---

## Core Functionalities

- User Authentication
- Role-Based Access Control
- Workshop Creation
- Workshop Enrollment
- Mentor Dashboard
- Learner Dashboard
- Workshop Management
- CRUD Operations
- RESTful API Integration

---

## REST APIs

The application exposes REST APIs for:

- Authentication
- User Management
- Workshop Management
- Enrollment Management
- Dashboard Operations

---

## Project Structure

```
SkillBridge
│
├── Backend
│   ├── Controller
│   ├── Service
│   ├── Repository
│   ├── Entity
│   ├── DTO
│   ├── Configuration
│   └── Exception
│
├── Frontend
│   ├── Components
│   ├── Services
│   ├── Models
│   ├── Guards
│   └── Routing
│
└── Database
```

---

## Getting Started

### Prerequisites

- Java 17+
- Maven
- MySQL
- Node.js
- Angular CLI

### Backend

```bash
git clone https://github.com/riddhidayma/Skillbridge.git

cd Skillbridge/backend

mvn spring-boot:run
```

### Frontend

```bash
cd frontend

npm install

ng serve
```

The application will be available at:

```
http://localhost:4200
```

---

## Learning Outcomes

Through this project, I gained hands-on experience in:

- Spring Boot application development
- REST API design
- Hibernate and JPA
- Angular component development
- MySQL database design
- Authentication and authorization
- Layered architecture
- Frontend-backend integration
- Git-based collaborative development

---

## Future Enhancements

- Email notifications
- Certificate generation
- Attendance management
- Workshop ratings and feedback
- File upload support
- Payment integration
- Docker deployment
- CI/CD pipeline

---

## Author

**Riddhi Dayma**

GitHub: https://github.com/riddhidayma

LinkedIn: https://www.linkedin.com/in/riddhidayma
