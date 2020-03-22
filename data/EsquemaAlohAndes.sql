--- Sentencias SQL para la creación del esquema de parranderos
--- Las tablas tienen prefijo A_ para facilitar su acceso desde SQL Developer

-- USO
-- Copie el contenido de este archivo en una pestaña SQL de SQL Developer
-- Ejecútelo como un script - Utilice el botón correspondiente de la pestaña utilizada

-- Creación del secuenciador
create sequence parranderos_sequence;

-- Creaación de la tabla USUARIO y especificación de sus restricciones
CREATE TABLE USUARIO
   (  ID NUMBER, 
	USUARIO VARCHAR2(255 BYTE), 
        CONTRASENA VARCHAR2(255 BYTE),
        CORREO VARCHAR2(255 BYTE),
        NUMDOC NUMBER,
	CONSTRAINT USUARIO_PK PRIMARY KEY (ID));
	
ALTER TABLE USUARIO
	ADD CONSTRAINT UN_USUARIO_CORREO 
	UNIQUE (CORREO)
ENABLE;

-- Creaación de la tabla OPERADOR y especificación de sus restricciones
CREATE TABLE OPERADOR 
   (    ID NUMBER, 
	TIPO NUMBER, 
	 
	CONSTRAINT OPERADOR_PK PRIMARY KEY (ID));
	
ALTER TABLE OPERADOR
ADD CONSTRAINT fk_idoperador
    FOREIGN KEY (id)
    REFERENCES usuario(id)
ENABLE;

-- Creaación de la tabla CLIENTE y especificación de sus restricciones
CREATE TABLE CLIENTE
   (    ID NUMBER, 
	NOMBRE VARCHAR2(255 BYTE), 
	ROL NUMBER, 
	CONSTRAINT CLIENTE_PK PRIMARY KEY (ID));
	 
ALTER TABLE CLIENTE
	ADD CONSTRAINT CK_CLIENTE_ROL 
	CHECK (ROL >-1 AND ROL<3)

ALTER TABLE CLIENTE
ADD CONSTRAINT fk_idcliente
    FOREIGN KEY (id)
    REFERENCES usuario(id)

ENABLE;

-- Creaación de la tabla RESERVA y especificación de sus restricciones
CREATE TABLE RESERVA
   (ID NUMBER, 
	ESTADO VARCHAR2(255 BYTE), 
	VALORTOTAL NUMBER,
        OFERTAID NUMBER,
        CLIENTEID NUMBER, 
	CONSTRAINT RESERVA_PK PRIMARY KEY (ID));

ALTER TABLE RESERVA
ADD CONSTRAINT fk_idoferta
    FOREIGN KEY (ofertaid)
    REFERENCES oferta(id)

ALTER TABLE CLIENTE
ADD CONSTRAINT fk_idcliente
    FOREIGN KEY (clienteid)
    REFERENCES cliente(id)

ENABLE;

-- Creaación de la tabla OFERTA y especificación de sus restricciones
CREATE TABLE OFERTA
(
  ID NUMBER,
  DIA DATE,
  PRECIO NUMBER,
  RESERVAID NUMBER,
  ALOJAMIENTOID NUMBER,
  CONSTRAINT OFERTA_PK PRIMARY KEY (ID));

ALTER TABLE OFERTA
ADD CONSTRAINT fk_idreserva
    FOREIGN KEY (reservaid)
    REFERENCES reserva(id)
ENABLE;
    
ALTER TABLE OFERTA
ADD CONSTRAINT fk_alojamientoid
    FOREIGN KEY (alojamientoid)
    REFERENCES alojamiento(id)
ENABLE;

-- Creaación de la tabla ALOJAMIENTO y especificación de sus restricciones
CREATE TABLE ALOJAMIENTO 
(
  ID NUMBER,
  CAPACIDAD NUMBER,
  TIPO NUMBER,
  IDOPERADOR NUMBER
  REGISTROCAM NUMBER,
  REGISTROSUP NUMBER,
  UBICACION VARCHAR2(244 BYTE), 
  DESCRIPCION VARCHAR2(244 BYTE),
  CONSTRAINT ALOJAMIENTO_PK PRIMARY KEY (ID));

ALTER ALOJAMIENTO
ADD CONSTRAINT fk_idoperador
    FOREIGN KEY (idoperador)
    REFERENCES operador(id)
ENABLE;
    



COMMIT;
