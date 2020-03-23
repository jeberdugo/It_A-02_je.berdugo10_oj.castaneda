--- Sentencias SQL para la creaci�n del esquema de parranderos
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer

-- USO
-- Copie el contenido deseado de este archivo en una pesta�a SQL de SQL Developer
-- Ejec�telo como un script - Utilice el bot�n correspondiente de la pesta�a utilizada
    
-- Eliminar todas las tablas de la base de datos
DROP TABLE "USUARIO" CASCADE CONSTRAINTS;
DROP TABLE "OPERADOR" CASCADE CONSTRAINTS;
DROP TABLE "CLIENTE" CASCADE CONSTRAINTS;
DROP TABLE "RESERVA" CASCADE CONSTRAINTS;
DROP TABLE "OFERTA" CASCADE CONSTRAINTS;
DROP TABLE "ALOJAMIENTO" CASCADE CONSTRAINTS;
COMMMIT;

-- Eliminar el contenido de todas las tablas de la base de datos
-- El orden es importante. Por qu�E
delete from USUARIO;
delete from OPERADOR;
delete from CLIENTE;
delete from RESERVA;
delete from OFERTA;
delete from ALOJAMIENTO;
commit;

