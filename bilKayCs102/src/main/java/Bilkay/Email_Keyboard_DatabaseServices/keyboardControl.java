package Bilkay.Email_Keyboard_DatabaseServices;

import java.awt.*;
import java.awt.event.KeyEvent;

public class keyboardControl {

    private Robot robot = null;

    public keyboardControl() {

        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }

    }

    public void holdCTRL() {
        this.robot.keyPress(KeyEvent.VK_CONTROL);
    }

    public void releaseCTRL() {
        this.robot.keyRelease(KeyEvent.VK_CONTROL);

    }
}
