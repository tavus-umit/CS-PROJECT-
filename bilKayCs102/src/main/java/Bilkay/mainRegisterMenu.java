package Bilkay;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;


public class mainRegisterMenu {

    private final JFrame myMainFrame;

    private final keyboardControl keyboardController;

    private JPanel mainPanelForMenu;
    private JPanel rightPanel;
    private JPanel rightCenter;
    private JTextField usernameTextField;
    private JLabel passwordLabel;
    private JButton registerButton;
    private JPasswordField passwordPasswordField;
    private JLabel userIcon;
    private JPanel leftPanel;
    private JPanel topLeftPanel;
    private JLabel logoLabel;
    private JPanel bottomLeftPanel;
    private JLabel mottoLabel;
    private JList<Category> interestsCategoryJList;
    private JScrollPane interestCategoryScrollPane;
    private JPasswordField confirmPasswordField;
    private JLabel passwordLabel2;
    private JLabel emailIconLabel;
    private JTextField emailJtextField;
    private JLabel nameSurbaneJlabel;
    private JTextField nameSurnameJTextField;
    private JPanel interstJpanel;
    private JList<SubCategory> interestSubCategoryJlist;
    private JScrollPane interestSubCategoryScrollPane;
    private JLabel categoriesJlabel;
    private JLabel subCategoriesJlabel;

    private ArrayList<Category> categoryItems;

    private ArrayList<SubCategory> subCategoryItems;


    public mainRegisterMenu(JFrame myMainFrameInput) {


        this.categoryItems = new ArrayList<Category>();
        this.subCategoryItems = new ArrayList<SubCategory>();

        createCategoryAndSubCategories();

        this.myMainFrame = myMainFrameInput;
        this.keyboardController = new keyboardControl();
        fillTheCategoryJList();

        interestsCategoryJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                keyboardController.holdCTRL();
            }
        });
        interestsCategoryJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                keyboardController.releaseCTRL();
            }
        });

        interestSubCategoryJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                keyboardController.holdCTRL();
            }
        });
        interestSubCategoryJlist.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                keyboardController.releaseCTRL();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                registerTheUser();

            }
        });
        interestsCategoryJList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateSubCategoryJList();
            }
        });
    }



    private void updateSubCategoryJList() {
        DefaultListModel<SubCategory> interestSubCategoryModel = new DefaultListModel<>();

        if (interestsCategoryJList.getSelectedValuesList().isEmpty()) {
            DefaultListModel<SubCategory> emptyModel = new DefaultListModel<>();
            interestSubCategoryJlist.setModel(emptyModel);
            return;
        }

        ArrayList<Category> chosenCategoryItems = (ArrayList<Category>) interestsCategoryJList.getSelectedValuesList();


        for (Category chosenCategoryItem : chosenCategoryItems) {
            for (SubCategory subcategory : chosenCategoryItem.getSubcategories()) {
                interestSubCategoryModel.addElement(subcategory);
            }
        }
        if (interestSubCategoryJlist.getSelectedValuesList().isEmpty()) {
            interestSubCategoryJlist.setModel(interestSubCategoryModel);

        } else {
            ArrayList<SubCategory> chosenSubCategoryItems = (ArrayList<SubCategory>) interestSubCategoryJlist.getSelectedValuesList();
            interestSubCategoryJlist.setModel(interestSubCategoryModel);

            for (SubCategory chosenSubCategoryItem : chosenSubCategoryItems) {
                int indexOfSub = interestSubCategoryModel.indexOf(chosenSubCategoryItem);
                if (indexOfSub >= 0) {
                    interestSubCategoryJlist.addSelectionInterval(indexOfSub,indexOfSub);
                }
            }
            chosenCategoryItems.clear();
        }

    }

    private void createCategoryAndSubCategories() {
        for (int i = 0; i < Category.categoryNames.length; i++) {
            categoryItems.add(new Category(Category.categoryNames[i]));
        }

        for (Category categoryItem : categoryItems) {
            switch (categoryItem.getName()) {
                case "Visual Arts" -> {
                    for (String x : SubCategory.subCategoryNamesForVisualArts) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Science" -> {
                    for (String x : SubCategory.subCategoryNamesForScience) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Performing Arts" -> {
                    for (String x : SubCategory.subCategoryNamesForPerformArts) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Social Sciences" -> {
                    for (String x : SubCategory.subCategoryNamesForSocialSciences) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Physical Sports" -> {
                    for (String x : SubCategory.subCategoryNamesForPhysicalSports) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Board and Card Games" -> {
                    for (String x : SubCategory.subCategoryNamesForBoardAndCardGames) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Music" -> {
                    for (String x : SubCategory.subCategoryNamesForMusic) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Cinema" -> {
                    for (String x : SubCategory.subCategoryNamesForCinema) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Outdoor Activities" -> {
                    for (String x : SubCategory.subCategoryNamesForOutdoorActivities) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Community Services" -> {
                    for (String x : SubCategory.subCategoryNamesForCommunityServices) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Writing" -> {
                    for (String x : SubCategory.subCategoryNamesForWritings) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
                case "Reading" -> {
                    for (String x : SubCategory.subCategoryNamesForReadings) {
                        subCategoryItems.add(new SubCategory(x, categoryItem));
                    }
                }
            }
        }
    }

    private void registerTheUser()  {

        String username = usernameTextField.getText();
        String nameSurname = nameSurnameJTextField.getText();
        String webmailAdress = emailJtextField.getText();

        if (webmailAdress.length() < 14) {
            JOptionPane.showMessageDialog(myMainFrame, "Enter a valid webmail address.", "Webmail Confirmation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        char[] passwordArray = passwordPasswordField.getPassword();
        StringBuilder passwordBuilder = new StringBuilder();
        for (char c : passwordArray) {
            passwordBuilder.append(c);
        }
        String password = passwordBuilder.toString();

        char[] confirmPasswordArray = confirmPasswordField.getPassword();
        StringBuilder confirmPasswordBuilder = new StringBuilder();
        for (char c : confirmPasswordArray) {
            confirmPasswordBuilder.append(c);
        }
        String confirmedPassword = confirmPasswordBuilder.toString();


        if (usernameTextField.getText().isEmpty() || nameSurnameJTextField.getText().isEmpty() || emailJtextField.getText().isEmpty() ||
                password.isEmpty() || confirmedPassword.isEmpty()) {
            JOptionPane.showMessageDialog(myMainFrame, "Your credentials are empty, please fill all of it.", "Credentials Error", JOptionPane.ERROR_MESSAGE);
            return;
        }



        if (!password.equals(confirmedPassword)) {
            JOptionPane.showMessageDialog(myMainFrame, "Passwords do not match.", "Password Confirmation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (interestsCategoryJList.getSelectedValuesList().isEmpty() || interestSubCategoryJlist.getSelectedValuesList().isEmpty() ) {
            JOptionPane.showMessageDialog(myMainFrame, "Your interests category or subcategory list is empty, please choose one or more.", "Interests Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        ArrayList<Category> chosenCategoryItems = (ArrayList<Category>) interestsCategoryJList.getSelectedValuesList();
        for (Category chosenCategoryItem : chosenCategoryItems) {
            System.out.println(chosenCategoryItem.getCategoryID());
        }


        if(isEmailValid(webmailAdress))
        {
            Random rand = new Random();
            long VerifyCode = rand.nextLong(100000,1000000);
            emilSenderBilkay sendEmails = new emilSenderBilkay();

            String emailBodyTextForCode = "Your Bilkay Verification Code is: " + VerifyCode;
            String emailSubjectTextForCode = "Bilkay Verification Code";

            if (sendEmails.sendEmail(webmailAdress, emailSubjectTextForCode, emailBodyTextForCode))
            {
                String code = JOptionPane.showInputDialog(myMainFrame, "Enter the verification code", "Verification Code", JOptionPane.INFORMATION_MESSAGE);
                if (!code.isEmpty()) {
                    if(Long.parseLong(code) == VerifyCode)
                    {
                        JOptionPane.showMessageDialog(myMainFrame, "Your webmail is successfully verified", "Webmail Verification", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(myMainFrame,"Wrong Verification Code", "Webmail Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"Please use your Bilkent Webmail", "Webmail Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        myMainFrame.setContentPane(new mainDashboardMenu(myMainFrame).getMainPanelForMenu());
        myMainFrame.revalidate();
        myMainFrame.repaint();

    }


    private void fillTheCategoryJList() {

        DefaultListModel<Category> interestCategoryModel = new DefaultListModel<>();
        for (Category items : categoryItems) {
            interestCategoryModel.addElement(items);
        }
        interestsCategoryJList.setModel(interestCategoryModel);
    }

    public boolean isEmailValid(String email)
    {
        String domainAddress = email.substring(email.length()-14);
        return domainAddress.equals("bilkent.edu.tr");

    }
    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
