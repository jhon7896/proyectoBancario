/*
Empresa        :  EurekaBank
Software       :  Sistema de Cuentas de Ahorro
DBMS           :  MySQL Server
Base de Datos  :  eurekabank
Script         :  Crea la Base de Datos
*/


-- =============================================
-- Creación de la Base de Datos
-- =============================================

CREATE DATABASE IF NOT EXISTS eurekabank;


-- =============================================
-- Seleccionar la Base de Datos
-- =============================================

USE eurekabank;


-- =============================================
-- Eliminar las tablas en caso existan
-- =============================================

DROP TABLE IF EXISTS asignado;
DROP TABLE IF EXISTS contador;
DROP TABLE IF EXISTS parametro;
DROP TABLE IF EXISTS movimiento;
DROP TABLE IF EXISTS tipomovimiento;
DROP TABLE IF EXISTS cuenta;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS empleado;
DROP TABLE IF EXISTS sucursal;
DROP TABLE IF EXISTS interesmensual;
DROP TABLE IF EXISTS costomovimiento;
DROP TABLE IF EXISTS cargomantenimiento;
DROP TABLE IF EXISTS moneda;
select * from costomovimiento;
-- =============================================
-- Creación de los Objetos de la Base de Datos
-- =============================================

CREATE TABLE TipoMovimiento (
	tipocodigo       CHAR(3) NOT NULL,
	tipodescripcion  VARCHAR(40) NOT NULL,
	tipoaccion       VARCHAR(10) NOT NULL,
	tipoestado       VARCHAR(15) DEFAULT 'ACTIVO' NOT NULL,
	CONSTRAINT PK_TipoMovimiento 
		PRIMARY KEY (tipocodigo),
	CONSTRAINT tipomovimiento_tipoaccion
		CHECK (tipoaccion IN ('INGRESO', 'SALIDA')),
	CONSTRAINT tipomovimiento_tipoestado
		CHECK (tipoestado IN ('ACTIVO', 'ANULADO', 'CANCELADO'))						
) ENGINE = INNODB ;

CREATE TABLE Sucursal (
	sucucodigo       CHAR(3) NOT NULL,
	sucunombre       VARCHAR(50) NOT NULL,
	sucuciudad       VARCHAR(30) NOT NULL,
	sucudireccion    VARCHAR(50) NULL,
	sucucontcuenta   INTEGER NOT NULL,
	CONSTRAINT PK_Sucursal 
		PRIMARY KEY (sucucodigo)
) ENGINE = INNODB ;

CREATE TABLE Empleado (
	emplcodigo       CHAR(4) NOT NULL,
	emplpaterno      VARCHAR(25) NOT NULL,
	emplmaterno      VARCHAR(25) NOT NULL,
	emplnombre       VARCHAR(30) NOT NULL,
	emplciudad       VARCHAR(30) NOT NULL,
	empldireccion    VARCHAR(50) NULL,
	emplusuario      VARCHAR(15) NOT NULL,
	emplclave        VARCHAR(15) NOT NULL,
	CONSTRAINT PK_Empleado 
		PRIMARY KEY (emplcodigo),
	CONSTRAINT U_Empleado_emplusuario
		UNIQUE (emplusuario)
) ENGINE = INNODB ;

-- Procedimiento Crear Registro Empleado
delimiter $
CREATE PROCEDURE REGISTRAR_EMPLEADO(emplpaterno varchar(25), emplmaterno varchar(25), emplnombre varchar(30),
emplciudad varchar(30),empldireccion varchar(50),emplusuario varchar(15),emplclave varchar(15))
begin
	declare maximo varchar(10);
    declare num int;
    declare codigo varchar(10);
    
	SET maximo = (SELECT MAX(emplcodigo) FROM Empleado);
    SET num = (SELECT LTRIM(RIGHT(maximo,4)));
    
    IF num>=1 and num<=8 then
		set num=num+1;
        set codigo=(select concat('0000', CAST(num as char)));
	ELSEIF num>=9 and num<=98 then
		set num=num+1;
        set codigo=(select concat('000', CAST(num as char)));
	ELSEIF num>=98 and num<=998 then
		set num=num+1;
        set codigo=(select concat('00', CAST(num as char)));
	ELSEIF num>=999 and num<=9999 then
		set num=num+1;
        set codigo=(select concat('0', CAST(num as char)));
	ELSE
		set codigo=(select '0001');
	END IF;
	INSERT INTO Empleado (emplcodigo,emplpaterno,emplmaterno,emplnombre,emplciudad,empldireccion,emplusuario,emplclave) value (codigo,emplpaterno,emplmaterno,emplnombre,emplciudad,empldireccion,emplusuario,emplclave);
end $

-- Generar Codigo Empleado
delimiter $
CREATE PROCEDURE Generar_Codigo_Empleado()
begin
	select max(emplcodigo) from empleado;
end $

-- Buscar Empleado
delimiter $
CREATE PROCEDURE Buscar_Empleado(IN codigo CHAR(5))
begin
	SELECT * FROM Empleado WHERE emplcodigo=codigo;
end $

-- Actualizar Empleado
delimiter $
CREATE PROCEDURE Actualizar_Empleado(in paterno varchar(60), in materno varchar(60), in nombre varchar(60),
in ciudad varchar(60), in direccion varchar(60), in usuario varchar(60), in contraseña varchar(60),IN codigo CHAR(5))
begin
	update empleado set emplpaterno=paterno, emplmaterno=materno, emplnombre=nombre, emplciudad=ciudad, empldireccion=direccion, emplusuario=usuario,
    emplclave=codigo where emplcodigo=codigo;
end $

-- Eliminar Empleado
delimiter $
CREATE PROCEDURE Eliminar_Empleado(IN codigo CHAR(5))
begin
	DELETE FROM Empleado WHERE emplcodigo=codigo;
end $

CREATE TABLE Asignado (
	asigcodigo       CHAR(6) NOT NULL,
	sucucodigo       CHAR(3) NOT NULL,
	emplcodigo       CHAR(4) NOT NULL,
	asigfechaalta    DATE NOT NULL,
	asigfechabaja    DATE NULL,
	CONSTRAINT PK_Asignado 
		PRIMARY KEY (asigcodigo), 
	KEY idx_asignado01 (emplcodigo),
	CONSTRAINT fk_asignado_empleado
		FOREIGN KEY (emplcodigo)
		REFERENCES Empleado (emplcodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT, 
	KEY idx_asignado02 (sucucodigo),
	CONSTRAINT fk_asignado_sucursal
		FOREIGN KEY (sucucodigo)
		REFERENCES Sucursal (sucucodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB;

CREATE TABLE Cliente (
	cliecodigo       CHAR(5) NOT NULL,
	cliepaterno      VARCHAR(25) NOT NULL,
	cliematerno      VARCHAR(25) NOT NULL,
	clienombre       VARCHAR(30) NOT NULL,
	cliedni          CHAR(8) NOT NULL,
	clieciudad       VARCHAR(30) NOT NULL,
	cliedireccion    VARCHAR(50) NOT NULL,
	clietelefono     VARCHAR(20) NULL,
	clieemail        VARCHAR(50) NULL,
	CONSTRAINT PK_Cliente 
		PRIMARY KEY (cliecodigo)
) ENGINE = INNODB ;

-- Procedimiento Crear Registro Cliente
delimiter $
CREATE PROCEDURE REGISTRAR_CLIENTE(cliepaterno varchar(60), cliematerno varchar(60), clienombre varchar(60),
cliedni varchar(60),clieciudad varchar(60),cliedireccion varchar(60),clietelefono varchar(60),clieemail varchar(60))
begin
	declare maximo varchar(10);
    declare num int;
    declare codigo varchar(10);
    
	SET maximo = (SELECT MAX(cliecodigo) FROM cliente);
    SET num = (SELECT LTRIM(RIGHT(maximo,5)));
    
    IF num>=1 and num<=8 then
		set num=num+1;
        set codigo=(select concat('0000', CAST(num as char)));
	ELSEIF num>=9 and num<=98 then
		set num=num+1;
        set codigo=(select concat('000', CAST(num as char)));
	ELSEIF num>=98 and num<=998 then
		set num=num+1;
        set codigo=(select concat('00', CAST(num as char)));
	ELSEIF num>=998 and num<=9998 then
		set num=num+1;
        set codigo=(select concat('0', CAST(num as char)));
	ELSE
		set codigo=(select '00001');
	END IF;
	INSERT INTO Cliente (cliecodigo,cliepaterno,cliematerno,clienombre,cliedni,clieciudad,cliedireccion,clietelefono,clieemail) value (codigo,cliepaterno,cliematerno,clienombre,cliedni,clieciudad,cliedireccion,clietelefono,clieemail);
end $

-- Generar Codigo cliente
delimiter $
CREATE PROCEDURE Generar_Codigo_Cliente()
begin
	select max(cliecodigo) from cliente;
end $

-- Buscar Cliente
delimiter $
CREATE PROCEDURE Buscar_Cliente(IN codigo CHAR(5))
begin
	SELECT * FROM Cliente WHERE cliecodigo=codigo;
end $

-- Actualizar Cliente
delimiter $
CREATE PROCEDURE Actualizar_Cliente(in paterno varchar(60), in materno varchar(60), in nombre varchar(60),
in dni varchar(60), in ciudad varchar(60), in direccion varchar(60), in telefono varchar(60), in email varchar(60),IN codigo CHAR(5))
begin
	update cliente set cliepaterno=paterno, cliematerno=materno, clienombre=nombre, cliedni=dni, clieciudad=ciudad, cliedireccion=direccion,
    clietelefono=telefono, clieemail=email where cliecodigo=codigo;
end $

-- Eliminar Cliente
delimiter $
CREATE PROCEDURE Eliminar_Cliente(IN codigo CHAR(5))
begin
	DELETE FROM Cliente WHERE cliecodigo=codigo;
end $

-- Listar Cliente
delimiter $
CREATE PROCEDURE Mostrar_Cliente()
begin
	SELECT * FROM Cliente;
end $

-- Listar Clientes ordenados por apellidos
delimiter $
CREATE procedure Mostrar_Clientes_Por_Apellidos()
begin
	SELECT * FROM cliente ORDER BY cliepaterno, cliematerno ASC;
end $

-- Listar Clientes ordenados por DNI
delimiter $
CREATE procedure Mostrar_Clientes_Por_DNI()
begin
	SELECT * FROM cliente ORDER BY cliedni ASC;
end $

CREATE TABLE Moneda (
	monecodigo       CHAR(2) NOT NULL,
	monedescripcion  VARCHAR(20) NOT NULL,
	CONSTRAINT PK_Moneda 
		PRIMARY KEY (monecodigo)
) ENGINE = INNODB ;

CREATE TABLE Cuenta (
	cuencodigo       CHAR(8) NOT NULL,
	monecodigo       CHAR(2) NOT NULL,
	sucucodigo       CHAR(3) NOT NULL,
	emplcreacuenta   CHAR(4) NOT NULL,
	cliecodigo       CHAR(5) NOT NULL,
	cuensaldo        DECIMAL(12,2) NOT NULL,
	cuenfechacreacion DATE NOT NULL,
	cuenestado       VARCHAR(15) DEFAULT 'ACTIVO' NOT NULL,
	cuencontmov      INTEGER NOT NULL,
	cuenclave        CHAR(6) NOT NULL,
	CONSTRAINT cuenta_cuenestado
		CHECK (cuenestado IN ('ACTIVO', 'ANULADO', 'CANCELADO')),
	CONSTRAINT PK_Cuenta 
		PRIMARY KEY (cuencodigo), 
	KEY idx_cuenta01 (cliecodigo),
	CONSTRAINT fk_cuenta_cliente
		FOREIGN KEY (cliecodigo)
		REFERENCES Cliente (cliecodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT, 
	KEY idx_cuenta02 (emplcreacuenta),
	CONSTRAINT fk_cuente_empleado
		FOREIGN KEY (emplcreacuenta)
		REFERENCES Empleado (emplcodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT, 
	KEY idx_cuenta03 (sucucodigo),
	CONSTRAINT fk_cuenta_sucursal
		FOREIGN KEY (sucucodigo)
		REFERENCES Sucursal (sucucodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT, 
	KEY idx_cuenta04 (monecodigo),
	CONSTRAINT fk_cuenta_moneda
		FOREIGN KEY (monecodigo)
		REFERENCES Moneda (monecodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB ;

CREATE TABLE Movimiento (
	cuencodigo       CHAR(8) NOT NULL,
	movinumero       INTEGER NOT NULL,
	movifecha        DATE NOT NULL,
	emplcodigo       CHAR(4) NOT NULL,
	tipocodigo       CHAR(3) NOT NULL,
	moviimporte      DECIMAL(12,2) NOT NULL,
	cuenreferencia   CHAR(8) NULL,
	CONSTRAINT Movimiento_importe4
		CHECK (moviimporte >= 0.0),		 
	CONSTRAINT PK_Movimiento 
		PRIMARY KEY (cuencodigo, movinumero), 
	KEY idx_movimiento01 (tipocodigo),
	CONSTRAINT fk_movimiento_tipomovimiento
		FOREIGN KEY (tipocodigo)
		REFERENCES TipoMovimiento (tipocodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT,
	KEY idx_movimiento02 (emplcodigo),
	CONSTRAINT fk_movimiento_empleado
		FOREIGN KEY (emplcodigo)
		REFERENCES Empleado (emplcodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT, 
	KEY idx_movimiento03 (cuencodigo),
	CONSTRAINT fk_movimiento_cuenta
		FOREIGN KEY (cuencodigo)
		REFERENCES Cuenta (cuencodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB ;

CREATE TABLE Parametro (
	paracodigo       CHAR(3) NOT NULL,
	paradescripcion  VARCHAR(50) NOT NULL,
	paravalor        VARCHAR(70) NOT NULL,
	paraestado       VARCHAR(15) DEFAULT 'ACTIVO' NOT NULL,
	CONSTRAINT parametro_paraestado
		CHECK (paraestado IN ('ACTIVO', 'ANULADO', 'CANCELADO')),
	CONSTRAINT PK_Parametro 
		PRIMARY KEY (paracodigo)
) ENGINE = INNODB ;

CREATE TABLE InteresMensual (
	monecodigo       CHAR(2) NOT NULL,
	inteimporte      DECIMAL(12,2) NOT NULL,
	CONSTRAINT PK_InteresMensual 
		PRIMARY KEY (monecodigo), 
	KEY idx_interesmensual01 (monecodigo),
	CONSTRAINT fk_interesmensual_moneda
		FOREIGN KEY (monecodigo)
		REFERENCES Moneda (monecodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB ;

CREATE TABLE CostoMovimiento (
	monecodigo       CHAR(2) NOT NULL,
	costimporte      DECIMAL(12,2) NOT NULL,
	CONSTRAINT PK_CostoMovimiento 
		PRIMARY KEY (monecodigo), 
	KEY idx_costomovimiento (monecodigo),
	CONSTRAINT fk_costomovimiento_moneda
		FOREIGN KEY (monecodigo)
		REFERENCES Moneda (monecodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB ;

CREATE TABLE CargoMantenimiento (
	monecodigo       CHAR(2) NOT NULL,
	cargMontoMaximo  DECIMAL(12,2) NOT NULL,
	cargImporte      DECIMAL(12,2) NOT NULL,
	CONSTRAINT PK_CargoMantenimiento 
		PRIMARY KEY (monecodigo), 
	KEY idx_cargomantenimiento01 (monecodigo),
	CONSTRAINT fk_cargomantenimiento_moneda
		FOREIGN KEY (monecodigo)
		REFERENCES Moneda (monecodigo)
		ON DELETE RESTRICT
		ON UPDATE RESTRICT
) ENGINE = INNODB ;

CREATE TABLE Contador (
	conttabla        VARCHAR(30) NOT NULL,
	contitem         INTEGER NOT NULL,
	contlongitud     INTEGER NOT NULL,
	CONSTRAINT PK_Contador 
		PRIMARY KEY (conttabla)
) ENGINE = INNODB ;