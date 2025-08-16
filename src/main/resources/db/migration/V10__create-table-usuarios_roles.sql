create table roles (
    id bigint auto_increment not null,
    role varchar(50) not null,

    primary key(id)
);

create table usuarios_roles (
    user_id bigint not null,
    role_id bigint not null,

    primary key (user_id, role_id),
    constraint fk_users_roles_user foreign key (user_id) references usuarios(id) on delete cascade,
    constraint fk_users_roles_role foreign key (role_id) references roles(id) on delete cascade
);