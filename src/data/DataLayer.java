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
			System.out.println("Loading JDBC Driver...");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("JDBC Driver loaded");
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println("Could not load JDBC Driver");
			return false;
		}
	}

	private boolean openConnection() {
		String connectionString = "jdbc:sqlserver://localhost:1433;" + "instanceName=SQLSERVER;" + "databaseName="
				+ "HbmrDb" + ";" + "integratedSecurity=true;";
		connection = null;
		try {
			System.out.println("Connecting to database...");
			connection = DriverManager.getConnection(connectionString);
			System.out.println("Connected to database");
			return true;
		} catch (SQLException e) {
			System.out.println("Could not connect to database!");
			System.out.println(e.getMessage());
			return false;
		}
	}
	String connectionString = "jdbc:sqlserver://localhost:1433;" + "instanceName=SQLSERVER;" + "databaseName="
			+ "HbmrDb" + ";" + "integratedSecurity=true;";

	/*
	 * Author: Lucas Elley Date: 12/01/2021
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
	
	public boolean deleteTeam(String name, int ligaId) {
		try {
			String sql = "{call spDeleteTeam(?, ?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setString(1, name);
				stmt.setInt(2, ligaId);
				stmt.execute();
			}
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
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
			System.out.println("fejl");
			return false;
		}
		
	}
	
	public ArrayList<Match> getAllMatches(int ligaId) {
		ArrayList<Match> matchList = new ArrayList<>();
		String sql = "{call spGetAllMatches(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1, ligaId);
			ResultSet matches = stmt.executeQuery();
			while(matches.next()) {
				int id = matches.getInt("id");
				int team1Id = matches.getInt("team1_id");
				int team2Id = matches.getInt("team2_id");
				int team1Goals = matches.getInt("team1_goals");
				int team2Goals = matches.getInt("team2_goals");
				
				matchList.add(new Match(id, team1Id, team2Id, team1Goals, team2Goals));
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldnt find any matches.");
		}
		return matchList;
	}

	/**
	 * @author $ Lasse Jonassen & Lucas Elley
	 * 
	 * @Created $ 12-01-2021
	 */
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
			System.out.println("Couldnt find any teams.");
		}
		return teamList;

	}
	
	
	
	/**
	 * @author $ Lasse Jonassen
	 * 
	 * @Created $ 11-01-2021
	 */
	public boolean deleteTeam(int teamId) {
		String sql = "{call spDeleteTeam(?)}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			stmt.setInt(1,  teamId);
			stmt.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @author $ Lasse Jonassen
	 * 
	 * @Created $ 11-01-2021
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
	
	/**
	 * @author $ Lucas Elley
	 * 
	 * @Created $ 14-01-2021
	 */
	
	public boolean deleteMatch(int ID) {
		try {
			String sql = "{call spDeleteMatch(?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, ID);
				stmt.execute();
			}
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean updateMatch(int team1ID, int team2ID) {
		try {
			String sql = "{call spUpdateMatch(?, ?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, team1ID);
				stmt.setInt(2, team2ID);
				stmt.execute();
			}
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/**
	 * @author $ Lasse Jonassen
	 * 
	 * @Created $ 11-01-2021
	 */
	public boolean createSuspension(int matchId, int teamId, String matchTime) {
		try {
			String sql = "{call spCreateSuspension(?, ? ,?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, matchId);
				stmt.setInt(2, teamId);
				stmt.setString(3, matchTime);
				stmt.execute();
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	/**
	 * @author $ Lasse Jonassen
	 * 
	 * @created $ 11-01-2021 - 13-01-2021
	 * 
	 * @tags $ CRUD On League
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
			System.out.println("Couldnt find any teams.");
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

	public ArrayList<Match> getAllMatchesTest() {
		String sql = "{call spGetMatches}";
		try (CallableStatement stmt = connection.prepareCall(sql)) {
			ResultSet resultSet = stmt.executeQuery();
			while (resultSet.next()) {
				String team1 = resultSet.getString("HOLDNAVN_H");
				String team2 = resultSet.getString("HOLDNAVN_U");
				int team1Goals = resultSet.getInt("MÅL_H");
				int team2Goals = resultSet.getInt("MÅL_U");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
