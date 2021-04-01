CREATE TABLE IF NOT EXISTS script(
    id int auto_increment primary key,
    command_name varchar(10) not null,
    description varchar(100) not null,
    command longblob not null,
    created_at timestamp not null,
    modified_at timestamp not null
)