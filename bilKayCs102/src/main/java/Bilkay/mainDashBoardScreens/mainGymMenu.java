package Bilkay.mainDashBoardScreens;

import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;
import Bilkay.UserRelatedServices.user;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class mainGymMenu {
    private final user currentUser;
    private final JFrame myMainFrame;
    ArrayList<String> firstProgram;
    ArrayList<String> secondProgram;
    ArrayList<String> thirdProgram;
    ArrayList<String> fourthProgram;
    ArrayList<String> fifthProgram;
    ArrayList<JLabel> d1List;
    ArrayList<JLabel> d2List;
    ArrayList<JLabel> d3List;
    ArrayList<JLabel> d4List;
    ArrayList<JLabel> d5List;
    private JPanel mainPanelForMenu;
    private JLabel logoJPanel;
    private JScrollPane mainJScrollPane;
    private JPanel mainJPanelForScroll;
    private JPanel gymProgramPanel;
    private JLabel userIcon;
    private JPanel gymProgramJpanel;
    private JLabel day1Jlabel;
    private JLabel day2Jlabel;
    private JLabel day3Jlabel;
    private JLabel day4JLabel;
    private JLabel day5Jlabel;
    private JButton newProgramButton;
    private JLabel d1e1;
    private JLabel d1e2;
    private JLabel d1e3;
    private JLabel d1e4;
    private JLabel d1e5;
    private JLabel d2e1;
    private JLabel d2e2;
    private JLabel d2e3;
    private JLabel d2e4;
    private JLabel d2e5;
    private JLabel d3e1;
    private JLabel d3e2;
    private JLabel d3e3;
    private JLabel d3e4;
    private JLabel d3e5;
    private JLabel d4e1;
    private JLabel d4e2;
    private JLabel d4e3;
    private JLabel d4e4;
    private JLabel d4e5;
    private JLabel d5e1;
    private JLabel d5e2;
    private JLabel d5e3;
    private JLabel d5e4;
    private JLabel d5e5;
    private JLabel d1e6;
    private JLabel d2e6;
    private JLabel d3e6;
    private JLabel d4e6;
    private JLabel d5e6;


    public mainGymMenu(JFrame myMainFrame, user currentUser) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;

        fillTheArrays();

        try {
            getTheProgramsFromDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        loadTheProgramToScreen();


        newProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    removeAllTheRelations();

                    firstProgram = getGymPrograms();
                    secondProgram = getGymPrograms();
                    thirdProgram = getGymPrograms();
                    fourthProgram = getGymPrograms();
                    fifthProgram = getGymPrograms();

                    loadTheProgramToScreen();
                    mainPanelForMenu.revalidate();
                    mainPanelForMenu.repaint();
                    JOptionPane.showMessageDialog(myMainFrame, "New Program Assigned", "Gym Program", JOptionPane.INFORMATION_MESSAGE);


                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });
    }

    private void getTheProgramsFromDB() throws SQLException {

        Connection connection = DatabaseManager.getConnection();

        String queryForExercisesFromProgram = "SELECT exercise_name FROM exercises WHERE program_id IN (SELECT user_gym_program_relation.program_id from user_gym_program_relation where user_id = ? ORDER BY user_program_id)";

        PreparedStatement preparedStatementForExercises = connection.prepareStatement(queryForExercisesFromProgram);

        preparedStatementForExercises.setInt(1, currentUser.getUserID());

        ResultSet resultsSetForExerciseNames = preparedStatementForExercises.executeQuery();

        ArrayList<String> allExercises = new ArrayList<String>();

        while (resultsSetForExerciseNames.next()) {
            String value = resultsSetForExerciseNames.getString("exercise_name");
            allExercises.add(value);
        }

        firstProgram = new ArrayList<>(allExercises.subList(0, 6));
        secondProgram = new ArrayList<>(allExercises.subList(6, 12)) ;
        thirdProgram = new ArrayList<>(allExercises.subList(12, 18))  ;
        fourthProgram = new ArrayList<> (allExercises.subList(18, 24)) ;
        fifthProgram = new ArrayList<>(allExercises.subList(24, 30)) ;

        preparedStatementForExercises.close();
        resultsSetForExerciseNames.close();
        connection.close();


    }

    private void loadTheProgramToScreen() {

        if (!(firstProgram == null) && !(secondProgram == null) && !(thirdProgram == null) && !(fourthProgram == null) && !(fifthProgram == null)) {

            for (int i = 0; i < d1List.size(); i++) {
                d1List.get(i).setText(firstProgram.get(i));
                d2List.get(i).setText(secondProgram.get(i));
                d3List.get(i).setText(thirdProgram.get(i));
                d4List.get(i).setText(fourthProgram.get(i));
                d5List.get(i).setText(fifthProgram.get(i));
            }
        } else {
            for (int i = 0; i < d1List.size(); i++) {
                d1List.get(i).setText("");
                d2List.get(i).setText("");
                d3List.get(i).setText("");
                d4List.get(i).setText("");
                d5List.get(i).setText("");

            }
        }


    }


    private void fillTheArrays() {

        d1List = new ArrayList<JLabel>();

        d1List.add(d1e1);
        d1List.add(d1e2);
        d1List.add(d1e3);
        d1List.add(d1e4);
        d1List.add(d1e5);
        d1List.add(d1e6);


        d2List = new ArrayList<JLabel>();

        d2List.add(d2e1);
        d2List.add(d2e2);
        d2List.add(d2e3);
        d2List.add(d2e4);
        d2List.add(d2e5);
        d2List.add(d2e6);

        d3List = new ArrayList<JLabel>();

        d3List.add(d3e1);
        d3List.add(d3e2);
        d3List.add(d3e3);
        d3List.add(d3e4);
        d3List.add(d3e5);
        d3List.add(d3e6);

        d4List = new ArrayList<JLabel>();

        d4List.add(d4e1);
        d4List.add(d4e2);
        d4List.add(d4e3);
        d4List.add(d4e4);
        d4List.add(d4e5);
        d4List.add(d4e6);

        d5List = new ArrayList<JLabel>();

        d5List.add(d5e1);
        d5List.add(d5e2);
        d5List.add(d5e3);
        d5List.add(d5e4);
        d5List.add(d5e5);
        d5List.add(d5e6);
    }

    private void removeAllTheRelations() throws SQLException {


        String deleteGym = "DELETE from user_gym_program_relation WHERE user_id = ?";

        Connection connection = DatabaseManager.getConnection();
        PreparedStatement deleteGymRelation = connection.prepareStatement(deleteGym);
        deleteGymRelation.setInt(1, currentUser.getUserID());

        deleteGymRelation.executeUpdate();
        connection.close();
        deleteGymRelation.close();


    }

    private ArrayList<String> getGymPrograms() {

        ArrayList<String> exercises = new ArrayList<String>();

        try {

            Random randomNumber = new Random();

            Connection connection = DatabaseManager.getConnection();

            int randomProgramId;

            boolean flag = true;

            do {
                randomProgramId = randomNumber.nextInt(1, 10);

                String checkGymRelation = "SELECT COUNT(*) FROM user_gym_program_relation WHERE user_id = ? AND program_id = ?";

                PreparedStatement checkGymRelationship = connection.prepareStatement(checkGymRelation);

                checkGymRelationship.setInt(1, currentUser.getUserID());
                checkGymRelationship.setInt(2, randomProgramId);


                ResultSet resultSet = checkGymRelationship.executeQuery();
                resultSet.next();

                if (resultSet.getInt(1) == 0) {
                    flag = false;

                    try {

                        String insertQuery = "INSERT INTO user_gym_program_relation (user_id, program_id) VALUES (?, ?)";
                        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
                        preparedStatement.setInt(1, currentUser.getUserID());
                        preparedStatement.setInt(2, randomProgramId);
                        preparedStatement.executeUpdate();
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
                checkGymRelationship.close();
                resultSet.close();


            } while (flag);


                String queryForExercisesFromProgram = "SELECT exercise_name FROM exercises WHERE program_id = ?";

                PreparedStatement preparedStatementForExercises = connection.prepareStatement(queryForExercisesFromProgram);

                preparedStatementForExercises.setInt(1, randomProgramId);

                ResultSet resultsSetForExerciseNames = preparedStatementForExercises.executeQuery();

                while (resultsSetForExerciseNames.next()) {
                    String value = resultsSetForExerciseNames.getString("exercise_name");
                    exercises.add(value);
                }


                preparedStatementForExercises.close();
                resultsSetForExerciseNames.close();
                connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return exercises;
    }


    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }
}
