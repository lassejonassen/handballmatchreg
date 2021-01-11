USE [handballmatchregDB]
GO
CREATE PROCEDURE spCreateMatch @team1 INT, @team2 INT
AS
BEGIN
	INSERT INTO TMatch
	VALUES (@team1, @team2, 'NOT STARTED')
END
GO
