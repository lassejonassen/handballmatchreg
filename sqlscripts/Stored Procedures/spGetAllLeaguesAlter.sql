USE [handballmatchregDB]
GO
/****** Object:  StoredProcedure [dbo].[spGetAllLeagues]    Script Date: 12-01-2021 11:13:02 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[spGetAllLeagues]
AS
BEGIN
	SELECT * FROM Liga
END
