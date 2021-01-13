USE [HbmrDb];
GO
CREATE PROCEDURE spUpdateLeague @league_id INT, @nleague_name NVARCHAR(20)
AS
BEGIN
	UPDATE League SET league_name = @nleague_name
	WHERE id = @league_id;
END
GO