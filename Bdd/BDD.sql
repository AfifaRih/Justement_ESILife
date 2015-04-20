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
drop table utilisateur_contenu_commenter;
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


create table contenu
(
	 "contenu_cle"  integer not null ,
    	 "contenu_date_publication" date not null,
    	 "contenu_date_modification" date default null,
    	 "contenu_text" varchar(1000) not null,
    	 "utilisateur_cle" integer not null,
    	 "contenu_binaire" bfile default null,
    	 "contenu_type" varchar(20) not null,
	 "contenu_accepter"  integer default 0 not null,
  	 constraint "date_publication_modification" 
      	 check("contenu_date_publication" <= "contenu_date_modification"),
	 constraint fk_PerOrders FOREIGN KEY ("utilisateur_cle")
	 REFERENCES utilisateur("utilisateur_cle"),
    	 primary key ("contenu_cle")    
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


create table utilisateur_contenu_commenter
(
  "commenter_cle" integer not null,
  "utilisateur_fk_cle" integer not null,
  "contenu_fk_cle" integer not null,
  "commenter_text" varchar(100) not null ,
  constraint fk_commenter_contenu     FOREIGN KEY ("contenu_fk_cle")           REFERENCES contenu("contenu_cle"),
  constraint fk_commenter_utilisateur FOREIGN KEY ("utilisateur_fk_cle") REFERENCES utilisateur("utilisateur_cle"),
  primary key("commenter_cle")
);
GRANT SELECT, INSERT, UPDATE, DELETE ON utilisateur_contenu_aimer TO esilifeuser;

Create sequence "commenter_cle" start with 1
increment by 1
minvalue 1;