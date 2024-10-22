package pickitem.version0;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FKeyListen implements KeyListener {
    private int curCount = 0;
    private final static int EXPECT_COUNT = 5;
    private final static char F = 'f';
    private final static char _F = 'F';
    private final Robot robot;

    public FKeyListen(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println("Key typed: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println("Key pressed: " + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        char keyChar = e.getKeyChar();
        if (keyChar == F || keyChar == _F) {
            System.out.println("Key released: " + keyChar);
            if (++curCount >= EXPECT_COUNT) {
                curCount = 0;
            } else {
                robot.keyPress(e.getKeyCode());
                robot.keyRelease(e.getKeyCode());
            }
        }
    }
}
