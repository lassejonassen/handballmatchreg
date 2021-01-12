USE [HbmrDb];
GO
CREATE PROCEDURE spCreateLeague @league_name nvarchar(20)
AS
BEGIN
	INSERT INTO League
	VALUES (@league_name)
END
GO