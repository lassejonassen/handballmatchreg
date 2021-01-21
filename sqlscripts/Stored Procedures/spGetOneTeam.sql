USE [HbmrDb];
GO
CREATE PROCEDURE spGetOneTeam @team_id INT
AS
BEGIN
	SELECT * FROM Team WHERE Team.id =  @team_id
END
GO