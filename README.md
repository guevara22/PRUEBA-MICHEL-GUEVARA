# Sistema de arriendo de vehiculos

Evaluacion de DSY1103 desarrollada individualmente por **Michel Guevara**.

El proyecto implementa una arquitectura de siete microservicios Spring Boot,
Eureka Server y API Gateway. La evaluacion individual se concentra en las
entidades `Reserva`, `EstadoReserva` y `Pago`.

## Arquitectura

| Componente | Puerto | Responsabilidad |
| --- | ---: | --- |
| API Gateway | 8080 | Punto unico de entrada y balanceo |
| Eureka Server | 8761 | Registro y descubrimiento |
| CLIENTES-SERVICE | 8081 | Clientes y direcciones |
| VEHICULOS-SERVICE | 8082 | Vehiculos y categorias |
| RESERVAS-SERVICE | 8083 | Reservas y estados |
| PAGOS-SERVICE | 8084 | Pagos de reservas |
| SUCURSALES-SERVICE | 8085 | Sucursales y regiones |
| EMPLEADOS-SERVICE | 8086 | Empleados |
| REPORTES-SERVICE | 8087 | Reportes consolidados |

Todos los servicios usan Controller, Service, Repository, DTO, Mapper,
validaciones, excepciones centralizadas, logs y configuracion YAML.

## Requisitos incorporados

- Eureka Server y clientes registrados por nombre.
- API Gateway reactivo con rutas `lb://`, predicados y filtros.
- Comunicacion REST con OpenFeign, LoadBalancer y timeouts.
- Swagger/OpenAPI en los siete microservicios.
- HATEOAS en Reserva, EstadoReserva y Pago.
- Pruebas JUnit 5 y Mockito con estructura Given-When-Then.
- Reportes de cobertura JaCoCo.
- Flyway para reservas y pagos; Liquibase para reportes.
- Dockerfiles y Docker Compose para despliegue local.
- Variables de entorno para puertos, base de datos y Eureka.
- Perfil `remote` y despliegue publico de `msreservas` en Railway.

## Rutas del Gateway

Todas las llamadas funcionales se pueden realizar desde `http://localhost:8080`.

| Servicio | Rutas |
| --- | --- |
| Clientes | `/api/v1/clientes/**`, `/api/v1/direcciones/**` |
| Vehiculos | `/api/v1/vehiculos/**`, `/api/v1/categorias/**` |
| Reservas | `/api/v1/reservas/**`, `/api/v1/estados-reserva/**` |
| Pagos | `/api/v1/pagos/**` |
| Sucursales | `/api/v1/sucursales/**`, `/api/v1/regiones/**` |
| Empleados | `/api/v1/empleados/**` |
| Reportes | `/api/v1/reportes/**` |

El filtro del Gateway agrega la cabecera `X-Gateway: API-GATEWAY`.

## Ejecucion con Docker

Requisito: Docker Desktop iniciado.

```bash
docker compose up --build
```

Para detener el sistema:

```bash
docker compose down
```

Para reiniciar tambien los datos:

```bash
docker compose down -v
```

La clave MySQL predeterminada para Docker es `duoc123`. Se puede reemplazar:

```bash
DB_PASSWORD=otra_clave docker compose up --build
```

## Ejecucion sin Docker

Requisitos: Java 17 y MySQL 8. Primero se crean las bases `prueba1`,
`prueba2`, `prueba3` y `prueba4`.

```bash
export DB_USER=root
export DB_PASSWORD=tu_clave_mysql
```

Iniciar en este orden, cada componente en una terminal:

```bash
cd eureka-server && ../msreservas/mvnw spring-boot:run
cd msclientes && ./mvnw spring-boot:run
cd msvehiculos && ./mvnw spring-boot:run
cd mssucursales && ./mvnw spring-boot:run
cd msempleados && ./mvnw spring-boot:run
cd msreservas && ./mvnw spring-boot:run
cd mspagos && ./mvnw spring-boot:run
cd msreportes && ./mvnw spring-boot:run
cd api-gateway && ../msreservas/mvnw spring-boot:run
```

## Swagger y Eureka

- Eureka: `http://localhost:8761`
- Reservas Swagger: `http://localhost:8083/swagger-ui.html`
- Pagos Swagger: `http://localhost:8084/swagger-ui.html`
- Los demas servicios usan la misma ruta Swagger en su puerto.
- Reservas Swagger remoto:
  `https://prueba-michel-guevara-production.up.railway.app/swagger-ui.html`
- Salud del servicio remoto:
  `https://prueba-michel-guevara-production.up.railway.app/actuator/health`

Ejemplo HATEOAS:

```bash
curl http://localhost:8080/api/v1/reservas/1
```

La respuesta incluye `_links` para el recurso, la coleccion y su estado.

## Despliegue remoto en Railway

El microservicio `msreservas` esta desplegado junto con una base MySQL en
Railway. El servicio se construye desde `msreservas/Dockerfile` usando
`/msreservas` como directorio raiz.

Variables configuradas en Railway:

```text
SPRING_PROFILES_ACTIVE=remote
DB_URL=jdbc:mysql://<host>:<puerto>/<base>
DB_USER=<usuario>
DB_PASSWORD=<clave>
SERVER_PORT=8083
```

Comprobacion remota:

```bash
curl https://prueba-michel-guevara-production.up.railway.app/actuator/health
curl https://prueba-michel-guevara-production.up.railway.app/api/v1/estados-reserva/1
```

El perfil `remote` usa variables de entorno, desactiva el registro en Eureka
para este despliegue individual y reconoce las cabeceras HTTPS del proxy de
Railway. Los demas microservicios se ejecutan localmente con Docker Compose.

## Pruebas y cobertura

```bash
cd msreservas && ./mvnw clean test
cd mspagos && ./mvnw clean test
```

Reportes:

- `msreservas/target/site/jacoco/index.html`
- `mspagos/target/site/jacoco/index.html`

Las pruebas unitarias cubren CRUD, busquedas y reglas de negocio:

- fecha final posterior a la inicial;
- existencia del estado de reserva;
- disponibilidad remota del vehiculo;
- validacion remota de la reserva;
- pago no superior al monto total;
- rango de montos valido.

## Defensa

Flujo recomendado para demostrar:

1. Abrir Eureka y mostrar los siete servicios y el Gateway registrados.
2. Abrir Swagger de reservas y pagos.
3. Consultar Reserva, EstadoReserva y Pago y mostrar `_links`.
4. Ejecutar una llamada por `localhost:8080` y mostrar `X-Gateway`.
5. Explicar Feign, los timeouts y las rutas `lb://`.
6. Ejecutar las pruebas y abrir el reporte JaCoCo.
7. Abrir Swagger remoto y demostrar una respuesta `200 OK`.
