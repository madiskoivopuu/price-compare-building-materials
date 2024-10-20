# price-compare-building-materials

This is a multi-project application that includes Spring Boot for backend and React for frontend

## How to run

### Option 1 - Docker

0. Prerequisites

Make sure you have docker installed on your local machine 
[Get Docker](https://docs.docker.com/get-started/get-docker/)

1. Clone the repository 
```bash
git clone https://github.com/madiskoivopuu/price-compare-building-materials.git
```
2. Move to root directory
```bash
cd price-compare-building-materials
```
3. Docker compose up
```bash
docker compose up
```
4. Access the application 
```bash
http://localhost:3000/
```

### Option 2 - build backend and frontend locally

0. Prerequisites

- Make sure you have Node installed [Get Node](https://nodejs.org/en/download/package-manager)

1. Clone the repository
```bash
git clone https://github.com/madiskoivopuu/price-compare-building-materials.git
```
2. Move to backend directory
```bash
cd .\backend\
```
3. Build
```bash
 .\gradlew build
```
4. Run
```bash
 .\gradlew bootRun
```
5. Open a new terminal
6. Move to frontend directory
```bash
cd .\frontend\
```
7. Install dependencies
```bash
npm install
```
8. Run the application
```bash
npm start
```
4. Access the application
```bash
http://localhost:3000/
```