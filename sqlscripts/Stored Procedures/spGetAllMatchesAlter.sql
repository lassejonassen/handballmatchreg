USE [handballmatchregDB]
GO
/****** Object:  StoredProcedure [dbo].[spGetAllMatches]    Script Date: 12-01-2021 11:16:55 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[spGetAllMatches]
AS
BEGIN
	SELECT * FROM TMatch
END
