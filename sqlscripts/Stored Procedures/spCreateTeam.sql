USE [HbmrDb];
GO
CREATE PROCEDURE spCreateTeam @team_name nvarchar(50), @liga_id INT
AS
BEGIN
	INSERT INTO Team
	VALUES (@team_name, 0, @liga_id)
END
GO