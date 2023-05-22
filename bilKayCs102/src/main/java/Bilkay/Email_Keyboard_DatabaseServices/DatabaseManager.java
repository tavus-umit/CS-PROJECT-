package Bilkay.Email_Keyboard_DatabaseServices;

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

    public static void main(String[] args) throws SQLException {


        String deleteCat = "DELETE from bilkaydb.user_interestscategory_relation WHERE bilkaydb.user_interestscategory_relation.user_id = ?";

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement deleteCatPStatement = connection.prepareStatement(deleteCat);
        deleteCatPStatement.setString(1, "1");

        int rowsAffected = deleteCatPStatement.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);

    }
}
