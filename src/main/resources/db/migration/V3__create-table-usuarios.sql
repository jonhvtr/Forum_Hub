create table usuarios (
	id bigint  auto_increment not null,
	nome varchar(255) not null,
	email varchar(255) unique not null,
	senha varchar(255),

    primary key(id)
);