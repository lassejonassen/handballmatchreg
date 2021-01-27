USE [HbmrDb];
GO
CREATE PROCEDURE spDeleteSuspension @match_id INT, @team_id INT
AS
BEGIN
	DECLARE @id INT;
	SET @id = (SELECT IDENT_CURRENT('Suspension') FROM Suspension WHERE @match_id = Suspension.match_id AND @team_id = Suspension.team_id);
	DELETE FROM Suspension WHERE Suspension.id = @id;
END
GO