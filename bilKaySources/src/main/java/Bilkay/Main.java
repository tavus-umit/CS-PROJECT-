package Bilkay;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {


        FlatMacDarkLaf.setup();

        JFrame haha = new JFrame();
        haha.pack();
        haha.setLayout(new GridLayout(1,1));
        haha.setVisible(true);
        haha.setSize(500,500);

        haha.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 3, 10, 10));

        panel.add(new JButton("Button"));
        panel.add(new JCheckBox("Checkbox"));
        panel.add(new JComboBox<>(new String[]{"Option 1", "Option 2", "Option 3"}));
        panel.add(new JRadioButton("Radio Button 1"));
        panel.add(new JRadioButton("Radio Button 2"));
        panel.add(new JRadioButton("Radio Button 3"));
        panel.add(new JSlider(JSlider.HORIZONTAL, 0, 100, 50));
        panel.add(new JSpinner(new SpinnerNumberModel(50, 0, 100, 1)));
        panel.add(new JLabel("Label"));
        panel.add(new JList<>(new String[]{"List Item 1", "List Item 2", "List Item 3"}));
        panel.add(new JTextArea("Text Area"));
        panel.add(new JTextField("Text Field"));
        panel.add(new JPasswordField("Password Field"));
        panel.add(new JScrollPane(new JTextArea("Scroll Pane")));
        panel.add(new JSeparator(JSeparator.VERTICAL));
        panel.add(new JSeparator(JSeparator.HORIZONTAL));
        panel.add(new JToolBar());
        panel.add(new JTable(new String[][]{{"Row 1 Col 1", "Row 1 Col 2"}, {"Row 2 Col 1", "Row 2 Col 2"}}, new String[]{"Column 1", "Column 2"}));
        panel.add(new JPopupMenu("Popup Menu"));
        panel.add(new JMenuBar());
        panel.add(new JProgressBar(0, 100));
        panel.add(new JToggleButton("Toggle Button"));
        panel.add(new JTree(new Object[]{"Tree Node 1", "Tree Node 2", "Tree Node 3"}));
        panel.add(new JColorChooser());
        panel.add(new JFileChooser());

        haha.add(panel);

    }
}
