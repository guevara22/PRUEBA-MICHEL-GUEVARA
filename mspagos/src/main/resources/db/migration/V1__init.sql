CREATE TABLE pagos (
  id INT AUTO_INCREMENT PRIMARY KEY, reserva_id INT NOT NULL, codigo_transaccion VARCHAR(60) NOT NULL UNIQUE,
  monto DECIMAL(12,2) NOT NULL, medio_pago VARCHAR(30) NOT NULL, pagado BOOLEAN NOT NULL,
  fecha_pago DATETIME NOT NULL, referencia VARCHAR(100) NOT NULL
);
INSERT INTO pagos(reserva_id,codigo_transaccion,monto,medio_pago,pagado,fecha_pago,referencia) VALUES
(1,'TRX-00001',120000,'CREDITO',true,NOW(),'Pago completo reserva 1'),
(2,'TRX-00002',90000,'DEBITO',false,NOW(),'Abono reserva 2'),
(3,'TRX-00003',240000,'TRANSFERENCIA',true,NOW(),'Pago completo reserva 3');
