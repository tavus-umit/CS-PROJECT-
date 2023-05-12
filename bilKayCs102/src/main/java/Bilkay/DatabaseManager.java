package Bilkay;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://bilkaydatabase.mysql.database.azure.com:3306/bilkaydb";

    private static final String USERNAME = "super";
    private static final String PASSWORD = "Bilkay2023";

    private static Connection connection;

    public static Connection getConnection() throws SQLException, SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        }
        return connection;
    }

    public static void main(String[] args) {
        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();

            // Execute the query to retrieve interests
            String query = "SELECT interestCategory_name FROM bilkaydb.interestscategory";
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set and print interests
            while (resultSet.next()) {
                String interest = resultSet.getString("interestCategory_name");
                System.out.println(interest);
            }

            // Close the resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
