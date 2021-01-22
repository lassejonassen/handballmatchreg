USE [HbmrDb];
GO
CREATE PROCEDURE spDeleteGoal @match_id INT, @team_id INT, @goal_id INT
AS
BEGIN
	DELETE FROM Goal
	WHERE id = @goal_id
END
GO