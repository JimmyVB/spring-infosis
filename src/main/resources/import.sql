INSERT INTO tipo_habitacion (descripcion) VALUES ('Estandar');
INSERT INTO tipo_habitacion (descripcion) VALUES ('Normal');
INSERT INTO tipo_habitacion (descripcion) VALUES ('Suite');

INSERT INTO estado_habitacion (descripcion) VALUES ('Libre');
INSERT INTO estado_habitacion (descripcion) VALUES ('Ocupada');
INSERT INTO estado_habitacion (descripcion) VALUES ('En mantenimiento');
INSERT INTO estado_habitacion (descripcion) VALUES ('Limpieza');


INSERT INTO habitacion (descripcion, precio, id_tipohabitacion, id_estado) VALUES ('C101', 40.0, 1, 1);
INSERT INTO habitacion (descripcion, precio, id_tipohabitacion, id_estado) VALUES ('C102', 90.0, 4, 2);
INSERT INTO habitacion (descripcion, precio, id_tipohabitacion, id_estado) VALUES ('C205', 30.0, 3, 2);
INSERT INTO habitacion (descripcion, precio, id_tipohabitacion, id_estado) VALUES ('C210', 40.0, 2, 1);
INSERT INTO habitacion (descripcion, precio, id_tipohabitacion, id_estado) VALUES ('C311', 140.0, 1, 3);
INSERT INTO habitacion (descripcion, precio, id_tipohabitacion, id_estado) VALUES ('C323', 40.0, 3, 1);




