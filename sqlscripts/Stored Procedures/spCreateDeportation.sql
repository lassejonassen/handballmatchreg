USE [HbmrDb];
GO
CREATE PROCEDURE spCreateSuspension @match_id INT, @team_id INT, @match_time nvarchar(10)
AS
BEGIN
	INSERT INTO Suspension
	VALUES (@match_id, @team_id, @match_time)
END
GO