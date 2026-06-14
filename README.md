# Sistema de arriendo de vehiculos

Evaluacion Parcial 2 de DSY1103 - Desarrollo FullStack I.

**Estudiante:** Michel Guevara

Proyecto individual compuesto por siete microservicios Spring Boot para administrar
clientes, vehiculos, reservas, pagos, sucursales, empleados y reportes.

## Arquitectura

Cada servicio aplica separacion por capas:

- `controller`: endpoints REST y respuestas HTTP.
- `service`: reglas de negocio, logs y transacciones.
- `repository`: persistencia con Spring Data JPA.
- `model`: entidades JPA y relaciones.
- `dto` y `mapper`: intercambio de datos sin exponer entidades.
- `exception`: manejo centralizado mediante `@ControllerAdvice`.

| Microservicio | Puerto | Base de datos | Responsabilidad |
| --- | ---: | --- | --- |
| msclientes | 8081 | prueba1 | Clientes y direcciones |
| msvehiculos | 8082 | prueba1 | Vehiculos y categorias |
| msreservas | 8083 | prueba2 | Reservas y estados |
| mspagos | 8084 | prueba3 | Pagos asociados a reservas |
| mssucursales | 8085 | prueba1 | Sucursales y regiones |
| msempleados | 8086 | prueba1 | Empleados de sucursales |
| msreportes | 8087 | prueba4 | Reportes consolidados |

## Funcionalidades

- CRUD completo mediante `GET`, `POST`, `PUT` y `DELETE`.
- Persistencia MySQL con JPA e Hibernate.
- Relaciones `OneToMany` y `ManyToOne`.
- DTOs con Bean Validation.
- Respuestas JSON mediante `ResponseEntity`.
- Excepciones centralizadas y codigos HTTP controlados.
- Logs de operaciones con SLF4J.
- Migraciones Flyway en reservas y pagos, y Liquibase en reportes.
- Comunicacion Feign: reservas consulta clientes y vehiculos; pagos consulta
  reservas; reportes consulta reservas y pagos.
- Timeouts configurados para las comunicaciones remotas.

## Ejecucion

Requisitos:

- Java 17
- MySQL 8
- Puertos 8081 a 8087 disponibles

Configura las credenciales de MySQL:

```bash
export DB_USER=root
export DB_PASSWORD=tu_clave_mysql
```

Ejecuta primero los servicios base y luego los que dependen de ellos:

```bash
cd msclientes && ./mvnw spring-boot:run
cd msvehiculos && ./mvnw spring-boot:run
cd mssucursales && ./mvnw spring-boot:run
cd msempleados && ./mvnw spring-boot:run
cd msreservas && ./mvnw spring-boot:run
cd mspagos && ./mvnw spring-boot:run
cd msreportes && ./mvnw spring-boot:run
```

Cada comando debe ejecutarse en una terminal distinta desde la raiz del proyecto.

## Endpoints principales

Todos los recursos usan la ruta `/api/v1`:

- `/clientes` y `/clientes/buscar?email=...`
- `/vehiculos` y `/vehiculos/buscar?precioMaximo=...`
- `/reservas` y `/reservas/buscar?fechaDesde=AAAA-MM-DD`
- `/pagos` y `/pagos/buscar?montoMin=...&montoMax=...`
- `/sucursales` y `/sucursales/operativas`
- `/empleados` y `/empleados/activos?anio=...`
- `/reportes`

Para probar un CRUD, agrega el identificador al final de la ruta para consultar,
actualizar o eliminar un registro, por ejemplo `/api/v1/clientes/1`.
