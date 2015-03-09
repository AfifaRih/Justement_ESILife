
/*insert into UTILISATEUR
    (CLE_UTILISATEUR,esi_gmail_compte,motDePass,bloqued)
values 
    (cle_utilisateur.nextval,'mail','kkk',0);*/
    

/*create table Suivi
(
  cleSuivi integer not null,
  utilisateurSuiveur integer not null,
  utilisateurSuivi integer not null,
  CONSTRAINT suiveurFk FOREIGN KEY (utilisateurSuiveur) REFERENCES Utilisateur(cleUtilisateur),
  CONSTRAINT suiviFk FOREIGN KEY (utilisateurSuivi) REFERENCES Utilisateur(cleUtilisateur),
  CONSTRAINT uniqueSuiveurSuivi UNIQUE (utilisateurSuiveur, utilisateurSuivi),
  primary key (cleSuivi)
);

GRANT SELECT, INSERT, UPDATE, DELETE ON suivi TO esilifeuser;


Create sequence cleSuivi start with 1
increment by 1
minvalue 1;*/


/*******************************/
drop user esilifeuser;
CREATE USER esilifeuser
  IDENTIFIED BY esilifeuser
  DEFAULT TABLESPACE USERS
  TEMPORARY TABLESPACE TEMP
  PROFILE DEFAULT
  ACCOUNT UNLOCK;

create table Utilisateur
(
    utilisateur_cle  integer not null ,
    utilisateur_compte varchar(100) not null,
    utilisateur_mot_pass        varchar(100) not null ,
    utilisateur_bloque          integer default 0 not null,
    constraint booleanBloqued check (utilisateur_bloque= 0 or utilisateur_bloque=1),
    primary key (utilisateur_cle),
    constraint uniqueCompte unique(utilisateur_compte)
);

GRANT SELECT, INSERT, UPDATE, DELETE ON utilisateur TO esilifeuser;

Create sequence utilisateur_cle start with 1
increment by 1
minvalue 1;


create table etudiant_affilie
(
    utilisateur_cle  integer not null ,
    primary key (utilisateur_cle),
    etudiant_affilie_annee integer not null,
    etudiant_affilie_section integer not null,
    etudiant_affilie_specialite integer not null
    
);

GRANT SELECT, INSERT, UPDATE, DELETE ON etudiant_affilie TO esilifeuser;


create table moderateur
(
    utilisateur_cle  integer not null ,
    primary key (utilisateur_cle)    
);

GRANT SELECT, INSERT, UPDATE, DELETE ON moderateur TO esilifeuser;

create table administratif
(
    utilisateur_cle  integer not null ,
    primary key (utilisateur_cle)    
);

GRANT SELECT, INSERT, UPDATE, DELETE ON administratif TO esilifeuser;


create table enseignant
(
    utilisateur_cle  integer not null ,
    primary key (utilisateur_cle)    
);

GRANT SELECT, INSERT, UPDATE, DELETE ON enseignant TO esilifeuser;


create table contenu
(
    contenu_cle  integer not null ,
    contenu_date_publication date not null,
    contenu_date_modification date default null,
    constraint date_publication_modification 
      check(contenu_date_publication <= contenu_date_modification),
    primary key (contenu_cle)    
);

GRANT SELECT, INSERT, UPDATE, DELETE ON contenu TO esilifeuser;


