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
ALTER PROCEDURE [dbo].[spCreateLiga] @ligaName nvarchar(20)
AS
BEGIN
	INSERT INTO Liga
	VALUES (@ligaName)
END
