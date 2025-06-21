# 🧠 Saanjha Backend

The backend of **Saanjha**, a community-driven social web application where users can create posts, share media, and interact with others in real-time. Built with **Spring Boot**, connected to a **PostgreSQL** database, and deployed on **Railway**.

---

## 🔧 Tech Stack

- **Java 17+**
- **Spring Boot**
- **Maven** (build tool)
- **PostgreSQL** (relational database)
- **Cloudinary** (for image/video uploads)
- **Deployed on Railway**

---

## 📁 Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.saanjha.backend
│   │   │       ├── controller   # REST API Controllers
|   |   |       ├── dto          # Data Transfer Objects
|   |   |       ├── configs      # configurations
│   │   │       ├── service      # Business logic
│   │   │       ├── model        # Entity models
│   │   │       ├── repository   # JPA repositories
│   │   │       └── SaanjhaApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── static / templates
├── pom.xml
└── README.md
