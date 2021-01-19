USE [HbmrDb];
GO
CREATE PROCEDURE spGetMatchesByLeagueID @league_id INT
AS
BEGIN
	SELECT
	tm.id AS ID,
	team1.team_name AS HOLDNAVN_H,
	tm.team1_goals AS MÅL_H,
	tm.team2_goals AS MÅL_U,
	team2.team_name AS HOLDNAVN_U
	FROM TMatch AS tm
		JOIN Team as team1
			ON team1.id = tm.team1_id AND team1.league_id = @league_id
			JOIN Team as team2
				ON team2.id = tm.team2_id AND team2.league_id = @league_id
END
GO