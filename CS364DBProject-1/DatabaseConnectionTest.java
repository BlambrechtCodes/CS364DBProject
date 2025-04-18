import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    // Database connection details
    private static final String URL = "jdbc:mysql://138.49.184.47:3306/lambrecht5083_CollegeHoopsDB";
    private static final String USER = "lambrecht5083"; // Replace with your username
    private static final String PASSWORD = "Z86vs3&gqjc!XREe"; // Replace with your password

    public static void main(String[] args) {
        System.out.println("Testing database connection...");

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Attempt to establish a connection
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
                if (conn != null) {
                    System.out.println("Connection established successfully!");
                } else {
                    System.out.println("Failed to establish connection.");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
