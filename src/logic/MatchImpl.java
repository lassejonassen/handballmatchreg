package logic;


import java.util.ArrayList;

import data.DataLayer;
import data.League;
import data.Match;
import data.Team;

public class MatchImpl implements iMatch {
	DataLayer dataLayer = new DataLayer();
	
	public void createMatch(Team team, Team team2, int leagueID) {
		dataLayer.createMatch(team.getId(), team2.getId(), leagueID);
	}
	
	public ArrayList<Match> getMatchesByLeagueID(League league) {
		return dataLayer.getAllMatchesByLeagueID(league.getId());
	}
	
	public void updateMatch(Match match) {
		dataLayer.updateMatch(match.getMatchID(), match.getTeam1Goals(), match.getTeam2Goals());
		Team team = dataLayer.getOneTeam(match.getTeam1Id());
		Team team2 = dataLayer.getOneTeam(match.getTeam2Id());
		team.setMatchesTotal(team.getMatchesTotal() + 1);
		team2.setMatchesTotal(team2.getMatchesTotal() + 1);
		team.setGoals(match.getTeam1Goals());
		team.setGoalsIn(match.getTeam2Goals());
		team2.setGoals(match.getTeam2Goals());
		team2.setGoalsIn(match.getTeam1Goals());
		
		if (match.getTeam1Goals() >  match.getTeam2Goals()) {
			team.setMatchesWon(team.getMatchesWon() + 1);
			team2.setMatchesLost(team2.getMatchesLost() + 1);
			team.setPoints(team.getPoints() + 2);
			team2.setPoints(team2.getPoints() + 0);
		} else if (match.getTeam1Goals() <  match.getTeam2Goals()) {
			team.setMatchesLost(team.getMatchesLost() + 1);
			team2.setMatchesWon(team2.getMatchesWon() + 1);
			team.setPoints(team.getPoints() + 0);
			team2.setPoints(team2.getPoints() + 2);
		} else if (match.getTeam1Goals() == match.getTeam2Goals()) {
			team.setMatchesDraw(team.getMatchesDraw() +1);
			team2.setMatchesDraw(team2.getMatchesDraw() +1);
			team.setPoints(team.getPoints() + 1);
			team2.setPoints(team2.getPoints() + 1);
		}
		dataLayer.updateTeamScore(team);
		dataLayer.updateTeamScore(team2);
	}
	
	public void deleteMatch(Match match) {
		dataLayer.deleteMatch(match.getMatchID());
	}
	
	public void matchPlayed(Match match) {
		match.setPlayed("yes");
		dataLayer.updateMatch(match);
	}
}
