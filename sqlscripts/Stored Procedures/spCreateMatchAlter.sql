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
ALTER PROCEDURE [dbo].[spCreateMatch] @team1 INT, @team2 INT
AS
BEGIN
	INSERT INTO TMatch
	VALUES (@team1, @team2, '')
END
