----- Création de l'utilisateur cdpn07 -----
create user cdpn07 with password 'passe';

----- Création de la base de données -----
create database mediastripe owner cdpn07;

----- Création de la table "utilisateur" -----
create table utilisateur (
	id serial,
	nom varchar(100),
	prenom varchar(100),
	mail varchar(150),
	dateinscription date,
	derniereconnexion timestamp,
	motdepasse varchar(255),
	administrateur boolean,
	constraint pk_utilisateur primary key (id)
);

----- Création de la table "tag" -----
create table tag (
	id serial,
	libelle varchar(150),
	constraint pk_tag primary key (id)
);

----- Création de la table "media" -----
create table media (
	id serial,
	titre varchar(150),
	description varchar(255),
	datecreation timestamp,
	publique boolean,
	publieur integer,
	theme integer,
	constraint pk_media primary key (id),
	constraint fk_media_publieur foreign key (publieur) references utilisateur (id),
	constraint fk_media_theme foreign key (theme) references tag (id)
);

----- Création de la table "fichier" -----
create table fichier(
	id integer,
	cheminfichier varchar(255),
	constraint pk_fichier primary key (id)
);

----- Création de la table "photo" -----
create table photo(
	id integer,
	constraint pk_photo primary key (id)
);

----- Création de la table "musique" -----
create table musique(
	id integer,
	constraint pk_musique primary key (id)
);

----- Création de la table "video" -----
create table video(
	id integer,
	constraint pk_video primary key (id)
);

----- Création de la table "film" -----
create table film(
	id integer,
	realisateur varchar(255),
	constraint pk_film primary key (id)
);

----- Création de la table "episode" -----
create table episode(
	id integer,
	numero integer,
	serie varchar(255),
	constraint pk_episode primary key (id)
);

----- Création de la table "playlist" -----
create table playlist(
	id integer,
	nom varchar(255),
	constraint pk_playlist primary key (id)
);

----- Création de la table "galerie" -----
create table galerie(
	id integer,
	constraint pk_galerie primary key (id)
);

----- Création de la table "salle" -----
create table salle (
	id serial,
	nom varchar(150),
	createur integer,
	constraint pk_salle primary key (id),
	constraint fk_salle_createur foreign key (createur) references utilisateur (id)
);

----- Création de la table "contact" -----
create table contact (
	utilisateur1 integer,
	utilisateur2 integer,
	statut varchar(50),
	constraint pk_contact primary key (utilisateur1, utilisateur2),
	constraint fk_contact_utilisateur1 foreign key (utilisateur1) references utilisateur (id),
	constraint fk_contact_utilisateur2 foreign key (utilisateur2) references utilisateur (id)
);

----- Ajout de la règle check pour le statut du contact (demande | acceptée) -----
alter table contact add check (statut in ('en attente', 'valide'));

----- Création de la table "abonnement" -----
create table abonnement (
	publieur integer,
	abonne integer,
	constraint pk_abonnement primary key (publieur, abonne),
	constraint fk_abonnement_publieur foreign key (publieur) references utilisateur (id),
	constraint fk_abonnement_abonne foreign key (abonne) references utilisateur (id)
);

----- Création de la table "contenuplaylist" -----
create table contenuplaylist (
	idplaylist integer,
	idmedia integer,
	constraint pk_contenuplaylist primary key (idplaylist, idmedia),
	constraint fk_contenuplaylist_playlist foreign key (idplaylist) references playlist (id),
	constraint fk_contenuplaylist_media foreign key (idmedia) references media (id)
);

----- Création de la table "contenugalerie" -----
create table contenugalerie (
	galerie integer,
	photo integer,
	constraint pk_contenugalerie primary key (galerie, photo),
	constraint fk_contenugalerie_galerie foreign key (galerie) references galerie (id),
	constraint photo foreign key (photo) references photo (id)
);

----- Création de la table "associationtag"
create table associationtag (
	media integer,
	tag integer,
	constraint pk_associationtag primary key (media, tag),
	constraint fk_associationtag_media foreign key (media) references media (id),
	constraint fk_associationtag_tag foreign key (tag) references tag (id)
);