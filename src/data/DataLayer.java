package data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;

public class DataLayer {
	private Connection connection;

	public DataLayer() {
		loadJDBCDriver();
		openConnection();
	}

	// Author: Lasse Jonassen
	// Created: 11-01-2021
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

	// Author: Lasse Jonassen
	// Created: 11-01-2021
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

	// Author: Lasse Jonassen
	// Created: 11-01-2021
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

	// Author: Lasse Jonassen & Lucas Elley
	// Created: 12-01-2021
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
				System.out.println(team);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldnt find any teams.");
		}
		return teamList;
		
	}

	// Author: Lasse Jonassen
	// Created: 11-01-2021
	public boolean createMatch(int team1ID, int team2ID) {
		try {
			String sql = "{call spCreateMatch(?, ?)}";
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

	// Author: Lasse Jonassen
	// Created: 11-01-2021
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
	
	// Author: Lasse Jonassen
	// Created: 12-01-2021
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
	
}
