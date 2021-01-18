USE [HbmrDb];
GO
CREATE PROCEDURE spGetMatchesForTable @league_id
AS
BEGIN
	SELECT Team.team_name, TMatch.team1_goals, Team.team_name, TMatch.team2_goals
	
END
GO