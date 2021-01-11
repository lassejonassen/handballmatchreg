USE handballmatchregDB;
GO
CREATE PROCEDURE spCreateTeam @name nvarchar(50), @liga INT
AS
BEGIN
	INSERT INTO Team
	VALUES (@name, 0, @liga)
END
GO