create table if not exists halls
(
    id          serial primary key,
    halls_name        varchar not null,
    row_count   int     not null,
    place_count int     not null,
    description varchar not null
);