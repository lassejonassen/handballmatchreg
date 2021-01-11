USE [handballmatchregDB]
GO
/*
 * Author: Lasse Jonassen
 * Created: 2021_01_11
*/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
ALTER PROCEDURE [dbo].[spCreateDeportation] @matchid INT, @teamid INT, @time nvarchar(10)
AS
BEGIN
	INSERT INTO Deportation
	VALUES (@matchid, @teamid, @time)
END
