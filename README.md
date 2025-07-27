
# TinyURL

    A URL shortening service built using Spring Boot, with support for in-memory, PostgreSQL, and Redis caching. 
    This project allows users to convert long URLs into short ones and retrieve them efficiently using a combination 
    of caching and persistent storage.


# 🚀 Features

* Shortens long URLs into short codes.
* Retrieves long URLs using short codes. 
* Integrated Redis as a cache layer. 
* Integrated PostgreSQL as persistent storage. 
* JVM in-memory fallback for dev/testing. 
* Implements Cache-Aside Pattern. 
* Configurable to switch between persistence strategies. 
* Built with extensibility and production-readiness in mind.


# 🛠️ Technologies Used

* Java 17+ 
* Spring Boot 
* PostgreSQL
* Redis
* Lombok 
* Maven
* SLF4J Logging


# 🧠 Architecture Overview

       Client
         |
     [POST/GET]
         |
    ┌─────────────┐
    │  Controller │
    └─────┬───────┘
          ↓
      [Service]
          ↓
    ┌─────▼──────┐
    │  Redis     │ <─── Cache Layer
    └─────┬──────┘
          ↓ (if not found)
    ┌─────▼─────────┐
    │ PostgreSQL DB │ <─── Persistent Layer
    └───────────────┘



# 📬 API Endpoints

**🔹 POST /shorten**

Shortens a long URL.

* Request Body (raw text/plain):

    `https://www.geeksforgeeks.org/linux-unix/linux-commands-cheat-sheet/`

* Response (200 OK):

    `http://localhost:8080/u/d31a9b`


# 🔹 GET /u/{shortCode}

Redirects to the original long URL.

* Example:

    `http://localhost:8080/u/d31a9b`


⚙️ Configuration (Optional)

You can toggle between different storage strategies (inMemory, postgres) via properties:

    url.repository.type=postgres






