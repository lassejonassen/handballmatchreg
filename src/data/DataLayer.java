package data;

import java.sql.*;

public class DataLayer {
	private Connection connection;

	public DataLayer() {
		loadJDBCDriver();
		openConnection();
	}

	// Author: Lasse Jonassen
	// Created: 2020_01_11
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
	// Created: 2020_01_11
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
				stmt.setInt(2, ligaId);
				stmt.setString(1, name);
				stmt.execute();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Author: Lasse Jonassen
	// Created: 2020_01_11
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
}
