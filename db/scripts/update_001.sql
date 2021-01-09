CREATE TABLE IF NOT EXISTS halls(
    id integer primary key not null,
    name varchar(250) not null,
    row integer not null,
    place integer not null,
    UNIQUE (row, place)
);

insert into halls(id, name, row, place) values
(11, 'Ряд 1, место 1', 1, 1), (12, 'Ряд 1, место 2', 1, 2), (13, 'Ряд 1, место 3', 1, 3),
(21, 'Ряд 2, место 1', 2, 1), (22, 'Ряд 2, место 2', 2, 2), (23, 'Ряд 2, место 3', 2, 3),
(31, 'Ряд 3, место 1', 3, 1), (32, 'Ряд 3, место 2', 3, 2), (33, 'Ряд 3, место 3', 3, 3)
ON CONFLICT DO NOTHING;

CREATE TABLE IF NOT EXISTS accounts(
    id serial primary key not null,
    fio varchar(250) not null,
    phone varchar(250) not null,
    place_id integer references halls(id),
    CONSTRAINT place_unique UNIQUE (place_id)
);
