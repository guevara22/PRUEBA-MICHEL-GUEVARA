# Sistema de arriendo de vehiculos

Proyecto de siete microservicios para la Prueba 1 de DSY1103.

| Microservicio | Puerto | Base de datos |
| --- | ---: | --- |
| msclientes | 8081 | prueba1 |
| msvehiculos | 8082 | prueba1 |
| msreservas | 8083 | prueba2 |
| mspagos | 8084 | prueba3 |
| mssucursales | 8085 | prueba1 |
| msempleados | 8086 | prueba1 |
| msreportes | 8087 | prueba4 |

Antes de iniciar, crea un usuario MySQL o define `DB_USER` y `DB_PASSWORD`.
Cada servicio se ejecuta de forma independiente con `./mvnw spring-boot:run` desde su carpeta.
