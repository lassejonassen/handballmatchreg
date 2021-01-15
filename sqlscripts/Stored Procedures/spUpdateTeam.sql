USE [HbmrDb];
GO
CREATE PROCEDURE spUpdateTeam @team_id INT, @nTeam_name NVARCHAR(20), @league_id INT
AS
BEGIN
	UPDATE Team SET team_name = @nTeam_name, league_id = @league_id
	WHERE id = @team_id
END
GO