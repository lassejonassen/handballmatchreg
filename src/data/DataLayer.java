package data;

import java.sql.*;
import java.util.ArrayList;

public class DataLayer {
	private Connection connection;

	public DataLayer() {
		loadJDBCDriver();
		openConnection();
	}

	private boolean loadJDBCDriver() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}

	private boolean openConnection() {
		String connectionString = "jdbc:sqlserver://localhost:1433;" + "instanceName=SQLSERVER;" + "databaseName="
				+ "HbmrDb" + ";" + "integratedSecurity=true;";
		connection = null;
		try {
			connection = DriverManager.getConnection(connectionString);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * @About League
	 * @tags {Create, Read, Update, Delete}
	 * @author Lasse Jonassen
	 * @created 11/01/2021 - 13/01/2021
	 */
	public boolean createLeague(String leagueName) {
		try {
			String sql = "{call spCreateLeague(?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setString(1, leagueName);
				stmt.execute();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<League> getAllLeagues() {
		ArrayList<League> leagueList = new ArrayList<League>();
		String sql = "{call spGetAllLeagues}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			ResultSet leagues = stmt.executeQuery();
			while (leagues.next()) {
				int id = leagues.getInt("id");
				String name = leagues.getString("league_name");
				League league = new League(id, name);
				leagueList.add(league);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return leagueList;
	}

	public League getLeagueById(int id) {
		ArrayList<League> leagues = new ArrayList<League>();
		String sql = "{call spGetLeagueById(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, id);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("league_name");
				leagues.add(new League(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return leagues.get(0);
	}

	public boolean updateLeague(int id, String newName) {
		try {
			String sql = "{call spUpdateLeague(?, ?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, id);
				stmt.setString(2, newName);
				stmt.execute();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean deleteLeague(int id) {
		String sql = "{call spDeleteLeague(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, id);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @About Team
	 * @tags {Create, Read, Update, Delete}
	 * @author Lucas Elley & Lasse Jonassen
	 * @created 11/01/2021 - 13/01/2021
	 */
	public boolean createTeam(String name, int ligaId) {
		try {
			String sql = "{call spCreateTeam(?,?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setString(1, name);
				stmt.setInt(2, ligaId);
				stmt.execute();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<Team> getAllTeams(int ligaId) {
		ArrayList<Team> teamList = new ArrayList<>();
		String sql = "{call spGetAllTeams(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, ligaId);
			ResultSet teams = stmt.executeQuery();
			while (teams.next()) {
				Team team = new Team();
				int id = teams.getInt("id");
				String name = teams.getString("team_name");
				int matchesTotal = teams.getInt("matches_total");
				int matchesWon = teams.getInt("matches_won");
				int matchesLost = teams.getInt("matches_lost");
				int matchesDraw = teams.getInt("matches_draw");
				int goals = teams.getInt("goals");
				int goalsIn = teams.getInt("goals_in");
				int points = teams.getInt("points");
				int leagueId = teams.getInt("league_id");
				team.setId(id);
				team.setName(name);
				team.setMatchesTotal(matchesTotal);
				team.setMatchesWon(matchesWon);
				team.setMatchesLost(matchesLost);
				team.setMatchesDraw(matchesDraw);
				team.setGoals(goals);
				team.setGoalsIn(goalsIn);
				team.setPoints(points);
				team.setLeagueId(leagueId);
				teamList.add(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return teamList;
	}

	public Team getOneTeam(int teamId) {
		ArrayList<Team> teamList = new ArrayList<Team>();
		String sql = "{call spGetOneTeam(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, teamId);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Team team = new Team();
				int id = resultSet.getInt("id");
				String name = resultSet.getString("team_name");
				int matchesTotal = resultSet.getInt("matches_total");
				int matchesWon = resultSet.getInt("matches_won");
				int matchesLost = resultSet.getInt("matches_lost");
				int matchesDraw = resultSet.getInt("matches_draw");
				int goals = resultSet.getInt("goals");
				int goalsIn = resultSet.getInt("goals_in");
				int points = resultSet.getInt("points");
				int leagueId = resultSet.getInt("league_id");
				team.setId(id);
				team.setName(name);
				team.setMatchesTotal(matchesTotal);
				team.setMatchesWon(matchesWon);
				team.setMatchesLost(matchesLost);
				team.setMatchesDraw(matchesDraw);
				team.setGoals(goals);
				team.setGoalsIn(goalsIn);
				team.setPoints(points);
				team.setLeagueId(leagueId);
				teamList.add(team);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return teamList.get(0);
	}

	public boolean updateTeam(int id, String newName, int idOther) {
		try {
			String sql = "{call spUpdateTeam(?, ?, ?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, id);
				stmt.setString(2, newName);
				stmt.setInt(3, idOther);
				stmt.execute();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateTeamScore(Team team) {
		String sql = "{call spUpdateTeamScore(?, ?, ?, ?, ?, ?, ?, ?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, team.getId());
			stmt.setInt(2, team.getMatchesTotal());
			stmt.setInt(3, team.getMatchesWon());
			stmt.setInt(4, team.getMatchesLost());
			stmt.setInt(5, team.getMatchesDraw());
			stmt.setInt(6, team.getGoals());
			stmt.setInt(7, team.getGoalsIn());
			stmt.setInt(8, team.getPoints());
			stmt.execute();
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public boolean deleteTeam(int teamId) {
		String sql = "{call spDeleteTeam(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, teamId);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @About Match
	 * @tags {Create, Read, Update, Delete}
	 * @author Lucas Elley & Lasse Jonassen
	 * @created 15/01/2021
	 */
	public boolean createMatch(int team1ID, int team2ID, int leagueID) {
		try {
			String sql = "{call spCreateMatch(?, ?, ?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, team1ID);
				stmt.setInt(2, team2ID);
				stmt.setInt(3, leagueID);
				stmt.execute();
			}
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	public ArrayList<Match> getAllMatchesByLeagueID(int leagueID) {
		ArrayList<Match> matchList = new ArrayList<>();
		String sql = "{call spGetMatchesByLeagueID(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, leagueID);
			ResultSet matches = stmt.executeQuery();
			while (matches.next()) {
				int id = matches.getInt("ID");
				String team1Name = matches.getString("HOLDNAVN_H");
				int team1Goals = matches.getInt("MAAL_H");
				int team2Goals = matches.getInt("MAAL_U");
				String team2Name = matches.getString("HOLDNAVN_U");
				int team1Id = matches.getInt("TEAM1ID");
				int team2Id = matches.getInt("TEAM2ID");
				String played = matches.getString("PLAYED");
				int leagueId = matches.getInt("LIGAID");
				matchList.add(new Match(id, team1Goals, team2Goals, team1Name, team2Name, team1Id, team2Id, played, leagueId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matchList;
	}
	
	public boolean matchExists(int matchId) {
		String sql = "{call spMatchExists(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, matchId);
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Match latestInsert() {
		ArrayList<Match> matchList = new ArrayList<Match>();
		String sql = "{call spGetLatestMatch}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				Match match = new Match();
				int id = resultSet.getInt("id");
				int team1Id = resultSet.getInt("team1_id");
				int team2Id = resultSet.getInt("team2_id");
				int team1Goals = resultSet.getInt("team1_goals");
				int team2Goals = resultSet.getInt("team2_goals");
				String played = resultSet.getString("played");
				match.setMatchID(id);
				match.setTeam1Id(team1Id);
				match.setTeam2Id(team2Id);
				match.setTeam1Goals(team1Goals);
				match.setTeam2Goals(team2Goals);
				match.setPlayed(played);
				matchList.add(match);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return matchList.get(0);
	}

	public boolean updateMatch(int matchID, int team1Goals, int team2Goals) {
		try {
			String sql = "{call spUpdateMatch(?, ?, ?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, matchID);
				stmt.setInt(2, team1Goals);
				stmt.setInt(3, team2Goals);
				stmt.execute();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteMatch(int ID) {
		try {
			String sql = "{call spDeleteMatch(?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, ID);
				stmt.execute();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateMatch(Match match) {
		String sql = "{call spUpdatePlayedMatch(?, ?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, match.getMatchID());
			stmt.setString(2, match.getPlayed());
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @About Suspension
	 * @tags {Create, delete}
	 * @author
	 * @created 15/01/2021
	 */
	public boolean createSuspension(int matchId, int teamId, int matchTime) {
		try {
			String sql = "{call spCreateSuspension(?, ? ,?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, matchId);
				stmt.setInt(2, teamId);
				stmt.setInt(3, matchTime);
				stmt.execute();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteSuspension(int matchId, int teamId, int suspensionID) {
		String sql = "{call spDeleteSuspension(?, ? ,?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, matchId);
			stmt.setInt(2, teamId);
			stmt.setInt(3, suspensionID);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @About Goal
	 * @tags {Create}
	 * @author
	 * @created 20/01/2021
	 */
	public boolean createGoal(int matchId, int timeStamp, int teamId) {
		String sql = "{call spCreateGoal(?, ?, ?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, matchId);
			stmt.setInt(2, timeStamp);
			stmt.setInt(3, teamId);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteGoal(int matchId, int timeStamp, int goalId) {
		String sql = "{call spDeleteGoal(?, ?, ?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, matchId);
			stmt.setInt(2, timeStamp);
			stmt.setInt(3, goalId);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @About Goal and Suspension
	 * @tags {Read}
	 * @author
	 * @created 21/01/2021
	 */
	public ArrayList<ReportDTO> getGoalsAndSuspensions(int matchId) {
		String sql = "{call spGetGoalsAndSuspensions(?)}";
		ArrayList<ReportDTO> list = new ArrayList<ReportDTO>();
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, matchId);
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int mId = resultSet.getInt("match_id");
				int timeStamp = resultSet.getInt("match_time");
				int teamId = resultSet.getInt("team_id");
				if ((id > 9999) & (id < 99999))
					list.add(new ReportDTO(new Suspension(id, mId, timeStamp, teamId)));
				else if ((id > 99999) & (id < 999999))
					list.add(new ReportDTO(new Goal(id, mId, timeStamp, teamId)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

}
