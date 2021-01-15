USE [HbmrDb];
GO
CREATE PROCEDURE spDeleteTeam @team_id INT
AS
BEGIN
	DELETE FROM Team
	WHERE id = @team_id
END
GO