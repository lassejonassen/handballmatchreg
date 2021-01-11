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
ALTER PROCEDURE [dbo].[spCreateTeam] @name nvarchar(50), @liga INT
AS
BEGIN
	INSERT INTO Team
	VALUES (@name, 0, @liga)
END
