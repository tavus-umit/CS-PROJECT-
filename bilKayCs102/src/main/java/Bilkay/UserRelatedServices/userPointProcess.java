package Bilkay.UserRelatedServices;

import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class userPointProcess {


    public static void increaseUserPoints(user currentUser, int points) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String changePasswordSql = "UPDATE users SET bil_Kay_points = ? WHERE user_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(changePasswordSql);
        preparedStatement.setInt(1, currentUser.getBilkayPoints()+ points);
        preparedStatement.setInt(2, currentUser.getUserID());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();

        currentUser.setBilkayPoints(currentUser.getBilkayPoints() + points);
    }


    public static void increaseUserPoints(user currentUser) throws SQLException {

        increaseUserPoints(currentUser,1);
    }

    public static void decreaseUserPoints(user currentUser, int points) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String changePasswordSql = "UPDATE users SET bil_Kay_points = ? WHERE user_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(changePasswordSql);
        preparedStatement.setInt(1, currentUser.getBilkayPoints() - points);
        preparedStatement.setInt(2, currentUser.getUserID());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        statement.close();

        currentUser.setBilkayPoints(currentUser.getBilkayPoints() - points);
    }

    public static void decreaseUserPoints(user currentUser) throws SQLException {

        decreaseUserPoints(currentUser,1);
    }
}
