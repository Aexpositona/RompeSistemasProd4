-- we don't know how to generate root <with-no-name> (class Root) :(

grant select on performance_schema.* to 'mysql.session'@localhost;

grant audit_abort_exempt, firewall_exempt, select, system_user on *.* to 'mysql.infoschema'@localhost;

grant audit_abort_exempt, authentication_policy_admin, backup_admin, clone_admin, connection_admin, firewall_exempt, persist_ro_variables_admin, session_variables_admin, shutdown, super, system_user, system_variables_admin on *.* to 'mysql.session'@localhost;

grant audit_abort_exempt, firewall_exempt, system_user on *.* to 'mysql.sys'@localhost;

grant alter, alter routine, application_password_admin, audit_abort_exempt, audit_admin, authentication_policy_admin, backup_admin, binlog_admin, binlog_encryption_admin, clone_admin, connection_admin, create, create role, create routine, create tablespace, create temporary tables, create user, create view, delete, drop, drop role, encryption_key_admin, event, execute, file, firewall_exempt, flush_optimizer_costs, flush_status, flush_tables, flush_user_resources, group_replication_admin, group_replication_stream, index, innodb_redo_log_archive, innodb_redo_log_enable, insert, lock tables, passwordless_user_admin, persist_ro_variables_admin, process, references, reload, replication client, replication slave, replication_applier, replication_slave_admin, resource_group_admin, resource_group_user, role_admin, select, sensitive_variables_observer, service_connection_admin, session_variables_admin, set_user_id, show databases, show view, show_routine, shutdown, super, system_user, system_variables_admin, table_encryption_admin, telemetry_log_admin, trigger, update, xa_recover_admin, grant option on *.* to root@localhost;

create table excursion
(
    idExcursion     int auto_increment
        primary key,
    codigoExcursion varchar(10) default '' not null,
    descripcion     varchar(255)           not null,
    fecha           date                   not null,
    duracion        int                    not null,
    precio          float                  not null,
    constraint codigoExcursion
        unique (codigoExcursion)
);

create table federacion
(
    idFederacion     int auto_increment
        primary key,
    codigoFederacion varchar(7)  default '' not null,
    nombreFederacion varchar(50) default '' not null,
    codigo           varchar(255)           not null,
    nombre           varchar(255)           not null,
    constraint codigoFederacion
        unique (codigoFederacion),
    constraint nombreFederacion
        unique (nombreFederacion)
);

create table seguro
(
    idSeguro     int           not null
        primary key,
    nombreSeguro varchar(10)   not null,
    precio       decimal(5, 2) not null,
    id           int           not null,
    nombre       varchar(255)  null,
    constraint idSeguro
        unique (idSeguro),
    constraint nombreSeguro
        unique (nombreSeguro)
);

create table socio
(
    idSocio           int auto_increment
        primary key,
    tipo              int         default 0  not null,
    codigoSocio       varchar(7)  default '' not null,
    nombreSocio       varchar(50) default '' not null,
    nifSocio          varchar(9)  default '' not null,
    DTYPE             varchar(31)            not null,
    numSocioTutor     varchar(255)           null,
    idFederacion      int                    null,
    idSeguro          int                    null,
    id                bigint      default 0  not null,
    seguro            varchar(255)           null,
    federacion_codigo varchar(255)           null,
    codigoFederacion  varchar(255)           null,
    nomreSeguro       varchar(255)           null,
    nombreSeguro      varchar(255)           null,
    constraint codigoSocio
        unique (codigoSocio),
    constraint FK7gf677l4jc69kpvdgjyaqel39
        foreign key (idSeguro) references seguro (idSeguro)
);

create table estandar
(
    idSocio  int auto_increment
        primary key,
    idSeguro int not null,
    constraint estandar_ibfk_1
        foreign key (idSocio) references socio (idSocio)
            on update cascade,
    constraint estandar_ibfk_2
        foreign key (idSeguro) references seguro (idSeguro)
            on update cascade
);

create index idSeguro
    on estandar (idSeguro);

create table federado
(
    idSocio      int auto_increment
        primary key,
    idFederacion int not null,
    constraint federado_ibfk_1
        foreign key (idSocio) references socio (idSocio)
            on update cascade,
    constraint federado_ibfk_2
        foreign key (idFederacion) references federacion (idFederacion)
            on update cascade
);

create index idFederacion
    on federado (idFederacion);

create table infantil
(
    idSocio      int auto_increment
        primary key,
    idSocioTutor int not null,
    constraint infantil_ibfk_1
        foreign key (idSocio) references socio (idSocio)
            on update cascade,
    constraint infantil_ibfk_2
        foreign key (idSocioTutor) references socio (idSocio)
            on update cascade
);

create index idSocioTutor
    on infantil (idSocioTutor);

create table inscripcion
(
    idInscripcion    int auto_increment
        primary key,
    fechaInscripcion date         not null,
    idSocio          int          null,
    idExcursion      int          null,
    numero           varchar(255) not null,
    codigoExcursion  varchar(255) null,
    constraint inscripcion_ibfk_1
        foreign key (idSocio) references socio (idSocio)
            on update cascade,
    constraint inscripcion_ibfk_2
        foreign key (idExcursion) references excursion (idExcursion)
            on update cascade
);

create index FK683ax83qlngkwb5pjrs0akiwn
    on inscripcion (codigoExcursion);

create index idExcursion
    on inscripcion (idExcursion);

create index idSocio
    on inscripcion (idSocio);

create
    definer = root@localhost procedure InsertarEstandar(IN nombre varchar(50), IN nif varchar(9), IN seguro int)
BEGIN
    INSERT INTO Socio(tipo, nombreSocio, nifSocio)
    VALUES (1, nombre, nif);

    SET @ultimo_id = LAST_INSERT_ID();

    INSERT INTO Estandar(idSocio, idSeguro)
    VALUES (@ultimo_id, seguro);

    UPDATE Socio SET codigoSocio = gen_codigo(3, @ultimo_id) WHERE idSocio = @ultimo_id;

    SELECT "Usuario Estandar añadido." AS Resultado,
           so.idSocio,
           so.codigoSocio,
           so.nombreSocio,
           so.nifSocio,
           se.nombreSeguro,
           se.precio
    FROM Socio so
             JOIN Estandar e ON so.idSocio = e.idSocio
             JOIN Seguro se ON e.idSeguro = se.idSeguro
    WHERE so.idSocio = @ultimo_id;

END;

create
    definer = root@localhost procedure InsertarExcursion(IN descripcion varchar(255), IN fecha date, IN duracion int,
                                                         IN precio decimal(5, 2))
BEGIN
    INSERT INTO Excursion(descripcion, fecha, duracion, precio)
    VALUES (descripcion, fecha, duracion, precio);

    SET @ultimo_id = LAST_INSERT_ID();

    UPDATE Excursion SET codigoExcursion = gen_codigo(1, @ultimo_id) WHERE idExcursion = @ultimo_id;

    SELECT "Excursión añadida." AS Resultado,
           ex.idExcursion,
           ex.codigoExcursion,
           ex.descripcion,
           ex.fecha,
           ex.duracion,
           ex.precio
    FROM Excursion ex
    WHERE ex.idExcursion = @ultimo_id;

END;

create
    definer = root@localhost procedure InsertarFederacion(IN nombre varchar(50))
BEGIN
    INSERT INTO Federacion(nombreFederacion)
    VALUES (nombre);

    SET @ultimo_id = LAST_INSERT_ID();

    UPDATE Federacion SET codigoFederacion = gen_codigo(4, @ultimo_id) WHERE idFederacion = @ultimo_id;

    SELECT "Federación añadida." AS Resultado,
           fe.idFederacion,
           fe.codigoFederacion,
           fe.nombreFederacion
    FROM Federacion fe
    WHERE fe.idFederacion = @ultimo_id;

END;

create
    definer = root@localhost procedure InsertarFederado(IN nombre varchar(50), IN nif varchar(9), IN federacion int)
BEGIN
    INSERT INTO Socio(tipo, nombreSocio, nifSocio)
    VALUES (2, nombre, nif);

    SET @ultimo_id = LAST_INSERT_ID();

    INSERT INTO Federado(idSocio, idFederacion)
    VALUES (@ultimo_id, federacion);

    UPDATE Socio SET codigoSocio = gen_codigo(3, @ultimo_id) WHERE idSocio = @ultimo_id;

    SELECT "Usuario Federado añadido." AS Resultado,
           so.idSocio,
           so.codigoSocio,
           so.nombreSocio,
           so.nifSocio,
           fe.nombreFederacion
    FROM Socio so
             JOIN Federado fedo ON so.idSocio = fedo.idSocio
             JOIN Federacion fe ON fe.idFederacion = fedo.idFederacion
    WHERE so.idSocio = @ultimo_id;

END;

create
    definer = root@localhost procedure InsertarInfantil(IN nombre varchar(50), IN nif varchar(9), IN socioTutor int)
BEGIN
    INSERT INTO Socio(tipo, nombreSocio, nifSocio)
    VALUES (3, nombre, nif);

    SET @ultimo_id = LAST_INSERT_ID();

    INSERT INTO Infantil(idSocio, idSocioTutor)
    VALUES (@ultimo_id, socioTutor);

    UPDATE Socio SET codigoSocio = gen_codigo(3, @ultimo_id) WHERE idSocio = @ultimo_id;

    SELECT "Usuario Infantil añadido." AS Resultado,
           so.idSocio,
           so.codigoSocio,
           so.nombreSocio,
           so.nifSocio,
           (SELECT s.nombreSocio FROM socio s WHERE s.idSocio = i.idSocioTutor) AS Socio_Tutor
    FROM Socio so
             JOIN Infantil i ON so.idSocio = i.idSocio
    WHERE so.idSocio = @ultimo_id;

END;

create
    definer = root@localhost procedure InsertarInscripcion(IN fechaInscripcion date, IN idSocio int, IN idExcursion int)
BEGIN
    INSERT INTO Inscripcion(fechaInscripcion, idSocio, idExcursion)
    VALUES (fechaInscripcion, idSocio, idExcursion);

    SET @ultimo_id = LAST_INSERT_ID();

    UPDATE Inscripcion SET codigoInscripcion = gen_codigo(2, @ultimo_id) WHERE idInscripcion = @ultimo_id;

    SELECT "Inscripción añadida." AS Resultado,
           i.idInscripcion,
           i.codigoInscripcion,
           i.fechaInscripcion,
           s.nombreSocio,
           ex.descripcion
    FROM Inscripcion i
             JOIN Excursion ex ON i.idExcursion = ex.idExcursion
             JOIN Socio s ON i.idSocio = s.idSocio
    WHERE ex.idExcursion = @ultimo_id;

END;

create
    definer = root@localhost function gen_codigo(tipo int, id int) returns varchar(7) deterministic
BEGIN
    DECLARE codigo_socio VARCHAR(7);
    DECLARE encabezado VARCHAR(3);
    DECLARE relleno VARCHAR(4);
    SET encabezado =
            CASE
                WHEN tipo = 1 THEN "EXC"
                WHEN tipo = 2 THEN "INS"
                WHEN tipo = 3 THEN "SOC"
                WHEN tipo = 4 THEN "FED"
                ELSE ""
                END;
    SET relleno =
            CASE
                WHEN id < 10 THEN "000"
                WHEN id >= 10 AND id < 100 THEN "00"
                WHEN id >= 100 AND id < 1000 THEN "0"
                ELSE ""
                END;
    SET codigo_socio = CONCAT(encabezado, relleno, id);
    RETURN codigo_socio;
END;

