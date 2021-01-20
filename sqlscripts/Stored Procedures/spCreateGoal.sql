USE [HbmrDb];
GO
CREATE PROCEDURE spCreateGoal @match_id INT, @timestamp NVARCHAR(20), @team_id INT
AS
BEGIN
	INSERT INTO Goal
	VALUES (@match_id, @timestamp, @team_id)
END
GO