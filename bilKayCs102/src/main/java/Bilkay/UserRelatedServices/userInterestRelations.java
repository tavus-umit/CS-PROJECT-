package Bilkay.UserRelatedServices;

import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;

import java.sql.*;

public class userInterestRelations {


    public static int findCategoryIDFromDB(String categoryName) throws SQLException {

        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String getCatID = "SELECT * FROM bilkaydb.interestscategory WHERE bilkaydb.interestscategory.interestCategory_name=?";

        PreparedStatement idStatement = connection.prepareStatement(getCatID);
        idStatement.setString(1, categoryName);

        ResultSet resultForID = idStatement.executeQuery();

        if (resultForID.next()) {
            return Integer.parseInt(resultForID.getString("interestCategory_id"));
        }
        idStatement.close();
        statement.close();
        connection.close();
        return -1;
    }

    public static int findSubCategoryIDFromDB(String subCategoryName) throws SQLException {

        Connection connection = DatabaseManager.getConnection();
        Statement statement = connection.createStatement();

        String getSubCatID = "SELECT * FROM bilkaydb.interestsubcategory WHERE bilkaydb.interestsubcategory.interestSubCategory_name=?";

        PreparedStatement idStatement = connection.prepareStatement(getSubCatID);
        idStatement.setString(1, subCategoryName);

        ResultSet resultForID = idStatement.executeQuery();

        if (resultForID.next()) {
            return Integer.parseInt(resultForID.getString("interestSubCategory_id"));
        }
        idStatement.close();
        statement.close();
        connection.close();
        return -1;
    }


    public static void storeUserSubCategories(int userId, int subCategoryId) throws SQLException {


        String checkSubCatRelation = "SELECT COUNT(*) FROM user_interestsubcategory_relation WHERE user_id = ? AND interestSubCategory_id = ?";

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement checkOccurrence = connection.prepareStatement(checkSubCatRelation);

        checkOccurrence.setInt(1, userId);
        checkOccurrence.setInt(2, subCategoryId);
        ResultSet resultSet = checkOccurrence.executeQuery();

        resultSet.next();

        if (!(resultSet.getInt(1) > 0)) {

            String insert_subcategory_relationship = "INSERT INTO user_interestsubcategory_relation (user_id, interestSubCategory_id) VALUES (?, ?)";


            PreparedStatement preparedStatement = connection.prepareStatement(insert_subcategory_relationship);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, subCategoryId);

            preparedStatement.executeUpdate();
        }
        connection.close();
        resultSet.close();

    }


    public static void storeUserCategories(int userId, int categoryId) throws SQLException {


        String checkCatRelation = "SELECT COUNT(*) FROM user_interestscategory_relation WHERE user_id = ? AND interestCategory_id = ?";

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement checkOccurrence = connection.prepareStatement(checkCatRelation);

        checkOccurrence.setInt(1, userId);
        checkOccurrence.setInt(2, categoryId);
        ResultSet resultSet = checkOccurrence.executeQuery();

        resultSet.next();

        if (!(resultSet.getInt(1) > 0)) {

            String insert_category_relationship = "INSERT INTO user_interestscategory_relation (user_id, interestCategory_id) VALUES (?, ?)";


            PreparedStatement preparedStatement = connection.prepareStatement(insert_category_relationship);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, categoryId);

            preparedStatement.executeUpdate();
        }
        connection.close();
        resultSet.close();

    }


}
