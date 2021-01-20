USE [HbmrDb];
GO
CREATE PROCEDURE spUpdateTeamScore 
@team_id INT, @total INT, @won INT,
@lost INT, @draw INT, @goals INT, @goals_in INT,
@points INT
AS
BEGIN
	UPDATE Team
	SET Team.matches_total = @total, Team.matches_won = @won,
	Team.matches_lost = @lost, Team.matches_draw = @draw,
	Team.goals = @goals,
	Team.goals_in = @goals_in,
	Team.points = @points
	WHERE Team.id = @team_id
END
GO

