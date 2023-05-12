package Bilkay;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;


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
                    for (String subCategoryNamesForVisualArt : SubCategory.subCategoryNamesForVisualArts) {
                        subCategoryItems.add(new SubCategory(subCategoryNamesForVisualArt, categoryItem));
                    }
                }
                case "Science" -> {
                    for (String subCategoryNamesForScienc : SubCategory.subCategoryNamesForScience) {
                        subCategoryItems.add(new SubCategory(subCategoryNamesForScienc, categoryItem));
                    }
                }
                case "Performing Arts" -> {
                    for (String subCategoryNamesForPerformArt : SubCategory.subCategoryNamesForPerformArts) {
                        subCategoryItems.add(new SubCategory(subCategoryNamesForPerformArt, categoryItem));
                    }
                }
                case "Social Sciences" -> {
                    for (String subCategoryNamesForSocialScience : SubCategory.subCategoryNamesForSocialSciences) {
                        subCategoryItems.add(new SubCategory(subCategoryNamesForSocialScience, categoryItem));
                    }
                }
                case "Physical Sports" -> {
                    for (String subCategoryNamesForPhysicalSport : SubCategory.subCategoryNamesForPhysicalSports) {
                        subCategoryItems.add(new SubCategory(subCategoryNamesForPhysicalSport, categoryItem));
                    }
                }
            }
        }
    }

    private void registerTheUser() {
        String username = usernameTextField.getText();

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

        if (!password.equals(confirmedPassword)) {
            JOptionPane.showMessageDialog(myMainFrame, "Passwords do not match.", "Register Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (interestsCategoryJList.getSelectedValuesList().isEmpty()) {
            JOptionPane.showMessageDialog(myMainFrame, "Your interests list is empty, please choose one or more.", "Register Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        myMainFrame.setContentPane(new mainDashboardMenu(myMainFrame).getMainPanelForMenu());
        myMainFrame.revalidate();
        myMainFrame.repaint();


        ArrayList<Category> chosenCategoryItems = (ArrayList<Category>) interestsCategoryJList.getSelectedValuesList();
        for (Category chosenCategoryItem : chosenCategoryItems) {
            System.out.println(chosenCategoryItem.getCategoryID());
        }

    }



    private void fillTheCategoryJList() {

        DefaultListModel<Category> interestCategoryModel = new DefaultListModel<>();
        for (Category items : categoryItems) {
            interestCategoryModel.addElement(items);
        }
        interestsCategoryJList.setModel(interestCategoryModel);
    }

    public JPanel getMainPanelForMenu() {
        return mainPanelForMenu;
    }


}
