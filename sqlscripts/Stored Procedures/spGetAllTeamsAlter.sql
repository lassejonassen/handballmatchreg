USE [handballmatchregDB]
GO
/****** Object:  StoredProcedure [dbo].[spGetAllTeams]    Script Date: 12-01-2021 11:14:54 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[spGetAllTeams]
AS
BEGIN
	SELECT * FROM Team
END
