USE master;

IF DB_ID('handballmatchregDB') IS NOT NULL
  DROP DATABASE handballmatchregDB;

CREATE DATABASE handballmatchregDB;
GO

USE handballmatchregDB;

CREATE TABLE Liga (
	id int IDENTITY(1,1) NOT NULL,
	liga_name varchar(20) NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE Team (
	id int IDENTITY (100, 1) NOT NULL,
	team_name nvarchar(50) NOT NULL,
	point int NOT NULL,
	liga int,

	PRIMARY KEY (id),
	FOREIGN KEY (liga) REFERENCES Liga(id) ON DELETE SET NULL
);

CREATE TABLE TMatch (
	id int IDENTITY (1000, 1) NOT NULL,
	team1_id INT NOT NULL,
	team2_id INT NOT NULL,
	score nvarchar(10),
	
	PRIMARY KEY (id)
);

CREATE TABLE Deportation (
	id INT IDENTITY (10000, 1) NOT NULL,
	tmatch INT NOT NULL,
	team INT NOT NULL,
	match_time nvarchar(10) NOT NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (tmatch) REFERENCES TMatch(id) ON DELETE CASCADE,
	FOREIGN KEY (team) REFERENCES Team(id) ON DELETE CASCADE
);




