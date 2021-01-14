USE [HbmrDb];
GO
CREATE PROCEDURE spCreateMatch @team1_id INT, @team2_id INT
AS
BEGIN
	INSERT INTO TMatch
	VALUES (@team1_id, @team2_id, 0, 0, 0)
END
GO
