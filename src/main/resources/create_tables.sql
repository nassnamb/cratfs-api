--Table craftsman --
CREATE TABLE craftsman
(
    craftsman_id        SERIAL PRIMARY KEY,
    craftsman_name        varchar(80)   NOT NULL
);

--Table users --
CREATE TABLE users
(
	user_id         serial PRIMARY KEY,
    login        	varchar(50) UNIQUE NOT NULL,
    last_name     	varchar(50) NOT NULL,
    first_name  	varchar(50) NOT NULL,
	password     	varchar(50) NOT NULL,
    status       	varchar(50) NULL,
    creation_date	timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--Table profile --
CREATE TABLE profile
(
    profile varchar(50),
    description varchar(240) NOT NULL,
    CONSTRAINT profile_pkey PRIMARY KEY (profile)
);

--Table user_profile --
CREATE TABLE user_profile (
  user_login varchar(50) NOT NULL,
  profile varchar(50) NOT NULL,
  grant_date TIMESTAMP(6),
  PRIMARY KEY (user_login),
  FOREIGN KEY (profile) REFERENCES profile (profile),
  FOREIGN KEY (user_login) REFERENCES users (login)
);

--Table audit_lines --
CREATE TABLE AUDIT_LINES
(
    id  SERIAL PRIMARY KEY,
    login        	varchar(50) NOT NULL,
	eventTimeStamp	timestamp(6) NOT NULL,
    actionId     	varchar(50) NOT NULL,
    description  	varchar(250) NULL,
    objectJSON   	varchar(2048) NULL
);

-------------------------------------------
---new tables-----
-------------------------------------------

--Table Category --
DROP TABLE Category
CREATE TABLE Category (
                          category_id SERIAL PRIMARY KEY,
                          title VARCHAR(255) NOT NULL,
                          picture VARCHAR(255)
);


--Table Job --
DROP TABLE Job
CREATE TABLE Job (
                     job_id SERIAL PRIMARY KEY,
                     nomJob VARCHAR(255) NOT NULL,
                     category_id INT,
                     picture VARCHAR(255),
                     FOREIGN KEY (category_id) REFERENCES Category(category_id)
);

--Table Pays --
DROP TABLE Pays
CREATE TABLE Pays (
                      codePays CHAR(3) PRIMARY KEY,
                      nomPays VARCHAR(255) NOT NULL
);
--Table Communes --
DROP TABLE Communes
CREATE TABLE Communes (
                          codePays CHAR(3),
                          nomCommune VARCHAR(255) NOT NULL,
                          PRIMARY KEY (codePays, nomCommune),
                          FOREIGN KEY (codePays) REFERENCES Pays(codePays)
);

--Table Jobeur --
DROP TABLE Jobeur
CREATE TABLE Jobeur (
                        jobeur_id SERIAL PRIMARY KEY,
                        nom VARCHAR(255) NOT NULL,
                        prenom VARCHAR(255) NOT NULL,
                        pays_id CHAR(3),
                        job_id VARCHAR(255),
                        codePays CHAR(3),
                        nomCommune VARCHAR(255),
                        description TEXT,
                        photo VARCHAR(255),
                        FOREIGN KEY (pays_id) REFERENCES Pays(codePays),
                        FOREIGN KEY (job_id) REFERENCES Job(job_id),
                        FOREIGN KEY (codePays, nomCommune) REFERENCES Communes(codePays, nomCommune)
);

--Table JobeurService --
CREATE TABLE JobeurService (
                               jobeur_id INT,
                               service VARCHAR(255),
                               PRIMARY KEY (jobeur_id, service),
                               FOREIGN KEY (jobeur_id) REFERENCES Jobeur(jobeur_id)
);

