package Bilkay.UserRelatedServices;

import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;

public class userDbPull {

    public static user getUserObjFromUserID(int userID) {

        user currentUser = null;

        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();

            String insertQuery = "SELECT * FROM users WHERE user_id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, userID);

            ResultSet resultsSetForCurrentUser = preparedStatement.executeQuery();

            if (resultsSetForCurrentUser.next()) {
                currentUser = new user(Integer.parseInt(resultsSetForCurrentUser.getString("user_id")), resultsSetForCurrentUser.getString("name_surname"), resultsSetForCurrentUser.getString("username"), resultsSetForCurrentUser.getString("password"), resultsSetForCurrentUser.getString("webmail"), null, null, resultsSetForCurrentUser.getString("role"));
                if (resultsSetForCurrentUser.getString("profile_picture_path") != null) {
                    currentUser.setPathToPP(resultsSetForCurrentUser.getString("profile_picture_path"));
                } else {
                    currentUser.setPathToPP("./src\\main\\resources\\iconsForApp\\userIconDashboard.png");
                }

                if (resultsSetForCurrentUser.getString("age") != null) {
                    currentUser.setAge(Integer.parseInt(resultsSetForCurrentUser.getString("age")));
                }
                if (resultsSetForCurrentUser.getString("grade") != null) {
                    currentUser.setGrade(resultsSetForCurrentUser.getString("grade"));
                }
                if (resultsSetForCurrentUser.getString("department") != null) {
                    currentUser.setDepartment(resultsSetForCurrentUser.getString("department"));
                }
                if (resultsSetForCurrentUser.getString("gender") != null) {
                    currentUser.setGender(resultsSetForCurrentUser.getString("gender"));
                }
                if (resultsSetForCurrentUser.getString("bil_Kay_points") != null) {
                    currentUser.setBilkayPoints(resultsSetForCurrentUser.getInt("bil_Kay_points"));
                }


            } else {
                return null;
            }

            preparedStatement.close();
            resultsSetForCurrentUser.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUser;

    }


    public static ArrayList<SubCategory> getChosenSubCategoriesFromUserID(int UserID) {

        ArrayList<SubCategory> chosenSubCategories = new ArrayList<SubCategory>();

        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();

            String queryForSubCategoryID = "SELECT interestSubCategory_name FROM interestsubcategory WHERE interestSubCategory_id IN (select interestSubCategory_id From user_interestsubcategory_relation Where user_id = ? )";

            PreparedStatement preparedStatementForSubCatID = connection.prepareStatement(queryForSubCategoryID);
            preparedStatementForSubCatID.setInt(1, UserID);

            ResultSet resultsSetForSubCatIDS = preparedStatementForSubCatID.executeQuery();

            while (resultsSetForSubCatIDS.next()) {
                String value = resultsSetForSubCatIDS.getString("interestSubCategory_name");
                chosenSubCategories.add(new SubCategory(value));
            }

            SubCategory.resetSubCatID();


            preparedStatementForSubCatID.close();
            resultsSetForSubCatIDS.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chosenSubCategories;
    }

    public static ArrayList<Category> getChosenCategoriesFromUserID(int UserID) {

        ArrayList<Category> chosenCategories = new ArrayList<Category>();

        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();

            String queryForCategoryID = "SELECT interestCategory_name FROM interestscategory WHERE interestCategory_id IN (select interestCategory_id From user_interestscategory_relation Where user_id = ? )";

            PreparedStatement preparedStatementForCatID = connection.prepareStatement(queryForCategoryID);
            preparedStatementForCatID.setInt(1, UserID);

            ResultSet resultsSetForCatIDS = preparedStatementForCatID.executeQuery();

            while (resultsSetForCatIDS.next()) {
                String value = resultsSetForCatIDS.getString("interestCategory_name");
                chosenCategories.add(new Category(value));
            }

            Category.resetCategoryIDs();


            preparedStatementForCatID.close();
            resultsSetForCatIDS.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chosenCategories;
    }


    public static String idToUserNameDB(int UserID) {

        String userName = null;

        try {
            Connection connection = DatabaseManager.getConnection();
            Statement statement = connection.createStatement();

            String queryForSubCategoryID = "SELECT username FROM users WHERE user_id =?";

            PreparedStatement preparedStatementForSubCatID = connection.prepareStatement(queryForSubCategoryID);
            preparedStatementForSubCatID.setInt(1, UserID);

            ResultSet resultsSetForSubCatIDS = preparedStatementForSubCatID.executeQuery();

            while (resultsSetForSubCatIDS.next()) {
                userName = resultsSetForSubCatIDS.getString("username");
            }
            preparedStatementForSubCatID.close();
            resultsSetForSubCatIDS.close();
            statement.close();
            return userName;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
