package Bilkay.Email_Keyboard_DatabaseServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DB_URL = "deletedForPrivacyMatters";

    private static final String USERNAME = "deletedForPrivacyMatters";
    private static final String PASSWORD = "deletedForPrivacyMatters";

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
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
