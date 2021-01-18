USE [HbmrDb];
GO
CREATE PROCEDURE spDeleteMatch @match_id INT
AS
BEGIN
	DELETE FROM TMatch
	WHERE id = @match_id
END
GO