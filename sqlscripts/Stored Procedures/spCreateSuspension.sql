USE [HbmrDb];
GO
CREATE PROCEDURE spCreateSuspension @match_id INT, @team_id INT, @match_time INT
AS
BEGIN
	INSERT INTO Suspension
	VALUES (@match_id, @match_time, @team_id)
END
GO