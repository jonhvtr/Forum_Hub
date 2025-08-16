create table usuarios (
	id bigint auto_increment not null,
	nome varchar(255) not null,
	email varchar(255) not null,
	senha varchar(255) not null,

    primary key(id)
);

alter table topicos
add constraint fk_topicos_usuarios
foreign key (autor_id) references usuarios(id);