USE [HbmrDb];
GO
CREATE PROCEDURE spGetMatchesByLeagueID @league_id INT
AS
BEGIN
	SELECT
	tm.id AS ID,
	team1.team_name AS HOLDNAVN_H,
	tm.team1_goals AS MAAL_H,
	tm.team2_goals AS MAAL_U,
	team2.team_name AS HOLDNAVN_U,
	tm.team1_id AS TEAM1ID,
	tm.team2_id AS TEAM2ID,
	tm.played AS PLAYED
	FROM TMatch AS tm
		JOIN Team as team1
			ON team1.id = tm.team1_id AND team1.league_id = @league_id
			JOIN Team as team2
				ON team2.id = tm.team2_id AND team2.league_id = @league_id
END
GO