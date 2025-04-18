import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Main method to run the College Hoops Database Manager.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                boolean running = true;

                // Clear the Screen Before Running DB Manager
                clearScreen();

                printTeamTable(conn);
                printPlayerTable(conn);
                printGameTable(conn);
                printBracketTable(conn);
                printScheduleTable(conn);

                while (running) {

                    System.out.println("\n----------------------------------");
                    System.out.println("=== College Hoops Database Manager ===");
                    System.out.println("----------------------------------");

                    System.out.println("1. Add Team");
                    System.out.println("2. Remove Team");
                    System.out.println("3. Add Player");
                    System.out.println("4. Remove Player");
                    System.out.println("5. Update Player Stats");
                    System.out.println("6. Add Game");
                    System.out.println("7. Remove Game");
                    System.out.println("8. Add Bracket");
                    System.out.println("9. Remove Bracket");
                    System.out.println("10. Add Schedule");
                    System.out.println("11. Remove Schedule");
                    System.out.println("12. View Tables");
                    System.out.println("13. List All Information");
                    System.out.println("14. Exit");
                    System.out.print("Select option: ");

                    int choice = readInt();

                    System.out.println("----------------------------------");

                    switch (choice) {
                        case 1 -> addTeamUI(conn);
                        case 2 -> removeTeamUI(conn);
                        case 3 -> addPlayerUI(conn);
                        case 4 -> removePlayerUI(conn);
                        case 5 -> updatePlayerStatsUI(conn);
                        case 6 -> addGameUI(conn);
                        case 7 -> removeGameUI(conn);
                        case 8 -> addBracketUI(conn);
                        case 9 -> removeBracketUI(conn);
                        case 10 -> addScheduleUI(conn);
                        case 11 -> removeScheduleUI(conn);
                        case 12 -> viewTablesUI(conn);
                        case 13 -> listAllInformationUI(conn);
                        case 14 -> {
                            System.out.println("Exiting the UI. Thank you!");
                            System.out.println("----------------------------------");
                            running = false;
                        }
                        default -> System.out.println("Invalid choice!");
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            System.err.println("Please ensure the database is running and accessible.");
            System.out.println();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J"); // ANSI escape code for clear screen
        System.out.flush();
    }

     // -------------------------------------------------------------------------
    // Object Storage Methods
    // -------------------------------------------------------------------------

    /**
     * Retrieves all teams from the database and returns them as a list of Team objects.
     *
     * @param conn Database connection.
     * @return A list of Team objects.
     * @throws SQLException If a database error occurs.
     */
    public static List<Team> getAllTeams(Connection conn) throws SQLException {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT TeamID, Name, Region FROM Team";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
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
     * Retrieves all players from the database and returns them as a list of Player objects.
     *
     * @param conn Database connection.
     * @return A list of Player objects.
     * @throws SQLException If a database error occurs.
     */
    public static List<Player> getAllPlayers(Connection conn) throws SQLException {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT PlayerID, TeamID, Name, Position, Height FROM Player";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
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
     * Retrieves all games from the database and returns them as a list of Game objects.
     *
     * @param conn Database connection.
     * @return A list of Game objects.
     * @throws SQLException If a database error occurs.
     */
    public static List<Game> getAllGames(Connection conn) throws SQLException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT GameID, Round, ScheduleID, Team1ID, Team2ID FROM Game";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int gameId = rs.getInt("GameID");
                String round = rs.getString("Round");
                int scheduleId = rs.getInt("ScheduleID");
                int team1Id = rs.getInt("Team1ID");
                int team2Id = rs.getInt("Team2ID");
                Game game = new Game(gameId, round, scheduleId, team1Id, team2Id);
                games.add(game);
            }
        }
        return games;
    }

    /**
     * Retrieves all brackets from the database and returns them as a list of Bracket objects.
     *
     * @param conn Database connection.
     * @return A list of Bracket objects.
     * @throws SQLException If a database error occurs.
     */
    public static List<Bracket> getAllBrackets(Connection conn) throws SQLException {
        List<Bracket> brackets = new ArrayList<>();
        String sql = "SELECT BracketID, Name, Champion FROM Bracket";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
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
     * Retrieves all schedules from the database and returns them as a list of Schedule objects.
     *
     * @param conn Database connection.
     * @return A list of Schedule objects.
     * @throws SQLException If a database error occurs.
     */
    public static List<Schedule> getAllSchedules(Connection conn) throws SQLException {
        List<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT ScheduleID, Tickets, Broadcasting, Date, Location FROM Schedule";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
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

    private static void listAllInformationUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.println("All Information:");
        System.out.println("----------------------------------");

        listTeamsWithLabels(conn);
        System.out.println("----------------------------------");
        listPlayersWithLabels(conn);
        System.out.println("----------------------------------");
        listGamesWithLabels(conn);
        System.out.println("----------------------------------");
        listBracketsWithLabels(conn);
        System.out.println("----------------------------------");
        listSchedulesWithLabels(conn);

        System.out.println("----------------------------------");
        displayResults();
    }

    // -------------------------------------------------------------------------
    // Listing Methods with Labels
    // -------------------------------------------------------------------------

    /**
     * Lists teams with labels.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void listTeamsWithLabels(Connection conn) throws SQLException {
        List<Team> teams = getAllTeams(conn);
        System.out.println("Teams:");
        for (Team team : teams) {
            System.out.println("TeamID: " + team.getTeamId() +
                               ", Name: " + team.getName() +
                               ", Region: " + team.getRegion());
        }
    }

    /**
     * Lists players with labels.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void listPlayersWithLabels(Connection conn) throws SQLException {
        List<Player> players = getAllPlayers(conn);
        System.out.println("Players:");
        for (Player player : players) {
            System.out.println("PlayerID: " + player.getPlayerId() +
                               ", TeamID: " + player.getTeamId() +
                               ", Name: " + player.getName() +
                               ", Position: " + player.getPosition() +
                               ", Height: " + player.getHeight());
        }
    }

    /**
     * Lists games with labels.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void listGamesWithLabels(Connection conn) throws SQLException {
        List<Game> games = getAllGames(conn);
        System.out.println("Games:");
        for (Game game : games) {
            System.out.println("GameID: " + game.getGameId() +
                               ", Round: " + game.getRound() +
                               ", ScheduleID: " + game.getScheduleId() +
                               ", Team1ID: " + game.getTeam1Id() +
                               ", Team2ID: " + game.getTeam2Id());
        }
    }

    /**
     * Lists brackets with labels.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void listBracketsWithLabels(Connection conn) throws SQLException {
        List<Bracket> brackets = getAllBrackets(conn);
        System.out.println("Brackets:");
        for (Bracket bracket : brackets) {
            System.out.println("BracketID: " + bracket.getBracketID() +
                               ", BracketName: " + bracket.getBracketName() +
                               ", ChampionTeamID: " + bracket.getChampionTeamID());
        }
    }

    /**
     * Lists schedules with labels.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void listSchedulesWithLabels(Connection conn) throws SQLException {
        List<Schedule> schedules = getAllSchedules(conn);
        System.out.println("Schedules:");
        for (Schedule schedule : schedules) {
            System.out.println("ScheduleID: " + schedule.getScheduleId() +
                               ", Tickets: " + schedule.getTickets() +
                               ", Broadcasting: " + schedule.getBroadcasting() +
                               ", Date: " + schedule.getDate() +
                               ", Location: " + schedule.getLocation());
        }
    }

    /**
     * UI method to add a new team.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void addTeamUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.print("Team Name: ");
        String name = scanner.nextLine();

        System.out.print("Region: ");
        String region = scanner.nextLine();

        addTeam(conn, name, region);
        System.out.println("Team added successfully!");
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * UI method to remove a team.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void removeTeamUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.print("Enter Team ID to remove: ");
        int teamId = readInt();

        deleteTeam(conn, teamId);
        System.out.println("Team removed (if existed)");
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * UI method to add a new player.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void addPlayerUI(Connection conn) throws SQLException {
        clearScreen();

        // Display available Team IDs
        System.out.println("Available Team IDs:");
        printAvailableTeamIDs(conn); // Helper method to display Team IDs

        int teamId;
        while (true) {
            System.out.print("Enter Team ID: ");
            teamId = readInt();

            if (teamIdExists(conn, teamId)) {
                break; // Valid Team ID, exit loop
            } else {
                System.out.println("Error: Team ID " + teamId + " does not exist. Please enter a valid Team ID.");
            }
        }

        System.out.print("Player ID: ");
        int playerId = readInt();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Position: ");
        String position = scanner.nextLine();

        System.out.print("Height (e.g., 6.2): ");
        double height = readDouble();

        addPlayer(conn, playerId, teamId, name, position, height);
        System.out.println("Player added successfully!");
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * Helper method to display available Team IDs.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void printAvailableTeamIDs(Connection conn) throws SQLException {
        String sql = "SELECT TeamID, Name FROM Team";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Team ID: " + rs.getInt("TeamID") + ", Name: " + rs.getString("Name"));
            }
        }
    }

    /**
     * Helper method to check if a TeamID exists.
     *
     * @param conn Database connection.
     * @param teamId Team ID to check.
     * @return true if team ID exists, false otherwise.
     * @throws SQLException If a database error occurs.
     */
    private static boolean teamIdExists(Connection conn, int teamId) throws SQLException {
        String sql = "SELECT TeamID FROM Team WHERE TeamID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // Returns true if a row is found
            }
        }
    }

    /**
     * UI method to remove a player.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void removePlayerUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.print("Enter Player ID to remove: ");
        int playerId = readInt();

        deletePlayer(conn, playerId);
        System.out.println("Player removed (if existed)");
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * UI method to update player statistics.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void updatePlayerStatsUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.print("Enter Player ID to update stats: ");
        int playerId = readInt();

        System.out.print("STL: ");
        double stl = readDouble();

        System.out.print("BLK: ");
        double blk = readDouble();

        System.out.print("MIN: ");
        double min = readDouble();

        System.out.print("TO: ");
        double to = readDouble();

        System.out.print("FG Percentage: ");
        double fgPercentage = readDouble();

        System.out.print("3P Percentage: ");
        double threePPercentage = readDouble();

        System.out.print("FT Percentage: ");
        double ftPercentage = readDouble();

        System.out.print("PTS: ");
        double pts = readDouble();

        System.out.print("REB: ");
        double reb = readDouble();

        System.out.print("AST: ");
        double ast = readDouble();

        System.out.print("PF: ");
        double pf = readDouble();

        updatePlayerStats(conn, playerId, stl, blk, min, to, fgPercentage, threePPercentage, ftPercentage, pts, reb, ast, pf);
        System.out.println("Player stats updated successfully!");
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * UI method to add a new game.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void addGameUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.print("Round: ");
        String round = scanner.nextLine();

        System.out.print("Schedule ID: ");
        int scheduleId = readInt();

        System.out.print("Enter Team 1 ID: ");
        int team1Id = readInt();

        System.out.print("Enter Team 2 ID: ");
        int team2Id = readInt();

        try {
            addGame(conn, round, scheduleId, team1Id, team2Id);
            System.out.println("Game added successfully!");
        } catch (SQLException e) {
            if (e.getMessage().contains("foreign key constraint fails")) {
                System.out.println("Schedule not present: Please make a schedule to schedule the game");
            } else {
                System.err.println("Database error: " + e.getMessage());
            }
        }
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * Helper method to get and validate TeamID.
     *
     * @param conn Database connection.
     * @param prompt Prompt for user input.
     * @return Valid Team ID, or -1 if input error.
     * @throws SQLException If a database error occurs.
     */
    private static int getAndValidateTeamID(Connection conn, String prompt) throws SQLException {
        while (true) {
            System.out.print("Enter " + prompt + ": ");
            int teamID = readInt();
            if (teamIdExists(conn, teamID)) {
                return teamID;
            } else {
                System.out.println("Error: Team ID does not exist.");
            }
        }
    }

    /**
     * Method to add a new game to the database.
     *
     * @param conn Database connection.
     * @param round Game round.
     * @param scheduleID Schedule ID.
     * @param team1ID Team 1 ID.
     * @param team2ID Team 2 ID.
     * @throws SQLException If a database error occurs.
     */
    private static void addGame(Connection conn, String round, int scheduleID, int team1ID, int team2ID) throws SQLException {
        String sql = "INSERT INTO Game (Round, ScheduleID, Team1ID, Team2ID) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, round);
            stmt.setInt(2, scheduleID);
            stmt.setInt(3, team1ID);
            stmt.setInt(4, team2ID);
            stmt.executeUpdate();
        }
    }

    /**
     * UI method to remove a game.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void removeGameUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.print("Enter Game ID to remove: ");
        int gameId = readInt();

        deleteGame(conn, gameId);
        System.out.println("Game removed (if existed)");
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * Method to delete a game from the database.
     *
     * @param conn Database connection.
     * @param gameId Game ID to remove.
     * @throws SQLException If a database error occurs.
     */
    private static void deleteGame(Connection conn, int gameId) throws SQLException {
        String sql = "DELETE FROM Game WHERE GameID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, gameId);
            stmt.executeUpdate();
        }
    }

    /**
     * UI method to add a new bracket.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void addBracketUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.print("Bracket Name: ");
        String Name = scanner.nextLine();

        System.out.print("Champion Team ID: ");
        int championTeamID = readInt();

        addBracket(conn, Name, championTeamID);
        System.out.println("Bracket added successfully!");
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * UI method to remove a bracket.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void removeBracketUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.print("Enter Bracket ID to remove: ");
        int bracketId = readInt();

        deleteBracket(conn, bracketId);
        System.out.println("Bracket removed (if existed)");
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * UI method to add a new schedule.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void addScheduleUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.print("Tickets: ");
        String tickets = scanner.nextLine();

        System.out.print("Broadcasting: ");
        String broadcasting = scanner.nextLine();

        System.out.print("Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        addSchedule(conn, tickets, broadcasting, Date.valueOf(date), location);
        System.out.println("Schedule added successfully!");
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * UI method to remove a schedule.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void removeScheduleUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.print("Enter Schedule ID to remove: ");
        int scheduleId = readInt();

        deleteSchedule(conn, scheduleId);
        System.out.println("Schedule removed (if existed)");
        System.out.println("----------------------------------");
        displayResults();
    }

    /**
     * UI method to view tables.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void viewTablesUI(Connection conn) throws SQLException {
        clearScreen();
        System.out.println("\nWhich tables to display?");
        System.out.println("1. Teams\n2. Players\n3. Games\n4. Brackets\n5. Schedules\n6. All");
        System.out.print("Choice: ");

        int choice = readInt();
        System.out.println("----------------------------------");

        switch (choice) {
            case 1 -> printTeamTable(conn);
            case 2 -> printPlayerTable(conn);
            case 3 -> printGameTable(conn);
            case 4 -> printBracketTable(conn);
            case 5 -> printScheduleTable(conn);
            case 6 -> {
                printTeamTable(conn);
                printPlayerTable(conn);
                printGameTable(conn);
                printBracketTable(conn);
                printScheduleTable(conn);
            }
            default -> System.out.println("Invalid choice!");
        }
        displayResults();
    }

    /**
     * Method to add a team to the database.
     *
     * @param conn Database connection.
     * @param name Team name.
     * @param region Team region.
     * @throws SQLException If a database error occurs.
     */
    private static void addTeam(Connection conn, String name, String region) throws SQLException {
        String sql = "INSERT INTO Team (Name, Region) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, region);
            stmt.executeUpdate();
        }
    }

    /**
     * Method to delete a team from the database.
     *
     * @param conn Database connection.
     * @param teamId Team ID to delete.
     * @throws SQLException If a database error occurs.
     */
    private static void deleteTeam(Connection conn, int teamId) throws SQLException {
        String sql = "DELETE FROM Team WHERE TeamID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, teamId);
            stmt.executeUpdate();
        }
    }

    /**
     * Method to add a player to the database.
     *
     * @param conn Database connection.
     * @param playerId Player ID.
     * @param teamId Team ID.
     * @param name Player name.
     * @param position Player position.
     * @param height Player height.
     * @throws SQLException If a database error occurs.
     */
    private static void addPlayer(Connection conn, int playerId, int teamId, String name, String position, double height) throws SQLException {
        String sql = "INSERT INTO Player (PlayerID, TeamID, Name, Position, Height) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            stmt.setInt(2, teamId);
            stmt.setString(3, name);
            stmt.setString(4, position);
            stmt.setDouble(5, height);
            stmt.executeUpdate();
        }
    }

    /**
     * Method to delete a player from the database.
     *
     * @param conn Database connection.
     * @param playerId Player ID to delete.
     * @throws SQLException If a database error occurs.
     */
    private static void deletePlayer(Connection conn, int playerId) throws SQLException {
        String sql = "DELETE FROM Player WHERE PlayerID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, playerId);
            stmt.executeUpdate();
        }
    }

    /**
     * Method to update player stats in the database.
     *
     * @param conn Database connection.
     * @param playerId Player ID to update.
     * @param stl Steals.
     * @param blk Blocks.
     * @param min Minutes.
     * @param to Turnovers.
     * @param fgPercentage Field goal percentage.
     * @param threePPercentage 3-point percentage.
     * @param ftPercentage Free throw percentage.
     * @param pts Points.
     * @param reb Rebounds.
     * @param ast Assists.
     * @param pf Personal fouls.
     * @throws SQLException If a database error occurs.
     */
    private static void updatePlayerStats(Connection conn, int playerId, double stl, double blk, double min, double to, double fgPercentage, double threePPercentage, double ftPercentage, double pts, double reb, double ast, double pf) throws SQLException {
        String sql = "UPDATE PlayerGameStats SET STL = ?, BLK = ?, MIN = ?, `TO` = ?, `FG%` = ?, `3P%` = ?, `FT%` = ?, PTS = ?, REB = ?, AST = ?, PF = ? WHERE PlayerID = ? AND GameID = (SELECT GameID from GameParticipation WHERE PlayerID = ? limit 1)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, stl);
            stmt.setDouble(2, blk);
            stmt.setDouble(3, min);
            stmt.setDouble(4, to);
            stmt.setDouble(5, fgPercentage);
            stmt.setDouble(6, threePPercentage);
            stmt.setDouble(7, ftPercentage);
            stmt.setDouble(8, pts);
            stmt.setDouble(9, reb);
            stmt.setDouble(10, ast);
            stmt.setDouble(11, pf);
            stmt.setInt(12, playerId);
            stmt.setInt(13, playerId);
            stmt.executeUpdate();
        }
    }

    /**
     * Method to add a bracket to the database.
     *
     * @param conn Database connection.
     * @param bracketName Bracket name.
     * @param championTeamID Champion team ID.
     * @throws SQLException If a database error occurs.
     */
    private static void addBracket(Connection conn, String Name, int championTeamID) throws SQLException {
        String sql = "INSERT INTO Bracket (Name, Champion) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, Name);
            stmt.setInt(2, championTeamID);
            stmt.executeUpdate();
        }
    }

    /**
     * Method to delete a bracket from the database.
     *
     * @param conn Database connection.
     * @param bracketId Bracket ID to delete.
     * @throws SQLException If a database error occurs.
     */
    private static void deleteBracket(Connection conn, int bracketId) throws SQLException {
        String sql = "DELETE FROM Bracket WHERE BracketID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bracketId);
            stmt.executeUpdate();
        }
    }

    /**
     * Method to add a schedule to the database.
     *
     * @param conn Database connection.
     * @param tickets Ticket information.
     * @param broadcasting Broadcasting information.
     * @param date Schedule date.
     * @param location Schedule location.
     * @throws SQLException If a database error occurs.
     */
    private static void addSchedule(Connection conn, String tickets, String broadcasting, Date date, String location) throws SQLException {
        String sql = "INSERT INTO Schedule (Tickets, Broadcasting, Date, Location) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tickets);
            stmt.setString(2, broadcasting);
            stmt.setDate(3, date);
            stmt.setString(4, location);
            stmt.executeUpdate();
        }
    }

    /**
     * Method to delete a schedule from the database.
     *
     * @param conn Database connection.
     * @param scheduleId Schedule ID to delete.
     * @throws SQLException If a database error occurs.
     */
    private static void deleteSchedule(Connection conn, int scheduleId) throws SQLException {
        String sql = "DELETE FROM Schedule WHERE ScheduleID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            stmt.executeUpdate();
        }
    }

    /**
     * Method to print the Team table.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void printTeamTable(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Team";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Team Table ---");
            System.out.println("+---------+---------------------------+------------------+");
            System.out.printf("| %-7s | %-25s | %-16s |\n", "ID", "Name", "Region");
            System.out.println("+---------+---------------------------+------------------+");
            while (rs.next()) {
                System.out.printf("| %-7d | %-25s | %-16s |\n",
                        rs.getInt("TeamID"),
                        rs.getString("Name"),
                        rs.getString("Region"));
            }
            System.out.println("+---------+---------------------------+------------------+");
        }
    }

    /**
     * Method to print the Player table.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void printPlayerTable(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Player";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Player Table ---");
            System.out.println("+---------+----------+---------------------------+--------------------+----------+");
            System.out.printf("| %-7s | %-8s | %-25s | %-18s | %-8s |\n", "ID", "Team", "Name", "Position", "Height");
            System.out.println("+---------+----------+---------------------------+--------------------+----------+");
            while (rs.next()) {
                System.out.printf("| %-7d | %-8d | %-25s | %-18s | %-8.2f |\n",
                        rs.getInt("PlayerID"),
                        rs.getInt("TeamID"),
                        rs.getString("Name"),
                        rs.getString("Position"),
                        rs.getDouble("Height"));
            }
            System.out.println("+---------+----------+---------------------------+--------------------+----------+");
        }
    }

    /**
     * Method to print the Game table.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void printGameTable(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Game";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Game Table ---");
            System.out.println("+---------+------------+------------------+------------------+------------------+");
            System.out.printf("| %-7s | %-10s | %-16s | %-16s | %-16s |\n", "ID", "Round", "ScheduleID", "Team1ID", "Team2ID");
            System.out.println("+---------+------------+------------------+------------------+------------------+");
            while (rs.next()) {
                System.out.printf("| %-7d | %-10s | %-16d | %-16d | %-16d |\n",
                        rs.getInt("GameID"),
                        rs.getString("Round"),
                        rs.getInt("ScheduleID"),
                        rs.getInt("Team1ID"),
                        rs.getInt("Team2ID"));
            }
            System.out.println("+---------+------------+------------------+------------------+------------------+");
        }
    }

    /**
     * Method to print the Bracket table.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void printBracketTable(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Bracket";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Bracket Table ---");
            System.out.println("+---------+---------------------------+------------------+");
            System.out.printf("| %-7s | %-25s | %-16s |\n", "ID", "Name", "Champion");
            System.out.println("+---------+---------------------------+------------------+");
            while (rs.next()) {
                System.out.printf("| %-7d | %-25s | %-16d |\n",
                        rs.getInt("BracketID"),
                        rs.getString("Name"),
                        rs.getInt("Champion"));
            }
            System.out.println("+---------+---------------------------+------------------+");
        }
    }

    /**
     * Method to print the Schedule table.
     *
     * @param conn Database connection.
     * @throws SQLException If a database error occurs.
     */
    private static void printScheduleTable(Connection conn) throws SQLException {
        String sql = "SELECT * FROM Schedule";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Schedule Table ---");
            System.out.println("+---------+------------------------------------+------------------------------------+----------------+------------------------+");
            System.out.printf("| %-7s | %-34s | %-34s | %-14s | %-22s |\n", "ID", "Tickets", "Broadcasting", "Date", "Location");
            System.out.println("+---------+------------------------------------+------------------------------------+----------------+------------------------+");
            while (rs.next()) {
                System.out.printf("| %-7d | %-34s | %-34s | %-14s | %-24s |\n",
                        rs.getInt("ScheduleID"),
                        rs.getString("Tickets"),
                        rs.getString("Broadcasting"),
                        rs.getDate("Date"),
                        rs.getString("Location"));
            }
            System.out.println("+---------+------------------------------------+------------------------------------+----------------+------------------------+");
        }
    }

    /**
     * Method to display the "End of operation" message.
     */
    private static void displayResults() {
        
    }

    /**
     * Helper method to read an integer from the user.
     *
     * @return Integer input by the user.
     */
    private static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter an integer: ");
            }
        }
    }

    /**
     * Helper method to read a double from the user.
     *
     * @return Double input by the user.
     */
    private static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a double: ");
            }
        }
    }
}