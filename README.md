# Tech-Stack

## Main Techs

> **Language** : `Java` (17)

> **Web Application framework** :  `Spring boot` (3.1.2)

> **Database** : `PostgreSQL` (15.3-1)

> **Package manager** : `Maven` ()

## Main Libraries

> Build : **`Maven`** ()

> Object-Relational Mapping (ORM) : **`Jakarta`** ()

> API Documentation : **`Swagger Springfox`** (3.0.0)

## Development Environment

> OS : 
- **`Win`** (11) || **`WSL 2`** (ubuntu-20.04.1) 
- **`Win`** (11) || **`WSL 2`** (ubuntu-20.04.1) 


> IDE : **`VS Code`** (1.80.0)

---

# Techs Stacks

## Object-Relational Mapping
* Hibernate (ORM)
* JPA
* Jakarta

## Web Application framework
* Spring boot (Rest API)

## Database
* PostgreSQL
* MySQL

## Package manager
* Apache Maven

## Build
* Apache Maven

---

# Database 

Entity–Relationship Model: [DB Diagram (v0.2.2)](https://dbdiagram.io/d/64486c036b3194705132087f)

---

# Utilities

## Run application with Maven 

```bash
mvn spring-boot:run
```

## Set environment variables from file 

```bash
export $(cat dev.env | grep -v ^# | grep -v ^alias | xargs)
```


<!-- save files on database -->

