package com.example;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;




/**
 * Provides CRUD operations for managing college hoops data.
 * Uses JDBC with prepared statements to interact with a MySQL database.
 *
 * @author Brendan Lambrecht and Leroy Ombogo
 * @since 03-12-2025
 */
public class CompanyDBCrud {

    private static final String URL = "jdbc:mysql://138.49.184.47:3306/lambrecht5083_CollegeHoopsDB";
    private static final String USER = "lambrecht5083";
    private static final String PASSWORD = "Z86vs3&gqjc!XREe";

    /**
     * Establishes a database connection.
     * @return Connection object.
     * @throws SQLException if a database error occurs.
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found: " + e.getMessage());
        }
    }

    /**
     * Retrieves all teams from the database and returns them as an ObservableList of Team objects.
     *
     * @return An ObservableList of Team objects.
     * @throws SQLException If a database error occurs.
     */
    public static ObservableList<Team> getAllTeams() throws SQLException {
        ObservableList<Team> teams = FXCollections.observableArrayList();
        String sql = "SELECT TeamID, Name, Region FROM Team";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int teamId = rs.getInt("TeamID");
                String name = rs.getString("Name");
                String region = rs.getString("Region");
                Team team = new Team(teamId, name, region);
                teams.add(team);
            }
        }
        return teams;
    }

    /**
     * Retrieves all players from the database and returns them as an ObservableList of Player objects.
     *
     * @return An ObservableList of Player objects.
     * @throws SQLException If a database error occurs.
     */
    public static ObservableList<Player> getAllPlayers() throws SQLException {
        ObservableList<Player> players = FXCollections.observableArrayList();
        String sql = "SELECT PlayerID, TeamID, Name, Position, Height FROM Player";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int playerId = rs.getInt("PlayerID");
                int teamId = rs.getInt("TeamID");
                String name = rs.getString("Name");
                String position = rs.getString("Position");
                double height = rs.getDouble("Height");
                Player player = new Player(playerId, teamId, name, position, height);
                players.add(player);
            }
        }
        return players;
    }

    /**
     * Retrieves all games from the database and returns them as an ObservableList of Game objects.
     *
     * @return An ObservableList of Game objects.
     * @throws SQLException If a database error occurs.
     */
    public static ObservableList<Game> getAllGames() throws SQLException {
        ObservableList<Game> games = FXCollections.observableArrayList();
        String sql = "SELECT GameID, Round, ScheduleID, Team1ID, Team2ID, BracketID FROM lambrecht5083_CollegeHoopsDB.Game";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
         try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int gameId = rs.getInt("GameID");
                String round = rs.getString("Round");
                int scheduleId = rs.getInt("ScheduleID");
                int team1Id = rs.getInt("Team1ID");
                int team2Id = rs.getInt("Team2ID");
                int bracketID = rs.getInt("BracketID"); //add
                Game game = new Game(gameId, round, scheduleId, team1Id, team2Id, bracketID); //add bracketID to constructor
                games.add(game);
            }
        } finally {
            // Close resources in a finally block to ensure they are always closed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return games;
    }

    /**
     * Retrieves all brackets from the database and returns them as an ObservableList of Bracket objects.
     *
     * @return An ObservableList of Bracket objects.
     * @throws SQLException If a database error occurs.
     */
    public static ObservableList<Bracket> getAllBrackets() throws SQLException {
        ObservableList<Bracket> brackets = FXCollections.observableArrayList();
        String sql = "SELECT BracketID, Name, Champion FROM Bracket";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int bracketId = rs.getInt("BracketID");
                String bracketName = rs.getString("Name");
                int Champion = rs.getInt("Champion");
                Bracket bracket = new Bracket(bracketId, bracketName, Champion);
                brackets.add(bracket);
            }
        }
        return brackets;
    }

    /**
     * Retrieves all schedules from the database and returns them as an ObservableList of Schedule objects.
     *
     * @return An ObservableList of Schedule objects.
     * @throws SQLException If a database error occurs.
     */
    public static ObservableList<Schedule> getAllSchedules() throws SQLException {
        ObservableList<Schedule> schedules = FXCollections.observableArrayList();
        String sql = "SELECT ScheduleID, Tickets, Broadcasting, Date, Location FROM Schedule";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int scheduleId = rs.getInt("ScheduleID");
                String tickets = rs.getString("Tickets");
                String broadcasting = rs.getString("Broadcasting");
                Date date = rs.getDate("Date");
                String location = rs.getString("Location");
                Schedule schedule = new Schedule(scheduleId, tickets, broadcasting, date, location);
                schedules.add(schedule);
            }
        }
        return schedules;
    }

    /**
     * Retrieves all player game stats from the database and returns them as an ObservableList of PlayerGameStat objects.
     *
     * @return An ObservableList of PlayerGameStat objects.
     * @throws SQLException If a database error occurs.
     */
    public static ObservableList<PlayerGameStat> getAllPlayerGameStats() throws SQLException {
        ObservableList<PlayerGameStat> stats = FXCollections.observableArrayList();
        String sql = "SELECT PlayerID, GameID, TeamID, STL, BLK, MIN, FG_Percentage, "
                   + "3P_Percentage, FT_Percentage, REB, PF, AST, Turnover, PTS "
                   + "FROM lambrecht5083_CollegeHoopsDB.PlayerGameStats";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                PlayerGameStat stat = new PlayerGameStat(
                    rs.getInt("PlayerID"),
                    rs.getInt("GameID"),
                    rs.getInt("TeamID"),
                    rs.getInt("STL"),
                    rs.getInt("BLK"),
                    rs.getInt("MIN"),
                    rs.getDouble("FG_Percentage"),
                    rs.getDouble("3P_Percentage"),
                    rs.getDouble("FT_Percentage"),
                    rs.getInt("REB"),
                    rs.getInt("PF"),
                    rs.getInt("AST"),
                    rs.getInt("Turnover"),
                    rs.getInt("PTS")
                );
                stats.add(stat);
            }
        } finally {
            // Close resources in a finally block to ensure they are always closed
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
        return stats;
    }
    
    /**
     * UI method to add a new team.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    public static void addTeam(Connection conn, String name, String region) throws SQLException {
        String sql = "INSERT INTO Team (Name, Region) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, region);
            stmt.executeUpdate();
        }
    }

    public static void addGame(Connection conn, String round, int scheduleId, int team1Id, int team2Id, int bracketID) throws SQLException {
        String sql = "INSERT INTO Game (Round, ScheduleID, Team1ID, Team2ID, BracketID) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, round);
            stmt.setInt(2, scheduleId);
            stmt.setInt(3, team1Id);
            stmt.setInt(4, team2Id);
            stmt.setInt(5, bracketID); //add
            stmt.executeUpdate();
        }
    }

    /**
     * UI method to remove a team.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    public static void deleteTeam(Connection conn, int teamId) throws SQLException {
        String sql = "DELETE FROM Team WHERE TeamID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            stmt.executeUpdate();
        }
    }
      /**
     * Checks if a team ID exists in the database.
     *
     * @param conn   Database connection.
     * @param teamId The team ID to check.
     * @return True if the team ID exists, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    public static boolean teamIdExists(Connection conn, int teamId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM Team WHERE TeamID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        }
    }
}
