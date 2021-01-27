USE [HbmrDb];
GO
CREATE PROCEDURE spDeleteSuspension @match_id INT, @team_id INT
AS
BEGIN
	DECLARE @id INT;
	SET @id = (SELECT Max(id) FROM Suspension 
				WHERE Suspension.match_id = 1038 
				AND Suspension.team_id = 105
				);
	DELETE FROM Suspension WHERE id = @id;
END
GO