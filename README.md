# CarMotor

## Description

This web application project was developed as part of the **ISIS2603 Desarrollo de Software en Equipos** course at _Universidad de Los Andes_. CarMotor enables users to explore car categories, view pricing, book test drives, and get advisor support through a streamlined and intuitive interface.

<img src="assets/CarMotorCars.jpg" alt="Cars preview" width="700"/>

## Project Documentation

- [UML Diagram](ISIS2603_202410_S3_E4_CarMotor_Back/ENTREGABLES/UML.jpg)

- [Project Pitch Presentation](https://www.canva.com/design/DAGpvFuU3YU/DBwR89X-uaEkCFO0OyGLUw/edit?utm_content=DAGpvFuU3YU&utm_campaign=designshare&utm_medium=link2&utm_source=sharebutton)

- [Script for inserting data into the SQL Database](ISIS2603_202410_S3_E4_CarMotor_Back/sql/data.sql)

## Tech Stack

- **Backend:** Java with Spring Boot (Restful APIs, Data Persistence, Security)
- **Frontend:** Angular (Component-Based Architecture, Reactive Forms, HTTP Client)
- **Data Persistence:** Implemented using a SQL-based relational in-memory database.

## Repository Structure 

The project is organized into two main folders:

- `CarMotor/`
    - **Backend:** `ISIS2603_202410_S3_E4_CarMotor_Back`
    - **Frontend:** `ISIS2603_202410_S3_E4_CarMotor_Front`

## Prerequisites

- **Backend:**
    - Java 11 or higher 
    - Maven 3.6+
- **Frontend:**
    - Node.js 14.x or higher
    - npm 6.x or higher (or Yarn)

## Running the Backend

1. Open a terminal and navigate to the `backend` directory:
```bash
cd ISIS2603_202410_S3_E4_CarMotor_Back
```
2. Build and run the application via Maven:
```bash
mvn clean spring-boot:run
```
3. The backend will start on `http://localhost:8999/api`

4. Access the H2 In-Memory Database (Optional)
    - Open your browser and go to `http://localhost:8080/h2-console`
    - Use the following credentials and connect
```bash
JDBC URL: jdbc:h2:mem:CarMotor
User: sa
Password: password
```
5. Insert Data into the Database Tables (Optional)
    - Paste this [script](ISIS2603_202410_S3_E4_CarMotor_Back/sql/data.sql) 
    - Click the run button

## Running the Frontend 

1. Open a terminal and navigate to the `frontend` directory:
```bash
cd ISIS2603_202410_S3_E4_CarMotor_Front
```
2. Install dependencies:
```bash
npm install
```
Or
```bash
yarn install
```
3. Start the development server:
```bash
ng serve
```
4. Open your browser at `http://localhost:4200` to use the app.

## Collaborators

- Juan Felipe Ledesma
- Santiago Orduz
- Karen Fuentes
- Luis Bobadilla
- Samara Martinez 





