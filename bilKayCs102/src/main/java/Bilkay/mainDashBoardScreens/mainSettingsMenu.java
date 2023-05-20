package Bilkay.mainDashBoardScreens;

import Bilkay.Email_Keyboard_DatabaseServices.DatabaseManager;
import Bilkay.Email_Keyboard_DatabaseServices.emilSenderBilkay;
import Bilkay.UserRelatedServices.user;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

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
    private JLabel changeGenderJlist;
    private JScrollPane genderScrollPane;
    private JList genderJlist;
    private JButton genderSumbitButton;
    private JButton chooseFileForPPButton;
    private JButton ProfilePictureSubmitButton;
    private JLabel currentUsernameJlabel;
    private final JFrame myMainFrame;
    private File profilePictureFile;

    public mainSettingsMenu(JFrame myMainFrame, user currentUser) {
        this.currentUser = currentUser;
        this.myMainFrame = myMainFrame;

        usernameJtextField.setText(currentUser.getUsername());
        nameSurnameJtextfield.setText(currentUser.getNameSurname());
        ageJTextField.setText(String.valueOf(currentUser.getAge()));
        gradeJlist.setSelectedValue(currentUser.getGrade(), true);
        departmentJlist.setSelectedValue(currentUser.getDepartment(),true);
        genderJlist.setSelectedValue(currentUser.getGender(),true);


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
        departmentSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!departmentJlist.getSelectedValue().toString().isEmpty() && !departmentJlist.getSelectedValue().toString().equals(currentUser.getDepartment())) {
                    try {
                        changeDepartmentOnDatabase(departmentJlist.getSelectedValue().toString());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }else {
                    JOptionPane.showMessageDialog(myMainFrame, "Use a valid new Department!", "Department Change", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        genderSumbitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!genderJlist.getSelectedValue().toString().isEmpty() && !genderJlist.getSelectedValue().toString().equals(currentUser.getGender())) {
                    try {
                        changeGenderOnDatabase(genderJlist.getSelectedValue().toString());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }else {
                    JOptionPane.showMessageDialog(myMainFrame, "Use a valid new Gender!", "Age Gender", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        passwordSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                char[] passwordArray = passwordPField.getPassword();
                StringBuilder SettingsPasswordBuilder = new StringBuilder();
                for (char c : passwordArray) {
                    SettingsPasswordBuilder.append(c);
                }
                String passwordNew = SettingsPasswordBuilder.toString();



                if (!passwordNew.isEmpty() && !passwordNew.equals(currentUser.getGender())) {
                    try {

                        emilSenderBilkay sendEmails = new emilSenderBilkay();

                        if(emilSenderBilkay.isEmailValid(currentUser.getWebmail()))
                        {
                            Random rand = new Random();
                            long VerifyCode = rand.nextLong(100000,1000000);

                            String emailBodyTextForCode = "Your Bilkay Verification Code For Password Change is: " + VerifyCode;
                            String emailSubjectTextForCode = "Bilkay Verification Code For Password Change";

                            if (sendEmails.sendEmail(currentUser.getWebmail(), emailSubjectTextForCode, emailBodyTextForCode))
                            {
                                String code = JOptionPane.showInputDialog(myMainFrame, "Enter your 6-digit verification code", "Verification Code", JOptionPane.INFORMATION_MESSAGE);
                                if (!code.isEmpty()) {
                                    if(Long.parseLong(code) == VerifyCode)
                                    {
                                        JOptionPane.showMessageDialog(myMainFrame, "Your webmail is successfully verified", "Webmail Verification", JOptionPane.INFORMATION_MESSAGE);
                                        changePasswordOnDatabase(passwordNew);

                                    }
                                    else {
                                        JOptionPane.showMessageDialog(myMainFrame,"Wrong Verification Code", "Webmail Validation Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                }
                            }
                        }
                        else {
                            JOptionPane.showMessageDialog(myMainFrame,"Please use your Bilkent Webmail", "Webmail Validation Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                }else {
                    JOptionPane.showMessageDialog(myMainFrame, "Use a valid new password!", "Password Change", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        chooseFileForPPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooseProfilePicture = new JFileChooser();

                if (chooseProfilePicture.showOpenDialog(myMainFrame) == JFileChooser.APPROVE_OPTION) {
                    profilePictureFile = new File(chooseProfilePicture.getSelectedFile().getAbsolutePath());
                    if (profilePictureFile.getName().endsWith(".png") || profilePictureFile.getName().endsWith(".jpeg") || profilePictureFile.getName().endsWith(".jpg")) {
                        chooseFileForPPButton.setForeground(new Color(255,255,235));
                        chooseFileForPPButton.setBackground(new Color(40,40,43));
                        chooseFileForPPButton.setText("Image has been chosen");

                    } else {
                        JOptionPane.showMessageDialog(myMainFrame, "Use a valid Image File!", "Profile Picture Change", JOptionPane.INFORMATION_MESSAGE);
                        profilePictureFile = null;
                        return;

                    }
                }
            }
        });
        profilePictureSumbitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
    private void changePictureOnDatabase(File pictureFile) throws SQLException, IOException {
        if (pictureFile != null) {

            FileInputStream profilePictureStream = new FileInputStream(pictureFile);

            Connection connection = DatabaseManager.getConnection();
            String changePictureSql = "UPDATE users SET profile_picture = ? WHERE user_id = ?";

            try {
                PreparedStatement statement = connection.prepareStatement(changePictureSql);
                statement.setBlob(1, profilePictureStream,pictureFile.length());
                statement.setInt(2, currentUser.getUserID());

                int rowsUpdated = statement.executeUpdate();
                profilePictureStream.close();
                if (rowsUpdated > 0) {




                    JOptionPane.showMessageDialog(myMainFrame, "Successfully Updated Profile Picture", "Grade Profile Picture", JOptionPane.INFORMATION_MESSAGE);
                    myMainFrame.revalidate();
                    myMainFrame.repaint();

                } else {
                    JOptionPane.showMessageDialog(myMainFrame, "Error Updating Profile Picture", "Grade Profile Picture", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(myMainFrame, "Choose a valid Image File!", "Profile Picture Change", JOptionPane.INFORMATION_MESSAGE);
            return;

        }

    }

    private void changePasswordOnDatabase(String passwordNew) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String changePasswordSql = "UPDATE users SET password = ? WHERE user_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(changePasswordSql);
            statement.setString(1, passwordNew);
            statement.setInt(2, currentUser.getUserID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                currentUser.setPassword(passwordNew);
                JOptionPane.showMessageDialog(myMainFrame, "Successfully Updated Password", "Grade Password", JOptionPane.INFORMATION_MESSAGE);
                myMainFrame.revalidate();
                myMainFrame.repaint();

            } else {
                JOptionPane.showMessageDialog(myMainFrame, "Error Updating Password", "Grade Password", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeGenderOnDatabase(String genderNew) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String changeGenderSql = "UPDATE users SET gender = ? WHERE user_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(changeGenderSql);
            statement.setString(1, genderNew);
            statement.setInt(2, currentUser.getUserID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                currentUser.setGender(genderNew);
                JOptionPane.showMessageDialog(myMainFrame, "Successfully Updated Gender", "Grade Gender", JOptionPane.INFORMATION_MESSAGE);
                myMainFrame.revalidate();
                myMainFrame.repaint();

            } else {
                JOptionPane.showMessageDialog(myMainFrame, "Error Updating Gender", "Grade Gender", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void changeDepartmentOnDatabase(String departmentNew) throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String changeDepartmentSql = "UPDATE users SET department = ? WHERE user_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(changeDepartmentSql);
            statement.setString(1, departmentNew);
            statement.setInt(2, currentUser.getUserID());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                currentUser.setDepartment(departmentNew);
                JOptionPane.showMessageDialog(myMainFrame, "Successfully Updated Department", "Grade Department", JOptionPane.INFORMATION_MESSAGE);
                myMainFrame.revalidate();
                myMainFrame.repaint();

            } else {
                JOptionPane.showMessageDialog(myMainFrame, "Error Updating Department", "Grade Department", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                currentUser.setGrade(gradeNew);
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
