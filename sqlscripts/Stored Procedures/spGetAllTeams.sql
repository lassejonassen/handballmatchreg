USE [HbmrDb];
GO
CREATE PROCEDURE spGetAllTeams @league_id INT
AS
BEGIN
	SELECT * FROM Team WHERE league_id = @league_id
END
GO