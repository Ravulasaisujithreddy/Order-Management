# Order Management Microservices

This project is a **microservices-based order management system** built with modern cloud-native tools. It demonstrates how services interact asynchronously using **Kafka**, achieve fast responses with **Redis caching**, and run in scalable containers using **Docker Swarm**. Monitoring and observability are handled by **Prometheus** and the **ELK stack**.

---

## 🚀 Microservices

### 1. **Cart Service**
- Handles shopping cart operations (add/remove items, view cart).
- Stores temporary state in **Redis** for fast retrieval.

### 2. **Inventory Service**
- Manages stock levels and product availability.
- Listens to Kafka events for order placement and adjusts stock accordingly.

### 3. **Product Service**
- Exposes product catalog APIs.
- Interfaces with Inventory for availability checks.

### 4. **Notification Service**
- Sends order confirmation and status updates.
- Consumes Kafka events published by other services.

---

## 🛠️ Tech Stack

- **Spring Boot (Java)** → for building microservices  
- **Apache Kafka** → asynchronous event-driven communication  
- **Redis** → caching layer for performance  
- **Docker** → containerization  
- **Docker Swarm** → service orchestration & scaling  
- **Prometheus** → monitoring & service discovery  
- **ELK Stack (Elasticsearch, Logstash, Kibana)** → centralized logging & visualization  

---

## 📡 Architecture

```mermaid
flowchart LR
    subgraph Client
        UI[Frontend / API Gateway]
    end

    subgraph Services
        Cart[Cart Service]
        Product[Product Service]
        Inventory[Inventory Service]
        Notification[Notification Service]
    end

    subgraph Infra
        Kafka[(Kafka Broker)]
        Redis[(Redis Cache)]
        Prometheus[(Prometheus)]
        ELK[(ELK Stack)]
    end

    UI --> Cart
    UI --> Product

    Cart -- publish --> Kafka
    Product -- publish --> Kafka
    Inventory -- consume --> Kafka
    Notification -- consume --> Kafka

    Cart -- cache --> Redis
    Product -- metrics --> Prometheus
    Inventory -- metrics --> Prometheus
    Notification -- metrics --> Prometheus

    Services -- logs --> ELK
