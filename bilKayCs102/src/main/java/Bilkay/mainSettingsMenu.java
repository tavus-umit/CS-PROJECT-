package Bilkay;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class mainSettingsMenu {
    private final user currentUser;
    private  JPanel mainPanelForMenu;
    private JScrollPane mainJScrollPane;
    private JPanel mainJPanelForScroll;
    private JLabel logoJpanel;
    private JPanel editProfileMainPanel;
    private JLabel userIcon;
    private JPanel editProfileValuesJpanel;
    private JSeparator upperSeperator;
    private JLabel changeUsernameJlabel;
    private JLabel changeNameSurnameJlabel;
    private JLabel changeAgeJLabel;
    private JLabel changeGradeJLabel;
    private JLabel changeDepartmentJLabel;
    private JLabel changePasswordJlabel;
    private JLabel changeProfilePicutreJlabel;
    private JTextField usernameJtextField;
    private JTextField nameSurnameJtextfield;
    private JTextField ageJTextField;
    private JTextField gradeJtextfield;
    private JPasswordField passwordPField;
    private JButton usernameSubmitButton;
    private JButton nameSurnameSubmitButton;
    private JButton ageSubmitButton;
    private JButton gradeSubmitButton;
    private JButton departmentSubmitButton;
    private JButton passwordSubmitButton;
    private JButton profilePictureSumbitButton;
    private JPanel editInterestMainPanel;
    private JPanel interstJpanel;
    private JScrollPane interestCategoryScrollPane;
    private JList interestsCategoryJList;
    private JScrollPane interestSubCategoryScrollPane;
    private JList interestSubCategoryJlist;
    private JLabel categoriesJlabel;
    private JLabel subCategoriesJlabel;
    private JList gradeJlist;
    private JScrollPane gradeJScrollPane;
    private JScrollPane departmentScrollPanel;
    private JList departmentJlist;
    private JButton ProfilePictureSubmitButton;
    private JLabel currentUsernameJlabel;
    private final JFrame myMainFrame;

    public mainSettingsMenu(JFrame myMainFrame, user currentUser) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;

        usernameJtextField.setText(currentUser.getUsername());
        nameSurnameJtextfield.setText(currentUser.getNameSurname());
        ageJTextField.setText(String.valueOf(currentUser.getAge()));
        gradeJlist.setSelectedValue("PREP", true);


        usernameSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!usernameJtextField.getText().equals(currentUser.getUsername()) && !usernameJtextField.getText().isEmpty()) {
                    try {
                        changeUsernameOnDatabase(usernameJtextField.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(myMainFrame, "Use a valid username!", "Username Change", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        nameSurnameSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!nameSurnameJtextfield.getText().equals(currentUser.getNameSurname()) && !nameSurnameJtextfield.getText().isEmpty()) {
                    try {
                        changeNameSurnameOnDatabase(nameSurnameJtextfield.getText());

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(myMainFrame, "Use a valid Name/Surname!", "Name/Surname Change", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        ageSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!ageJTextField.getText().equals(String.valueOf(currentUser.getAge())) && !ageJTextField.getText().isEmpty() && ageJTextField.getText().matches("[0-9]+")) {
                    try {
                        changeAgeOnDatabase(Integer.parseInt(ageJTextField.getText()));

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(myMainFrame, "Use a valid Grade!", "Grade Change", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        gradeSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!gradeJlist.getSelectedValue().toString().isEmpty() && !gradeJlist.getSelectedValue().toString().equals(currentUser.getGrade())) {
                    try {
                        changeGradeOnDatabase(gradeJlist.getSelectedValue().toString());

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(myMainFrame, "Use a valid Age!", "Age Change", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
    }

    private void changeGradeOnDatabase(String gradeNew) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String changeGradeSql = "UPDATE users SET grade = ? WHERE user_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(changeGradeSql);
            statement.setString(1, gradeNew);
            statement.setInt(2, currentUser.getUserID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                currentUser.setGrade(Integer.parseInt(gradeNew));
                JOptionPane.showMessageDialog(myMainFrame, "Successfully Updated Grade", "Grade Change", JOptionPane.INFORMATION_MESSAGE);
                myMainFrame.revalidate();
                myMainFrame.repaint();

            } else {
                JOptionPane.showMessageDialog(myMainFrame, "Error Updating Grade", "Grade Change", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeAgeOnDatabase(int ageNew) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String changeAgeSql = "UPDATE users SET age = ? WHERE user_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(changeAgeSql);
            statement.setString(1, String.valueOf(ageNew));
            statement.setInt(2, currentUser.getUserID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                currentUser.setAge(ageNew);
                JOptionPane.showMessageDialog(myMainFrame, "Successfully Updated Age", "Age Change", JOptionPane.INFORMATION_MESSAGE);
                myMainFrame.revalidate();
                myMainFrame.repaint();

            } else {
                JOptionPane.showMessageDialog(myMainFrame, "Error Updating Age", "Age Change", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeNameSurnameOnDatabase(String nameSurnameNew) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String changeNameSurnameSql = "UPDATE users SET name_surname = ? WHERE user_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(changeNameSurnameSql);
            statement.setString(1, nameSurnameNew);
            statement.setInt(2, currentUser.getUserID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                currentUser.setNameSurname(nameSurnameNew);
                JOptionPane.showMessageDialog(myMainFrame, "Successfully Updated Name/Surname", "Name/Surname Change", JOptionPane.INFORMATION_MESSAGE);
                myMainFrame.revalidate();
                myMainFrame.repaint();

            } else {
                JOptionPane.showMessageDialog(myMainFrame, "Error Updating Name/Surname", "Name/Surname Change", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeUsernameOnDatabase(String usernameNew) throws SQLException {

        Connection connection = DatabaseManager.getConnection();
        String changeUsernameSql = "UPDATE users SET username = ? WHERE user_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(changeUsernameSql);
            statement.setString(1, usernameNew);
            statement.setInt(2, currentUser.getUserID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                currentUser.setUsername(usernameNew);
                JOptionPane.showMessageDialog(myMainFrame, "Successfully Updated Username", "Username Change", JOptionPane.INFORMATION_MESSAGE);
                myMainFrame.revalidate();
                myMainFrame.repaint();

            } else {
                JOptionPane.showMessageDialog(myMainFrame, "Error Updating Username", "Username Change", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
