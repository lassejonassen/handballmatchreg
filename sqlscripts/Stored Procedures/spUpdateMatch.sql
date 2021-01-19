USE [HbmrDb];
GO
CREATE PROCEDURE spUpdateMatch @match_id INT, @team1Goals INT, @team2Goals INT
AS
BEGIN
	UPDATE [dbo].[TMatch]
	SET [TMatch].[team1_goals] = @team1Goals, [TMatch].[team2_goals] = @team2Goals
	WHERE [TMatch].[id] = @match_id
END
GO