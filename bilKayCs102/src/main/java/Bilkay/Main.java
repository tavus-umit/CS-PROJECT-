import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Register Page");
        GridLayout gl = new GridLayout(3,2);
        RegisterPanel rp = new RegisterPanel();
        rp.setLayout(gl);
        frame.add(rp);
        frame.setSize(350,150);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}