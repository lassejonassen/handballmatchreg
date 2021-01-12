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
				+ "handballmatchregDB" + ";" + "integratedSecurity=true;";
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
	// Created: 2020_01_11
	public boolean createLiga(String ligaName) {
		try {
			String sql = "{call spCreateLiga(?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setString(1, ligaName);
				stmt.execute();
				return true;
			}
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

	public ArrayList<Team> getAllTeams(int ligaId) {
		ArrayList<Team> teamList = new ArrayList<>();
		try {
			String sql = "{call spGetAllTeams(?)}";
			try (CallableStatement stmt = connection.prepareCall(sql)) {
				stmt.setInt(1, ligaId);
				ResultSet teams = stmt.executeQuery();
				while(teams.next()) {
					Team team = new Team();
					int teamId = teams.getInt("id");
					String name = teams.getString("team_name");
					int point = teams.getInt("point");
					team.setId(teamId);
					team.setName(name);
					team.setPoint(point);
					teamList.add(team);
				}
			}
			return teamList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Couldnt find any teams.");
			return teamList;
		}
	}
}
