USE [HbmrDb];
GO
CREATE PROCEDURE spGetMatches
AS
BEGIN
	SELECT Team.team_name AS HOLDNAVN_H, TMatch.team1_goals AS MÅL_H
	FROM TMatch
	JOIN Team
		ON Team.id = TMatch.team1_id
	SELECT TMatch.team2_goals AS MÅL_U, Team.team_name AS HOLDNAVN_U
	FROM TMatch
	JOIN Team
		ON Team.id = TMatch.team2_id
END
GO