USE handballmatchregDB;
GO
CREATE PROCEDURE spCreateLiga @ligaName nvarchar(20)
AS
BEGIN
	INSERT INTO Liga
	VALUES (@ligaName)
END