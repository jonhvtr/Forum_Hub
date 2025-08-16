create table cursos (
	id bigint auto_increment not null,
	nome varchar(255) not null,
	categoria varchar(255) not null,

    primary key(id)
);

alter table topicos add column curso_id bigint;

alter table topicos
add constraint fk_topicos_cursos
foreign key (curso_id) references cursos(id);