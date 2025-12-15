# Projektkalkuleringsværktøj (PKV)

### KEA Datamatiker – 2. Semester Projekt, Efterår 2025

Dette system er udviklet som et projektkalkuleringsværktøj, der understøtter planlægning og estimering af projekter gennem opgaver og underopgaver. Applikationen er udviklet som en del af 2. semesters projekt på Datamatikeruddannelsen ved KEA.

Formålet med systemet er at give et struktureret overblik over projekter, tilknyttede brugere og estimerede omkostninger, baseret på en simpel og overskuelig arkitektur.

---
## Sådan kommer du i gang
1. **Klon projektet**

2. **Opsæt MySQL-database**
    - Opret database: `pkv`
    - Kør SQL-scripts fra `src/main/resources/sql/`

3. **Indsæt værdier i `application.properties` i `src/main/resources/`**
   ```properties
   spring.application.name=pkv
   
   spring.datasource.url=jdbc:mysql://localhost:3306/pkv
   spring.datasource.username=DIT_BRUGERNAVN
   spring.datasource.password=DIT_PASSWORD
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   
   spring.sql.init.mode=always
4. **Kør applikationen**
    - Åbn projektet i IntelliJ IDEA
    - Navigér til `AlphaSolutionsPkvApplication` (Main klassen) og start programmet

### Testbrugere til login

**Admin testbruger**: Brugernavn: `admin` Adgangskode: `admin123`

**Project Manager testbruger**: Brugernavn: `john_doe` Adgangskode: `password123`

**Employee testbruger**: Brugernavn: `bob_johnson` Adgangskode: `password123`

---

## Funktionalitet

Login og sessionsbaseret autentifikation

Rollebaseret adgang (Admin, Projektleder, Medarbejder)

Oprettelse og visning af projekter

Tasks og subtasks med hierarkisk struktur

Tilknytning af brugere til projekter

Beregning af estimerede omkostninger

Adgangskontrol baseret på projekt-tilknytning
