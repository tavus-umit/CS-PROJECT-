package Bilkay.UserRelatedServices;

import javax.swing.*;
import java.awt.*;

import static Bilkay.UserRelatedServices.userDbPull.getChosenCategoriesFromUserID;
import static Bilkay.UserRelatedServices.userDbPull.getChosenSubCategoriesFromUserID;

public class userProfileDialog extends JDialog {
    private final JFrame myMainFrame;
    private final user currentUser;
    private JPanel contentPane;
    private JPanel contentJpanel;
    private JPanel topJpanel;
    private JLabel userPPJlabel;
    private JLabel NameSurnameJLabel;
    private JPanel bottomJpanel;
    private JPanel interstJpanel;
    private JScrollPane interestCategoryScrollPane;
    private JList<Category> interestsCategoryJList;
    private JScrollPane interestSubCategoryScrollPane;
    private JList<SubCategory> interestSubCategoryJlist;
    private JLabel categoriesJlabel;
    private JLabel subCategoriesJlabel;
    private JLabel ageJlabel;
    private JLabel genderJLabel;
    private JLabel departmentJlabel;
    private JLabel yearJlabel;
    private JLabel interestJLabel;

    public userProfileDialog(JFrame myMainFrameInput, user currentUser) {
        myMainFrame = myMainFrameInput;
        this.currentUser = currentUser;
        setContentPane(contentPane);
        setModal(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 300);

        setTitle("Profile");

        ImageIcon iconPP = new ImageIcon(new ImageIcon(currentUser.getPathToPP()).getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH));

        userPPJlabel.setIcon(iconPP);
        userPPJlabel.setHorizontalAlignment(SwingConstants.CENTER);
        userPPJlabel.setVerticalAlignment(SwingConstants.CENTER);

        NameSurnameJLabel.setText(currentUser.getNameSurname());

        genderJLabel.setText("Gender: " + currentUser.getGender());
        ageJlabel.setText("Age: " + currentUser.getAge());
        yearJlabel.setText("Grade: " + currentUser.getGrade());
        departmentJlabel.setText("Department: " + currentUser.getDepartment());

        DefaultListModel<Category> catModel = new DefaultListModel<>();
        for (Category cat : getChosenCategoriesFromUserID(currentUser.getUserID())) {
            catModel.addElement(cat);
        }

        interestsCategoryJList.setModel(catModel);

        DefaultListModel<SubCategory> subCatModel = new DefaultListModel<>();
        for (SubCategory subCat : getChosenSubCategoriesFromUserID(currentUser.getUserID())) {
            subCatModel.addElement(subCat);
        }

        interestSubCategoryJlist.setModel(subCatModel);

        pack();

        setLocationRelativeTo(myMainFrame);
        setVisible(true);
    }


}
