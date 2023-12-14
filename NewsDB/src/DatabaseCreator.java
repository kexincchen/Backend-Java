
import java.sql.*;

public class DatabaseCreator {

    private String host;
    private String user;
    private String password;

    public DatabaseCreator(String host, String user, String password){
        host = host;
        user = user;
        password = password;

    }

    public void createDatabase(String sql) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            System.out.println("Connecting to database...");
            connection = DriverManager.getConnection(host, user, password);

            // Execute a query
            System.out.println("Creating database...");
            statement = connection.createStatement();

            statement.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Finally block used to close resources
            try {
                if(statement != null) statement.close();
            }
            catch(SQLException ignored) {
            }
            try {
                if(connection != null) connection.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public boolean tableExists(String tableName, Connection conn) throws SQLException {
        boolean found = false;
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet rs = databaseMetaData.getTables(null, null, tableName, null);
        while (rs.next()) {
            String name = rs.getString("TABLE_NAME");
            if (tableName.equals(name)) {
                found = true;
                break;
            }
        }

        return found;
    }

    public static void main(String[] args) {
        String host = "jdbc:mysql://localhost:3306/?useSSL=false"; // Modify as needed
        String user = "root"; // Your database username
        String password = "your_password"; // Your database password

        String sql = "";

//        createDatabase(sql, host, user, password);
    }
}