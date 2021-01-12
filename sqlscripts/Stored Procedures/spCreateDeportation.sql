USE [handballmatchregDB]
GO
CREATE PROCEDURE spCreateDeportation @matchid INT, @teamid INT, @time nvarchar(10)
AS
BEGIN
	INSERT INTO Deportation
	VALUES (@matchid, @teamid, @time)
END
GO