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
    CONSTRAINT profile_pkey PRIMARY KEY (profile_id)
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

