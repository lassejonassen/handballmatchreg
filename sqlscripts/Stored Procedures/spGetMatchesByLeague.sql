USE [HbmrDb];
GO
CREATE PROCEDURE spGetMatchesByLeague @league_id INT
AS
BEGIN
	SELECT * FROM TMatch tm, Team t
	WHERE tm.team1_id = t.id or tm.team2_id = t.id
	AND t.league_id = @league_id
END
GO