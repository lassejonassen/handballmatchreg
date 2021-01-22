USE [HbmrDb];
GO
CREATE PROCEDURE spUpdatePlayedMatch @match_id INT, @played NVARCHAR(4)
AS
BEGIN
	UPDATE TMatch
	SET TMatch.played = @played
	WHERE TMatch.id = @match_id
END
GO