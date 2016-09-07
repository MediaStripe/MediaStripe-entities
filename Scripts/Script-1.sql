drop table if exists role;
drop table if exists acteur;
drop table if exists film;
drop table if exists genre;

create table genre(
	id serial not null,
	intitule varchar not null,
	constraint pk_genre primary key (id)
);

create table film(
	id serial not null,
	titre varchar not null,
	duree integer not null,
	genreprincipal integer,
	constraint pk_film primary key (id),
	constraint fk_film_genre foreign key (genreprincipal) references genre (id)
);

create table acteur(
	id serial not null,
	nom varchar not null, 
	prenom varchar not null,
	constraint pk_acteur primary key (id)
);

create table role(
	id_film integer,
	id_acteur integer,
	constraint pk_role primary key (id_film, id_acteur),
	constraint fk_role_film foreign key (id_film) references film (id),
	constraint fk_role_acteur foreign key (id_acteur) references acteur (id)
);