create table if not exists logs(
    id int auto_increment,
    script_id int not null,
    script_output blob not null,
    error_log blob null,
    primary key (id),
    foreign key (script_id) references script(id)
)