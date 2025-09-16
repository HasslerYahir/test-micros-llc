# Test Micros LLC

Este proyecto es una prueba de microservicios que incluye **Clients Service** y **Accounts Service**, ambos contenerizados con **Docker** y documentados con **Swagger**.

## Ejecución rápida

1. Clonar el repositorio:
```bash
git clone https://github.com/HasslerYahir/test-micros-llc.git
cd test-micros-llc
```bash

2.Compilar los microservicios:
```bash
./gradlew clean build -x test
```bash

3. En la ruta de descarga del repo Levantar todo con Docker:
```bash
docker compose up -d --build
```bash
Esto levanta: 

-Postgres backend_users (para clientes)
-Postgres backend_accounts (para cuentas)
-Clients Service en el puerto 8080
-Accounts Service en el puerto 8081

4. Swagger Una vez arriba, entra al navegador y prueba las APIs directamente en Swagger:

-Clients Service → http://localhost:8080/swagger-ui.html
-Accounts Service → http://localhost:8081/swagger-ui.html

5.Base de datos , No necesitas scripts .sql. Se usa Flyway, 
que versiona las bases de datos y aplica automáticamente las migraciones al iniciar los microservicios.

6. Endpoints

Clients Service: CRUD completo de clientes

Accounts Service: gestión de cuentas y movimientos

Todos los endpoints y controladores están en inglés para mantener consistencia.

7. Resiliencia

Se configuró un Circuit Breaker básico con Resilience4j para proteger las llamadas entre microservicios. Si un servicio falla o responde lento, el breaker corta las llamadas y reintenta después de unos segundos.

8. Saludos, espero ser parte del equipo.