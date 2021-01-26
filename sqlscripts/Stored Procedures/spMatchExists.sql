USE [HbmrDb];
GO
CREATE PROCEDURE spMatchExists @match_id INT
AS
BEGIN
	SELECT * FROM TMatch tm WHERE tm.id = @match_id;
END
GO