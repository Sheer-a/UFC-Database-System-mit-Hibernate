# UFC Database System mit Hibernate & JPA

Ein Datenbank-Projekt aus dem Studium zur Verwaltung von UFC-ähnlichen Kampfsport-Events mit Kämpfern, Veranstaltungen und Wettkämpfen.

## 🎯 Projektziel

Praktische Anwendung von Datenbankkonzepten (Entities, Relationen, JPA/Hibernate) in einem realitätsnahen Szenario.

## 🛠️ Tech Stack

- **Java 17** mit Spring Boot 3.1.1
- **MySQL** als Datenbank
- **JPA/Hibernate** für ORM
- **Gradle** als Build-Tool

## 📁 Hauptkomponenten

- **Fighter** - Kämpfer mit Körpermaßen und Gewichtsklassen
- **Event** - Kampfsport-Veranstaltungen
- **Game** - Einzelne Kämpfe
- **Referee** - Schiedsrichter
- **GameRating** - Bewertungen der Kämpfe
- **Organizer/Promoter/Sponsor** - Weitere Beteiligte

## 🚀 Setup

1. MySQL-Datenbank erstellen:
   ```sql
   CREATE DATABASE UFC_DB;
   ```

2. In [application.properties](src/main/resources/application.properties) die DB-Zugangsdaten anpassen

3. Anwendung starten:
   ```bash
   ./gradlew bootRun
   ```

## 📖 Features

- CRUD-Operationen für alle Entitäten
- JPA-Relationen (1:1, 1:n, n:m)
- Service-Layer für Business Logic
- Automatische Schema-Generierung mit Hibernate

## 📚 Kontext

Entwickelt im Rahmen eines Datenbank-Kurses zur Demonstration von:
- Entity-Relationship-Modellierung
- JPA-Annotationen und Mapping
- Spring Data JPA
- Transaktions-Management
