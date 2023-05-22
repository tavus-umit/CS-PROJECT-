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
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String getSubCatID = "SELECT * FROM bilkaydb.interestsubcategory WHERE bilkaydb.interestsubcategory.interestSubCategory_name=?";

        PreparedStatement idStatement = connection.prepareStatement(getSubCatID);
        idStatement.setString(1, "DIY");

        ResultSet resultForID = idStatement.executeQuery();

        if (resultForID.next()) {
            System.out.println(resultForID.getString("interestSubCategory_id"));
        }
        idStatement.close();
        statement.close();
        connection.close();

    }
}
