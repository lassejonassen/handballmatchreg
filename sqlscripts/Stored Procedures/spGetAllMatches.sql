USE [handballmatchregDB];
GO
CREATE PROCEDURE spGetAllMatches
AS
BEGIN
	SELECT * FROM TMatch
END
GO
