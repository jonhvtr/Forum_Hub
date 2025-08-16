create table topicos (
	id bigint  auto_increment not null,
	titulo varchar(255) not null,
	mensagem varchar(255) not null,
	created_at timestamp,
	status varchar(100),

    primary key(id)
);