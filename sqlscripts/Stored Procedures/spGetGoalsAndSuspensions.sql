USE [HbmrDb];
GO
CREATE PROCEDURE spGetGoalsAndSuspensions @match_id INT
AS
BEGIN
	SELECT * FROM Goal AS g WHERE g.match_id = 1001
	UNION
	SELECT * FROM Suspension AS s WHERE s.match_id = 1001
	ORDER BY match_time
END
GO