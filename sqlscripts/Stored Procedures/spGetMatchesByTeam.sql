USE [HbmrDb];
GO
CREATE PROCEDURE spGetMatchesByTeam @team_id INT
AS
BEGIN
	SELECT * FROM TMatch
	WHERE team1_id = @team_id or team2_id = @team_id
END
GO