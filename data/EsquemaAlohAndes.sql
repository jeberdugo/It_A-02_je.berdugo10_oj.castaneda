	--- Sentencias SQL para la creacion del esquema de AlohAndes

-- USO
-- Copie el contenido de este archivo en una pesta SQL de SQL Developer
-- Ejecutelo como un script - Utilice el boton correspondiente de la pesta utilizada

-- Creacion del secuenciador
create sequence alohandes_sequence;

-- Creacion de la tabla USUARIO
CREATE TABLE USUARIO
    (   ID NUMBER, 
        USUARIO VARCHAR2(255 BYTE), 
        CONTRASENA VARCHAR2(255 BYTE),
        CORREO VARCHAR2(255 BYTE),
        NUMERO_DOCUMENTO NUMBER,
        TIPO_DOCUMENTO NUMBER,
        CONSTRAINT USUARIO_PK PRIMARY KEY (ID));
    
-- Creacion de la tabla OPERADOR
CREATE TABLE OPERADOR 
    (   ID NUMBER, 
        TIPO NUMBER, 
        CONSTRAINT OPERADOR_PK PRIMARY KEY (ID));
  
-- Creacion de la tabla CLIENTE
CREATE TABLE CLIENTE
    (   ID NUMBER, 
        NOMBRE VARCHAR2(255 BYTE), 
        ROL NUMBER, 
        CONSTRAINT CLIENTE_PK PRIMARY KEY (ID));  
    
-- Creacion de la tabla OFERTA
CREATE TABLE OFERTA
    (   ID NUMBER,
        DIA DATE,
        PRECIO NUMBER,
        RESERVAID NUMBER,
        ALOJAMIENTOID NUMBER,
        CONSTRAINT OFERTA_PK PRIMARY KEY (ID));
    
-- Creacion de la tabla RESERVA
CREATE TABLE RESERVA
    (   ID NUMBER, 
        ESTADO VARCHAR2(255 BYTE), 
        VALORTOTAL NUMBER,
        CLIENTEID NUMBER, 
        CONSTRAINT RESERVA_PK PRIMARY KEY (ID));
        
-- Creacion de la tabla ALOJAMIENTO
CREATE TABLE ALOJAMIENTO 
    (   ID NUMBER,
        CAPACIDAD NUMBER,
        TIPO NUMBER,
        IDOPERADOR NUMBER,
        REGISTROCAM NUMBER,
        REGISTROSUP NUMBER,
        UBICACION VARCHAR2(244 BYTE), 
        DESCRIPCION VARCHAR2(244 BYTE),
        CONSTRAINT ALOJAMIENTO_PK PRIMARY KEY (ID));
        
-- Restricciones de la tabla USUARIO    
ALTER TABLE USUARIO
	ADD CONSTRAINT UN_USUARIO_CORREO 
	UNIQUE (CORREO)
ENABLE;
ALTER TABLE USUARIO
	ADD CONSTRAINT CK_USUARIO_TIPO_DOCUMENTO 
	CHECK (TIPO_DOCUMENTO >-1 AND TIPO_DOCUMENTO <3)
ENABLE;

-- Restricciones de la tabla OPERADOR 
ALTER TABLE OPERADOR
ADD CONSTRAINT FK_IDUSUARIO
    FOREIGN KEY (id)
    REFERENCES usuario(id)
ENABLE;

-- Restricciones de la tabla CLIENTE 
ALTER TABLE CLIENTE
	ADD CONSTRAINT CK_CLIENTE_ROL 
	CHECK (ROL >-1 AND ROL<5)
ENABLE;
ALTER TABLE CLIENTE
ADD CONSTRAINT fk_idcliente
    FOREIGN KEY (id)
    REFERENCES usuario(id)

ENABLE;

-- Restricciones de la tabla ALOJAMIENTO 
ALTER TABLE ALOJAMIENTO
ADD CONSTRAINT fk_idoperador
    FOREIGN KEY (idoperador)
    REFERENCES operador(id)
ENABLE;

-- Restricciones de la tabla OFERTA 
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

COMMIT;
