USE [HbmrDb];
GO
CREATE PROCEDURE spGetGoalsAndSuspensions @match_id INT
AS
BEGIN
	SELECT * FROM Goal AS g 
	WHERE g.match_id = @match_id
	UNION
	SELECT * FROM Suspension 
	AS s WHERE s.match_id = @match_id
	ORDER BY match_time
END
GO