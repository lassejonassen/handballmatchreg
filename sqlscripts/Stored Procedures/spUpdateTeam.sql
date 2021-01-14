USE [HbmrDb];
GO
CREATE PROCEDURE spUpdateTeam @team_id INT, @nTeam_name NVARCHAR(20)
AS
BEGIN
	UPDATE Team SET team_name = @nTeam_name
	WHERE id = @team_id;
END
GO