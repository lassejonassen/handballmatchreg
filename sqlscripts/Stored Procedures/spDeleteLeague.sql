USE [HbmrDb];
GO
CREATE PROCEDURE spDeleteLeague @league_id INT
AS
BEGIN
	DELETE FROM League
	WHERE id = @league_id
END
GO