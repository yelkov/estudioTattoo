DROP DATABASE IF EXISTS estudio_test;
CREATE DATABASE IF NOT EXISTS estudio_test;

USE estudio_test;

  /********* TABLA 1
					CLIENTES **********/
  
  DROP TABLE IF EXISTS CLIENTES;
  CREATE TABLE IF NOT EXISTS CLIENTES (
  
	ID_CLIENTE INT UNSIGNED AUTO_INCREMENT NOT NULL,
    DNI CHAR (9) NOT NULL,
    NOMBRE VARCHAR(30)  NOT NULL,
    TELEFONO VARCHAR(15) NOT NULL,
    DIRECCIÓN VARCHAR(60) NOT NULL,
    FECHA_NACIMIENTO DATE NOT NULL,
    INSTAGRAM VARCHAR(30)  NULL,
    EMAIL VARCHAR(30)  NULL,
    /******************************/
    PRIMARY KEY (ID_CLIENTE),
    UNIQUE INDEX AK_DNI_CLIENTE(DNI),
    UNIQUE INDEX AK_TELEFONO_CLIENTE(TELEFONO),
    /************	RELACION SER TUTOR LEGAL ************/
    TUTOR INT UNSIGNED NULL,
    PARENTESCO ENUM("PADRE_MADRE","ABUELO_A","OTRO") NULL,
    FOREIGN KEY (TUTOR) REFERENCES CLIENTES(ID_CLIENTE)
				ON DELETE SET NULL
                ON UPDATE CASCADE,
	INDEX FK_TUTOR_LEGAL(TUTOR)
	
  ) ENGINE INNODB;
  
  /********* TABLA 2
					ALERGIAS_CLIENTES **********/
  
DROP TABLE IF EXISTS ALERGIAS_CLIENTES;
CREATE TABLE IF NOT EXISTS ALERGIAS_CLIENTES (
  
	CLIENTE INT UNSIGNED NOT NULL,
    ALERGIA VARCHAR(30) NOT NULL,
    /******************************/
    PRIMARY KEY (CLIENTE, ALERGIA),
    FOREIGN KEY (CLIENTE) REFERENCES CLIENTES(ID_CLIENTE)
				ON DELETE CASCADE
                ON UPDATE CASCADE,
	INDEX FK_CLIENTE_ALERGIA(CLIENTE)
    
  ) ENGINE INNODB;

  
  /********* TABLA 3
					CABINAS **********/
DROP TABLE IF EXISTS CABINAS;
CREATE TABLE IF NOT EXISTS CABINAS (
	
    ID_CABINA INT UNSIGNED AUTO_INCREMENT NOT NULL,
    UBICACION VARCHAR(30) NOT NULL,
    SUPERFICIE FLOAT UNSIGNED NOT NULL,
	PUEDE_HACER_PIERCING BOOLEAN NOT NULL,
    /******************************/
    PRIMARY KEY (ID_CABINA),
    UNIQUE INDEX AK_UBICACION (UBICACION)
)ENGINE INNODB;

 /********* TABLA 4
					HUECOS **********/
DROP TABLE IF EXISTS HUECOS;
CREATE TABLE IF NOT EXISTS HUECOS (

	NUMERO INT UNSIGNED NOT NULL,
    HORA TIME NOT NULL,
    DIA DATE NOT NULL,
    /************	RELACION TENER CABINA ************/
    CABINA INT UNSIGNED NOT NULL,
    FOREIGN KEY (CABINA) REFERENCES CABINAS(ID_CABINA)
			ON DELETE CASCADE
            ON UPDATE CASCADE,
	INDEX FK_CABINA_HUECO (CABINA),
    /************	RELACION TENER CITA ************/
    CITA INT UNSIGNED NULL,
	/*AL FINAL DEL SCRIPT AÑADIMOS FOREIGN KEY*/
    /******************************/
    PRIMARY KEY (NUMERO,CABINA),
    UNIQUE INDEX AK_DIA_HORA_CABINA(DIA,HORA,CABINA)
    
)ENGINE INNODB;

/********* TABLA 5
					PRODUCTOS **********/
DROP TABLE IF EXISTS PRODUCTOS;
CREATE TABLE IF NOT EXISTS PRODUCTOS (

	ID_PRODUCTO INTEGER UNSIGNED AUTO_INCREMENT NOT NULL,
    NOMBRE VARCHAR(30) NOT NULL,
    /******************************/
    PRIMARY KEY (ID_PRODUCTO),
    UNIQUE INDEX AK_NOMBRE(NOMBRE)

) ENGINE INNODB;

/********* TABLA 6
					AGUJAS **********/
DROP TABLE IF EXISTS AGUJAS;
CREATE TABLE IF NOT EXISTS AGUJAS (

	ID_AGUJA INT UNSIGNED AUTO_INCREMENT NOT NULL,
    TIPO VARCHAR(50) NOT NULL,
    CANTIDAD INT UNSIGNED NOT NULL,
    /******************************/
    PRIMARY KEY (ID_AGUJA),
    UNIQUE INDEX AK_TIPO_AGUJA (TIPO)
) ENGINE INNODB;

/********* TABLA 7
					TRABAJADORES **********/
DROP TABLE IF EXISTS TRABAJADORES;
CREATE TABLE IF NOT EXISTS TRABAJADORES (

	ID_TRABAJADOR INT UNSIGNED AUTO_INCREMENT NOT NULL,
    NIF CHAR(9) NOT NULL,
    NOMBRE VARCHAR(50) NOT NULL,
    NSS CHAR(12) NOT NULL,
    FECHA_NACIMIENTO DATE NOT NULL,
    FECHA_ALTA DATE NOT NULL,
    SALARIO FLOAT UNSIGNED NULL,
    EMAIL VARCHAR(30) NOT NULL,
    /******************************/
    PRIMARY KEY (ID_TRABAJADOR),
    UNIQUE INDEX AK_NIF (NIF),
    UNIQUE INDEX AK_NSS (NSS)
    
) ENGINE INNODB;


/********* TABLA 8
					ANILLADORES **********/
DROP TABLE IF EXISTS ANILLADORES;
CREATE TABLE IF NOT EXISTS ANILLADORES (

	TRABAJADOR INT UNSIGNED NOT NULL,
    COMISION FLOAT UNSIGNED NOT NULL,
    /******************************/
    PRIMARY KEY (TRABAJADOR),
    FOREIGN KEY (TRABAJADOR) REFERENCES TRABAJADORES (ID_TRABAJADOR)
			ON DELETE CASCADE
            ON UPDATE CASCADE

)ENGINE INNODB;

/********* TABLA 9
					TATUADORES **********/
DROP TABLE IF EXISTS TATUADORES;
CREATE TABLE IF NOT EXISTS TATUADORES (

	TRABAJADOR INT UNSIGNED NOT NULL,
    COMISION FLOAT UNSIGNED NOT NULL,
    /******************************/
    PRIMARY KEY (TRABAJADOR),
    FOREIGN KEY (TRABAJADOR) REFERENCES TRABAJADORES (ID_TRABAJADOR)
			ON DELETE CASCADE
            ON UPDATE CASCADE

)ENGINE INNODB;

  /********* TABLA 10
					TELEFONOS_TRABAJADORES **********/
  
DROP TABLE IF EXISTS TELEFONOS_TRABAJADORES;
CREATE TABLE IF NOT EXISTS TELEFONOS_TRABAJADORES (
  
	TRABAJADOR INT UNSIGNED NOT NULL,
    TELEFONO VARCHAR(15) NOT NULL,
    /******************************/
    PRIMARY KEY (TRABAJADOR, TELEFONO),
    FOREIGN KEY (TRABAJADOR) REFERENCES TRABAJADORES(ID_TRABAJADOR)
				ON DELETE CASCADE
                ON UPDATE CASCADE,
	INDEX FK_TRABAJADOR_TELEFONO(TRABAJADOR)
    
  ) ENGINE INNODB;

/********* TABLA 11
					CITAS **********/
DROP TABLE IF EXISTS CITAS;
CREATE TABLE IF NOT EXISTS CITAS (

	ID_CITA INT UNSIGNED AUTO_INCREMENT NOT NULL,
    TIPO ENUM("TATUAJE","PIERCING") NOT NULL,
    DESCRIPCION VARCHAR(60) NOT NULL,
    SEÑAL FLOAT UNSIGNED NOT NULL DEFAULT 0.0,
    PRECIO FLOAT UNSIGNED NOT NULL,
    ESTADO ENUM("RESERVADA","REALIZADA") NOT NULL DEFAULT "RESERVADA",
    /************	RELACION REALIZAR TATUAJE ************/
    TATUADOR INT UNSIGNED NULL, /*DISPONEMOS DE TRIGGER PARA VERIFICAR QUE SIEMPRE HAYA EN CASO DE TIPO TATUAJE*/
    FOREIGN KEY (TATUADOR) REFERENCES TATUADORES(TRABAJADOR)
			ON DELETE RESTRICT
            ON UPDATE CASCADE,
	INDEX FK_TATUADOR (TATUADOR),
    /************	RELACION REALIZAR PIERCING ************/
    ANILLADOR INT UNSIGNED NULL, /*DISPONEMOS DE TRIGGER PARA VERIFICAR QUE SIEMPRE HAYA EN CASO DE TIPO PIERCING*/
    FOREIGN KEY (ANILLADOR) REFERENCES ANILLADORES(TRABAJADOR)
			ON DELETE RESTRICT
            ON UPDATE CASCADE,
	INDEX FK_ANILLADOR (ANILLADOR),
    /************	RELACION LLEVAR A CABO EN ************/
    CABINA INT UNSIGNED NOT NULL,
    FOREIGN KEY (CABINA) REFERENCES CABINAS(ID_CABINA)
			ON DELETE RESTRICT
            ON UPDATE CASCADE,
	INDEX FK_CABINA (CABINA),
    /******************************/
    PRIMARY KEY (ID_CITA),
    UNIQUE INDEX AK_FECHA_HORA_CABINA (DESCRIPCION,CABINA)

) ENGINE INNODB;

/********* TABLA 12
					CITAS_CLIENTES **********/
DROP TABLE IF EXISTS CITAS_CLIENTES;
CREATE TABLE IF NOT EXISTS CITAS_CLIENTES (

	CLIENTE INT UNSIGNED NOT NULL,
    CITA INT UNSIGNED NOT NULL,
    /******************************/
    PRIMARY KEY (CLIENTE,CITA),
    FOREIGN KEY (CLIENTE) REFERENCES CLIENTES(ID_CLIENTE)
			ON DELETE RESTRICT
			ON UPDATE CASCADE,
	FOREIGN KEY (CITA) REFERENCES CITAS(ID_CITA)
			ON DELETE CASCADE
			ON UPDATE CASCADE
)  ENGINE INNODB;

/********* TABLA 13
					CITAS_AGUJAS **********/
DROP TABLE IF EXISTS CITAS_AGUJAS;
CREATE TABLE IF NOT EXISTS CITAS_AGUJAS (

	AGUJA INT UNSIGNED NOT NULL,
    CITA INT UNSIGNED NOT NULL,
    CANTIDAD_USADA INT UNSIGNED NOT NULL,
    /******************************/
    PRIMARY KEY (AGUJA,CITA),
    FOREIGN KEY (AGUJA) REFERENCES AGUJAS(ID_AGUJA)
			ON DELETE RESTRICT
			ON UPDATE CASCADE,
	FOREIGN KEY (CITA) REFERENCES CITAS(ID_CITA)
			ON DELETE CASCADE
			ON UPDATE CASCADE
)  ENGINE INNODB;

/********* TABLA 14
					STOCK **********/
DROP TABLE IF EXISTS STOCK;
CREATE TABLE IF NOT EXISTS STOCK (
    ID_STOCK INT UNSIGNED AUTO_INCREMENT NOT NULL,
    PRODUCTO INT UNSIGNED NOT NULL,
    CABINA INT UNSIGNED NOT NULL,
    CANTIDAD_DISPONIBLE INT UNSIGNED DEFAULT 0,
    FECHA_CADUCIDAD DATE NULL,
    /************	RELACION TENER STOCK	***************/
    FOREIGN KEY (PRODUCTO) REFERENCES PRODUCTOS(ID_PRODUCTO)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
	INDEX FK_PRODUCTO_STOCK(PRODUCTO),
	/***********	RELACION HABER EN CABINA	**********/
    FOREIGN KEY (CABINA) REFERENCES CABINAS(ID_CABINA)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,
	INDEX FK_CABINA_STOCK(CABINA),
	/******************************/
    PRIMARY KEY (ID_STOCK)
) ENGINE INNODB;

ALTER TABLE HUECOS
	ADD     FOREIGN KEY (CITA) REFERENCES CITAS (ID_CITA)
			ON DELETE SET NULL
            ON UPDATE CASCADE,
	ADD INDEX FK_CITA_HUECO (CITA);
    
    
/********** TABLA 15
						TATUADORES_TINTAS ***********/

DROP TABLE IF EXISTS TATUADORES_DISEÑOS;
CREATE TABLE IF NOT EXISTS TATUADORES_DISEÑOS (
  
	TATUADOR INT UNSIGNED NOT NULL,
    TAG VARCHAR(30) NOT NULL,
    DISEÑO LONGBLOB NOT NULL,
    /******************************/
    PRIMARY KEY (TATUADOR, TAG),
    FOREIGN KEY (TATUADOR) REFERENCES TATUADORES(TRABAJADOR)
				ON DELETE CASCADE
                ON UPDATE CASCADE,
	INDEX FK_TATUADOR_DISEÑO(TATUADOR)
    
  ) ENGINE INNODB;
  
/********** TABLA 16
						ANILLADORES_PIEZAS ***********/

DROP TABLE IF EXISTS ANILLADORES_PIEZAS;
CREATE TABLE IF NOT EXISTS ANILLADORES_PIEZAS (
  
	ANILLADOR INT UNSIGNED NOT NULL,
    PIEZA_PROPIA VARCHAR(50) NOT NULL,
    CANTIDAD INT NOT NULL,
    /******************************/
    PRIMARY KEY (ANILLADOR, PIEZA_PROPIA),
    FOREIGN KEY (ANILLADOR) REFERENCES ANILLADORES(TRABAJADOR)
				ON DELETE CASCADE
                ON UPDATE CASCADE,
	INDEX FK_ANILLADOR_PIEZA(ANILLADOR)
    
  ) ENGINE INNODB;
    
    
/*============== INTRODUCCION DE DATOS DE PRUEBA ==============*/
/*AGUJAS*/
INSERT INTO `estudio_test`.`AGUJAS` (`TIPO`, `CANTIDAD`) VALUES ('10-1-RL-MEDIUM', '50');
INSERT INTO `estudio_test`.`AGUJAS` (`TIPO`, `CANTIDAD`) VALUES ('10-3-RL-MEDIUM', '50');
INSERT INTO `estudio_test`.`AGUJAS` (`TIPO`, `CANTIDAD`) VALUES ('10-5-RL-MEDIUM', '50');
INSERT INTO `estudio_test`.`AGUJAS` (`TIPO`, `CANTIDAD`) VALUES ('10-7-RL-MEDIUM', '50');
INSERT INTO `estudio_test`.`AGUJAS` (`TIPO`, `CANTIDAD`) VALUES ('10-9-RL-MEDIUM', '50');
INSERT INTO `estudio_test`.`AGUJAS` (`TIPO`, `CANTIDAD`) VALUES ('10-3-RS-SHORT', '50');
INSERT INTO `estudio_test`.`AGUJAS` (`TIPO`, `CANTIDAD`) VALUES ('10-9-RS-SHORT', '50');
INSERT INTO `estudio_test`.`AGUJAS` (`TIPO`, `CANTIDAD`) VALUES ('10-11-RS-MEDIUM', '50');
INSERT INTO `estudio_test`.`AGUJAS` (`TIPO`, `CANTIDAD`) VALUES ('12-13-M1-MEDIUM', '50');
INSERT INTO `estudio_test`.`AGUJAS` (`TIPO`, `CANTIDAD`) VALUES ('12-17-M1-MEDIUM', '50');


/*TRABAJADORES*/
INSERT INTO `estudio_test`.`TRABAJADORES` (`NIF`, `NOMBRE`, `NSS`, `FECHA_NACIMIENTO`, `FECHA_ALTA`, `EMAIL`) VALUES ('33445556Y', 'Armando Barullo Seguro', '1234567891', '2001-09-11', '2024-02-01', 'armando.barullo@gmail.com');
INSERT INTO `estudio_test`.`TRABAJADORES` (`NIF`, `NOMBRE`, `NSS`, `FECHA_NACIMIENTO`, `FECHA_ALTA`, `EMAIL`) VALUES ('99887766N', 'Joseba Cilarte', '9876543212', '1995-07-07', '2020-02-28', 'j.cilarte@hotmail.com');
INSERT INTO `estudio_test`.`TRABAJADORES` (`NIF`, `NOMBRE`, `NSS`, `FECHA_NACIMIENTO`, `FECHA_ALTA`, `SALARIO`, `EMAIL`) VALUES ('32456798L', 'Elena Nito del Bosque', '8887776661', '1989-05-23', '2018-06-20', '1100', 'elenaNito@protommail.com');
INSERT INTO `estudio_test`.`TRABAJADORES` (`NIF`, `NOMBRE`, `NSS`, `FECHA_NACIMIENTO`, `FECHA_ALTA`, `EMAIL`) VALUES ('77889988G', 'Ana Lladora Buena', '9998881112', '1999-12-31', '2021-03-21', 'anita.piercing@gmail.com');
INSERT INTO `estudio_test`.`TRABAJADORES` (`NIF`, `NOMBRE`, `NSS`, `FECHA_NACIMIENTO`, `FECHA_ALTA`, `EMAIL`) VALUES ('44455566K', 'Paco Merlo Toito', '1234568765', '1994-04-12', '2022-10-05', 'paquito.ttt@gmail.com');
INSERT INTO `estudio_test`.`TRABAJADORES` (`NIF`, `NOMBRE`, `NSS`, `FECHA_NACIMIENTO`, `FECHA_ALTA`, `EMAIL`) VALUES ('11122233T', 'Aitor Tilla Rica', '9998887776', '1993-02-09', '2023-11-23', 'aitor.tattoer@gmail.com');

/*TATUADORES*/
INSERT INTO `estudio_test`.`TATUADORES` (`TRABAJADOR`, `COMISION`) VALUES ('1', '60');
INSERT INTO `estudio_test`.`TATUADORES` (`TRABAJADOR`, `COMISION`) VALUES ('2', '60');
INSERT INTO `estudio_test`.`TATUADORES` (`TRABAJADOR`, `COMISION`) VALUES ('5', '60');
INSERT INTO `estudio_test`.`TATUADORES` (`TRABAJADOR`, `COMISION`) VALUES ('6', '60');

/*ANILLADORES*/
INSERT INTO `estudio_test`.`ANILLADORES` (`TRABAJADOR`, `COMISION`) VALUES ('4', '70');
INSERT INTO `estudio_test`.`ANILLADORES` (`TRABAJADOR`, `COMISION`) VALUES ('2', '70');

/*TELELFONOS_TRABAJADORES*/
INSERT INTO `estudio_test`.`TELEFONOS_TRABAJADORES` (`TRABAJADOR`, `TELEFONO`) VALUES ('1', '738124497');
INSERT INTO `estudio_test`.`TELEFONOS_TRABAJADORES` (`TRABAJADOR`, `TELEFONO`) VALUES ('1', '622837285');
INSERT INTO `estudio_test`.`TELEFONOS_TRABAJADORES` (`TRABAJADOR`, `TELEFONO`) VALUES ('2', '712280433');
INSERT INTO `estudio_test`.`TELEFONOS_TRABAJADORES` (`TRABAJADOR`, `TELEFONO`) VALUES ('3', '792880017');
INSERT INTO `estudio_test`.`TELEFONOS_TRABAJADORES` (`TRABAJADOR`, `TELEFONO`) VALUES ('4', '734345034');
INSERT INTO `estudio_test`.`TELEFONOS_TRABAJADORES` (`TRABAJADOR`, `TELEFONO`) VALUES ('4', '664190214');
INSERT INTO `estudio_test`.`TELEFONOS_TRABAJADORES` (`TRABAJADOR`, `TELEFONO`) VALUES ('5', '603262915');
INSERT INTO `estudio_test`.`TELEFONOS_TRABAJADORES` (`TRABAJADOR`, `TELEFONO`) VALUES ('6', '611061270');

/*CABINAS*/
INSERT INTO `estudio_test`.`CABINAS` (`UBICACION`, `SUPERFICIE`, `PUEDE_HACER_PIERCING`) VALUES ('FONDO PIERCING', '12.5', '1');
INSERT INTO `estudio_test`.`CABINAS` (`UBICACION`, `SUPERFICIE`, `PUEDE_HACER_PIERCING`) VALUES ('ENTRADA', '10.25', '0');
INSERT INTO `estudio_test`.`CABINAS` (`UBICACION`, `SUPERFICIE`, `PUEDE_HACER_PIERCING`) VALUES ('MEDIO VENTANA', '14.5', '0');
INSERT INTO `estudio_test`.`CABINAS` (`UBICACION`, `SUPERFICIE`, `PUEDE_HACER_PIERCING`) VALUES ('MEDIO PIERCING', '12.0', '1');


/*CLIENTES*/
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('99228844M', 'Mipri Mer Cliente', '611454545', 'Calle Larga 32', '2000-07-07', 'mipri.mc@gmail.com');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('88999988H', 'Agrícolo Pérez', '622345678', 'Rúa labranza 12, Vigo', '1964-07-21', 'agricolo.granja@gmail.com');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('33488829R', 'Ferran Millan', '722545743', 'Alameda Iglesia, 24, 30150, Abanilla(murcia)', '1995-12-06', 'zcq35m1t3@yahoo.es');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('67525870D', 'Yolanda Olmo', '765015602', 'Jardines Catalunya, 78', '2001-05-29', 'zcq35m1t3@yahoo.es');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('70625723Z', 'Claudia Cobos', '638996591', 'Parque Nueva, 99 Zaragoza ', '1982-09-11', '5reyyelyx@journalism.com');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('15857824Z', 'Fidel Moral Arroyo', '736552574', 'Polígono Nueva, 88, 44109, Torrevelilla(teruel)', '1977-11-22', '1myvk6g3ni@yahoo.com');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('19788750X', 'Isabel Alcaraz Cabezas', '798882576', 'Travesía Nueva, 48, 41312, Constantina(sevilla)', '1974-08-27', 'p1oaqrn3oe@lycos.nl');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('88984923Q', 'Maria Concepcion', '771514845', 'Poblado Iglesia, 51,  Royo, El(soria)', '1974-01-08', 'f1limrds@teacher.com');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('03248042M', 'Edgar Andreu Bello', '673156446', 'Plazuela Iglesia, 31,  Peralta(huesca)', '1981-10-21', 'f19c0kn0l@netscape.com');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('27332143R', 'Maria Mar Duarte Roldan', '654212978', 'Barrio Real, 47,  Hoyo De Pinares, El(ávila)', '1983-07-09', 'bak5rbjcw@aim.com');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('08776316E', 'Enric Barroso Prieto', '627954602', 'Carretera Horno, 1, Martín Muñoz De La Dehesa(segovia)', '1977-10-04', 'y1f45woyz@msn.com');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`,`TUTOR`,`PARENTESCO`) VALUES ('30350524T', 'Enriqueta Barroso Vazquez', '702885215', 'Carretera Horno, 1, Martín Muñoz De La Dehesa(segovia)', '2007-12-07', '5h0tazwlig@yahoo.com',11,"PADRE_MADRE");
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('91909557Q', 'German Tena Campo', '613291901', 'Avenida Mayor, 61, Esgos(ourense)', '2001-04-09', 'zurp8p89@netscape.net');
INSERT INTO `estudio_test`.`CLIENTES` (`DNI`, `NOMBRE`, `TELEFONO`, `DIRECCIÓN`, `FECHA_NACIMIENTO`, `EMAIL`) VALUES ('45104684M', 'Estela Sancho Reyes', '672306906', 'Estrada De España, 96 Carenas(zaragoza)', '1997-03-10', '8rwtzgwof@witty.com');

/*PRODUCTOS*/
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('vaselina');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('rollo film transparente');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('rollo papel camilla');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('rollo papel absorbente');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('tijera');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('bolsas basura');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('bote cups pequeños');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('bote cups medianos');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('bote cups grandes');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('caja depresores');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('caja guantes');
INSERT INTO `estudio_test`.`PRODUCTOS` (`NOMBRE`) VALUES ('caja cubreclipcord');
    
    
/*TATUADORES_DISEÑOS*/
INSERT INTO `estudio_test`.`TATUADORES_DISEÑOS` (`TATUADOR`, `TAG`,`DISEÑO`) VALUES ('1', 'Tag de prueba','0x5245564E55545920424655205359532055524E55414E44205359532049524E554620616E6420435A41');
INSERT INTO `estudio_test`.`TATUADORES_DISEÑOS` (`TATUADOR`, `TAG`,`DISEÑO`) VALUES ('1', 'Nuevo Tag de prueba','0x5245564E55545920424655205359532055524E55414E44205359532049524E554620616E6420435A41');
    
/*============== INTRODUCCION DE PROCEDURES Y FUNCTIONS ==============*/    
    
/********* PROCEDIMIENTOS
							**********/
    
DELIMITER $$

/*Procedimiento para crear huecos de cabina semanales, introduciendo el lunes de inicio y el numero de semanas a rellenar*/
DROP PROCEDURE IF EXISTS CREAR_HUECOS_SEMANALES$$
CREATE PROCEDURE CREAR_HUECOS_SEMANALES(IN LUNES_INICIO DATE, IN NUM_SEMANAS INT UNSIGNED)
MODIFIES SQL DATA
BEGIN
	DECLARE HORA_ACTUAL TIME;
    DECLARE HORA_FIN TIME;
    DECLARE CONTADOR_SEMANA INT;
    DECLARE CONTADOR_DIA_SEMANA INT;
    DECLARE FECHA_ACTUAL DATE;
    DECLARE NUMERO_HUECO INT;
    
    IF WEEKDAY(LUNES_INICIO) != 0  /*NOS ASEGURAMOS QUE EL DIA INTRODUCIDO COMO PARAMETRO ES UN LUNES*/
		THEN SIGNAL SQLSTATE '45001' SET MESSAGE_TEXT = "EL DIA INTRODUCIDO COMO FECHA DE INICIO DEBE SER UN LUNES";
	END IF;
    
    SET HORA_ACTUAL = "11:00:00";
    SET HORA_FIN = "20:00:00";
    SET CONTADOR_SEMANA = 1;
    SET CONTADOR_DIA_SEMANA = 1;
    SET FECHA_ACTUAL = LUNES_INICIO;
    
    SELECT MAX(NUMERO)+1 INTO NUMERO_HUECO
		FROM HUECOS;
	IF NUMERO_HUECO IS NULL THEN SET NUMERO_HUECO = 1;
    END IF;
    
    
    
    WHILE CONTADOR_SEMANA <= NUM_SEMANAS DO /*PARA REPETIR TANTAS SEMANAS COMO SE SOLICITE POR PARÁMETRO*/
		WHILE CONTADOR_DIA_SEMANA <= 6 DO /*REPITE DE LUNES A SÁBADO, LOS DOMINGOS NO SE ABRE*/
			WHILE HORA_ACTUAL < HORA_FIN DO
            
				INSERT	INTO HUECOS
				(NUMERO, HORA,DIA, CABINA)
				SELECT
					NUMERO_HUECO,
					HORA_ACTUAL,
					FECHA_ACTUAL,
					ID_CABINA
						FROM CABINAS;
                    
				SET HORA_ACTUAL = ADDTIME(HORA_ACTUAL,"01:00:00");
				SET NUMERO_HUECO = NUMERO_HUECO + 1;    
			END WHILE;
            
            SET FECHA_ACTUAL = ADDDATE(FECHA_ACTUAL, INTERVAL 1 DAY);
            SET HORA_ACTUAL = "11:00:00";
            SET CONTADOR_DIA_SEMANA = CONTADOR_DIA_SEMANA +1;
		END WHILE;
        SET FECHA_ACTUAL = LUNES_INICIO + INTERVAL CONTADOR_SEMANA WEEK;
        SET CONTADOR_DIA_SEMANA = 1;
		SET CONTADOR_SEMANA = CONTADOR_SEMANA + 1;
	END WHILE;

END$$

/*Procedimiento para borrar huecos de cabina que hayan quedado vacíos una vez pasada la fecha del hueco*/
DROP PROCEDURE IF EXISTS ELIMINAR_HUECOS_VACIOS_PASADOS$$
CREATE PROCEDURE ELIMINAR_HUECOS_VACIOS_PASADOS()
MODIFIES SQL DATA
BEGIN
	DELETE FROM HUECOS
		WHERE DIA < CURRENT_DATE()
			AND
		CITA IS NULL;
END$$


/********* FUNCIONES
						**********/



call CREAR_HUECOS_SEMANALES('2024-12-30',1)$$
