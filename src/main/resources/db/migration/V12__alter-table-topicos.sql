alter table topicos
modify status enum('NAO_RESPONDIDO', 'RESPONDIDO')
 default 'NAO_RESPONDIDO';

update topicos
set status = 'NAO_RESPONDIDO'
where status is null;