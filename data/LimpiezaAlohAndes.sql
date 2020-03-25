--- Sentencias SQL para la creacion del esquema de AlohAndes

-- USO
-- Copie el contenido deseado de este archivo en SQL Developer
-- Ejecutelo como un script - Utilice el boton correspondiente de la pestana utilizada
    
-- Eliminar todas las tablas de la base de datos
DROP TABLE USUARIO CASCADE CONSTRAINTS;
DROP TABLE OPERADOR CASCADE CONSTRAINTS;
DROP TABLE CLIENTE CASCADE CONSTRAINTS;
DROP TABLE RESERVA CASCADE CONSTRAINTS;
DROP TABLE OFERTA CASCADE CONSTRAINTS;
DROP TABLE ALOJAMIENTO CASCADE CONSTRAINTS;
DROP TABLE HORARIO CASCADE CONSTRAINTS;
DROP TABLE REGLA CASCADE CONSTRAINTS;
DROP TABLE MENAJE CASCADE CONSTRAINTS;
DROP TABLE SEGURO CASCADE CONSTRAINTS;
DROP TABLE SERVICIOS_ALOJAMIENTOS CASCADE CONSTRAINTS;
DROP TABLE SERVICIO CASCADE CONSTRAINTS;
DROP TABLE HABITACION CASCADE CONSTRAINTS;
COMMIT;

-- Eliminar el contenido de todas las tablas de la base de datos

DELETE FROM REGLA;
DELETE FROM MENAJE;
DELETE FROM SEGURO;
DELETE FROM SERVICIOS_ALOJAMIENTOS;
DELETE FROM SERVICIO;
DELETE FROM HORARIO; 
DELETE FROM HABITACION;
DELETE FROM OFERTA;
DELETE FROM RESERVA;
DELETE FROM ALOJAMIENTO;
DELETE FROM CLIENTE;
DELETE FROM OPERADOR;
DELETE FROM USUARIO;

COMMIT;

