CREATE TABLE estados_reserva (
  id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(40) NOT NULL, descripcion VARCHAR(150) NOT NULL,
  prioridad INT NOT NULL, permite_modificacion BOOLEAN NOT NULL, activo BOOLEAN NOT NULL, fecha_creacion DATE NOT NULL
);
CREATE TABLE reservas (
  id INT AUTO_INCREMENT PRIMARY KEY, cliente_id INT NOT NULL, vehiculo_id INT NOT NULL,
  fecha_inicio DATE NOT NULL, fecha_fin DATE NOT NULL, monto_total DECIMAL(12,2) NOT NULL,
  observaciones VARCHAR(250) NOT NULL, confirmada BOOLEAN NOT NULL, fecha_creacion DATETIME NOT NULL,
  estado_reserva_id INT NOT NULL, CONSTRAINT fk_reserva_estado FOREIGN KEY (estado_reserva_id) REFERENCES estados_reserva(id)
);
INSERT INTO estados_reserva(nombre,descripcion,prioridad,permite_modificacion,activo,fecha_creacion) VALUES
('PENDIENTE','Reserva pendiente de confirmacion',1,true,true,CURRENT_DATE),
('CONFIRMADA','Reserva confirmada y vigente',2,true,true,CURRENT_DATE),
('FINALIZADA','Reserva completada correctamente',3,false,true,CURRENT_DATE);
INSERT INTO reservas(cliente_id,vehiculo_id,fecha_inicio,fecha_fin,monto_total,observaciones,confirmada,fecha_creacion,estado_reserva_id) VALUES
(1,1,DATE_ADD(CURRENT_DATE, INTERVAL 2 DAY),DATE_ADD(CURRENT_DATE, INTERVAL 5 DAY),120000,'Retiro en sucursal central',true,NOW(),2),
(2,2,DATE_ADD(CURRENT_DATE, INTERVAL 7 DAY),DATE_ADD(CURRENT_DATE, INTERVAL 10 DAY),180000,'Cliente solicita silla infantil',false,NOW(),1),
(3,3,DATE_ADD(CURRENT_DATE, INTERVAL 12 DAY),DATE_ADD(CURRENT_DATE, INTERVAL 15 DAY),240000,'Entrega en aeropuerto',false,NOW(),1);
