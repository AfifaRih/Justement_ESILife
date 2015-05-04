drop user esilifeuser cascade;
CREATE USER esilifeuser
  IDENTIFIED BY esilifeuser
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;

drop table utilisateur;
drop table etudiant_affilie;
drop table moderateur;
drop table administratif;
drop table enseignant;
drop table contenu;
drop table utilisateur_contenu_aimer;
drop sequence "utilisateur_cle";
drop sequence "contenu_cle";
drop sequence "utilisateur_contenu_aimer_cle";

create table utilisateur
(
    "utilisateur_cle"  integer not null ,
    "utilisateur_compte" varchar(100) not null,
    "utilisateur_bloque"          integer default 0 not null,
    constraint booleanBloqued check ("utilisateur_bloque"= 0 or "utilisateur_bloque"=1),
    primary key ("utilisateur_cle"),
    constraint uniqueCompte unique("utilisateur_compte")
);

GRANT SELECT, INSERT, UPDATE, DELETE ON utilisateur TO esilifeuser;

Create sequence "utilisateur_cle" start with 1
increment by 1
minvalue 1;

Create sequence "contenu_cle" start with 1
increment by 1
minvalue 1;

create table etudiant_affilie
(
	"utilisateur_cle"  integer not null,
    	"etudiant_affilie_annee" integer not null,
    	"etudiant_affilie_section" integer not null,
    	"etudiant_affilie_specialite" integer not null,
	primary key ("utilisateur_cle")
);

GRANT SELECT, INSERT, UPDATE, DELETE ON etudiant_affilie TO esilifeuser;


create table moderateur
(
	"utilisateur_cle"  integer not null ,
 	primary key ("utilisateur_cle")
);

GRANT SELECT, INSERT, UPDATE, DELETE ON moderateur TO esilifeuser;

create table administratif
(
	"utilisateur_cle"  integer not null ,
	 primary key ("utilisateur_cle")
);

GRANT SELECT, INSERT, UPDATE, DELETE ON administratif TO esilifeuser;


create table enseignant
(
	"utilisateur_cle"  integer not null ,
    	primary key ("utilisateur_cle")
);

GRANT SELECT, INSERT, UPDATE, DELETE ON enseignant TO esilifeuser;


CREATE TABLE  "CONTENU"
   (	"contenu_cle" NUMBER(*,0) NOT NULL DISABLE,
	"contenu_date_publication" VARCHAR2(25),
	"contenu_date_modification" VARCHAR2(25) DEFAULT null,
	"contenu_text" VARCHAR2(1000) NOT NULL DISABLE,
	"utilisateur_cle" NUMBER(*,0) NOT NULL DISABLE,
	"contenu_type" VARCHAR2(20) NOT NULL DISABLE,
	"contenu_accepter" NUMBER(*,0) DEFAULT 0 NOT NULL DISABLE,
	"contenu_binaire" BLOB,
	 PRIMARY KEY ("contenu_cle") ENABLE,
	 CONSTRAINT "FK_PERORDERS" FOREIGN KEY ("utilisateur_cle")
	  REFERENCES  "UTILISATEUR" ("utilisateur_cle") ENABLE
  );

GRANT SELECT, INSERT, UPDATE, DELETE ON contenu TO esilifeuser;

Create sequence "contenu_cle" start with 1
increment by 1
minvalue 1;

create table utilisateur_contenu_aimer
(
	"utilisateur_contenu_aimer_cle" integer not null,
	"utilisateur_fk_cle" integer not null,
	"contenu_fk_cle" integer not null,
	constraint fk_PerOrders2 FOREIGN KEY ("utilisateur_fk_cle")
	REFERENCES utilisateur("utilisateur_cle"),
	constraint fk_PerOrders3 FOREIGN KEY ("contenu_fk_cle")
	REFERENCES contenu("contenu_cle"),
	primary key ("utilisateur_contenu_aimer_cle")
);

GRANT SELECT, INSERT, UPDATE, DELETE ON utilisateur_contenu_aimer TO esilifeuser;

Create sequence "utilisateur_contenu_aimer_cle" start with 1
increment by 1
minvalue 1;
