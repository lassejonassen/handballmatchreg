USE [HbmrDb];
GO
CREATE PROCEDURE spDeleteSuspension @match_id INT, @team_id INT, @suspension_id INT
AS
BEGIN
	DELETE FROM Suspension
	WHERE id = @suspension_id
END
GO