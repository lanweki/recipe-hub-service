create table recipe_hub_user
(
    id            serial primary key,
    username      varchar(50) not null,
    user_password varchar(50) not null,
    first_name    varchar(50),
    last_name     varchar(50),
    user_role     varchar(50) not null,
    unique (username)
);

create table recipe
(
    id                      serial primary key,
    recipe_name             varchar(50) not null,
    recipe_category         varchar(50),
    cuisine                 varchar(50),
    ingredients_description text        not null,
    instruction_description text        not null,
    description text        not null,
    create_date             timestamp,
    created_by              integer,
    picture_url             varchar(255),
    foreign key (created_by) references recipe_hub_user (id)
);

create table comment
(
    id           serial primary key,
    user_id      integer not null,
    comment_text text    not null,
    recipe_id    integer not null,
    foreign key (user_id) references recipe_hub_user (id),
    foreign key (recipe_id) references recipe (id)
);

create table user_recipe_book
(
    id        serial primary key,
    user_id   integer not null,
    recipe_id integer not null,
    unique (user_id, recipe_id)
);