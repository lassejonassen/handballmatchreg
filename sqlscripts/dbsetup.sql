USE [master];

IF DB_ID('HbmrDb') IS NOT NULL
  DROP DATABASE HbmrDb;

CREATE DATABASE HbmrDb;
GO

USE [HbmrDb]

CREATE TABLE League (
	id int IDENTITY(1,1) NOT NULL,
	league_name varchar(20) NOT NULL,

	PRIMARY KEY (id),
);

CREATE TABLE Team (
	id int IDENTITY (100, 1) NOT NULL,
	team_name nvarchar(50) NOT NULL,
	matches_total INT NOT NULL,
	matches_won INT NOT NULL,
	matches_lost INT NULL,
	matches_draw INT NOT NULL,
	goals INT NOT NULL,
	goals_in INT NOT NULL,
	points INT NOT NULL,
	league_id INT NOT NULL,

	PRIMARY KEY (id),

	CONSTRAINT Team_League_Relation FOREIGN KEY (league_id) 
	REFERENCES League(id)
);

CREATE TABLE TMatch (
	id int IDENTITY (1000, 1) NOT NULL,
	team1_id INT NOT NULL,
	team2_id INT NOT NULL,
	team1_goals INT NOT NULL,
	team2_goals INT NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE Suspension (
	id INT IDENTITY (10000, 1) NOT NULL,
	match_id INT NOT NULL,
	match_time nvarchar(10) NOT NULL,
	team_id INT NOT NULL,
	
	PRIMARY KEY (id),
	CONSTRAINT Suspension_Match_Relation FOREIGN KEY (match_id) 
	REFERENCES TMatch(id) ON DELETE CASCADE,
	CONSTRAINT Suspension_Team_Relation FOREIGN KEY (team_id) 
	REFERENCES Team(id) ON DELETE CASCADE
);




