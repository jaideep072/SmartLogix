import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/smartlogix";
        String user = "postgres";
        String password = "postgres";

        System.out.println("Attempting to connect to PostgreSQL database...");

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("✅ Connection Successful!");

            // Run a simple test query to count tables
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(
                         "SELECT count(*) FROM information_schema.tables WHERE table_schema = 'public'")) {
                
                if (rs.next()) {
                    System.out.println("✅ Database is active. Number of tables found: " + rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Connection Failed!");
            System.err.println("Error details: " + e.getMessage());
            System.err.println("\nTroubleshooting tips:");
            System.err.println("1. Is pgAdmin / PostgreSQL running?");
            System.err.println("2. Did you create the database named 'smartlogix'?");
            System.err.println("3. Is your password 'postgres'? (If not, update DatabaseManager.java)");
        }
    }
}
