USE [HbmrDb];
GO
CREATE PROCEDURE spDeleteGoal @match_id INT, @team_id INT, @goal_id INT
AS
BEGIN
	DECLARE @id INT;
	SET @id = (SELECT Max(id) FROM Goal 
				WHERE Goal.match_id = @match_id 
				AND Goal.team_id = @team_id
				);
	DELETE FROM Goal WHERE id = @id;
END
GO