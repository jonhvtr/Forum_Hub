alter table topicos add column autor_id bigint;

alter table topicos
add constraint fk_topicos_usuarios
foreign key (autor_id) references usuarios(id);