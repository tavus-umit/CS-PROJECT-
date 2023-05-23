package Bilkay;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MessageViewer extends JFrame {
    private JPanel messagePanel;
    private JScrollPane scrollPane;

    public MessageViewer() {
        setTitle("Message Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));

        // Create a JPanel to hold the messages
        messagePanel = new JPanel();
        messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));

        // Create a JScrollPane and add the messagePanel to it
        scrollPane = new JScrollPane(messagePanel);

        // Add the JScrollPane to the frame's content pane
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    public void displayMessage(String message) {
        // Create a JLabel for the message with desired styling
        JLabel messageLabel = new JLabel(message);
        messageLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Add a separator after each message
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(messagePanel.getWidth(), 1));

        // Add the message label and separator to the messagePanel
        messagePanel.add(messageLabel);
        messagePanel.add(separator);

        // Refresh the layout and scroll to the bottom
        messagePanel.revalidate();
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MessageViewer viewer = new MessageViewer();
            viewer.pack();
            viewer.setVisible(true);

            // Simulating received messages
            List<String> messages = new ArrayList<>();
            messages.add("Message 1");
            messages.add("Message 2");
            messages.add("Message 3");
            messages.add("Message 3");
            messages.add("Message 3");
            messages.add("Message 3");
            messages.add("Message 3");
            messages.add("Message 3");
            messages.add("Message 3");


            // Display received messages
            for (String message : messages) {
                viewer.displayMessage(message);
            }
        });
    }
}