USE [HbmrDb]
GO
/****** Object:  StoredProcedure [dbo].[spDeleteMatch]    Script Date: 19-01-2021 10:08:18 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[spDeleteMatch] @match_id INT
AS
BEGIN
	DELETE FROM TMatch
	WHERE id = @match_id
END
