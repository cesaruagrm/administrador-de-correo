create table usuario(
   id serial primary key ,
   nombre varchar(200) not null,
   ci integer,
   sexo varchar(2) not null,
   direccion varchar(300) not null,
   pass varchar(20) not null
);

create table contenido(
   id serial primary key,
   descripcion varchar(200) not null
);

create table alerta(
  id serial primary key ,
  diaVence integer not null,
  descripcion varchar(50) not null
);

create table depositoDigital(
  id serial primary key ,
  descripcion varchar(200),
  link text
);

create table documento(
   id serial primary key,
   titulo varchar(200) not null,
   editorial varchar(200) not null,
   autor varchar(200) not null,
   ejemplar integer ,
   idContenido integer ,
   idAlerta integer ,
   idDeposito integer ,
   foreign key(idContenido) references contenido(id),
   foreign key(idAlerta) references alerta(id),
   foreign key(idDeposito) references depositoDigital(id)
);


create table recursos(
   id serial primary key ,
   descripcion varchar(300) not null,
   idUsuario integer,
   foreign key(idUsuario) references usuario(id)
);


create table detalleRecurso(
  idRecurso integer ,
  idDocumento integer,
  foreign key(idRecurso) references recursos(id),
  foreign key(idDocumento) references documento(id)
);


insert into usuario values(0,'adm',0,'M','','admin');