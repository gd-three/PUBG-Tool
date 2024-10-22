package pickitem.version0;

import javax.swing.*;
import java.awt.*;

// 只能窗体内，才能触发，没有实际使用价值
public class PickItem {

    public static void main(String[] args) throws AWTException {
        Robot robot = new Robot();
        JFrame frame = new JFrame("Keyboard Listener");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100, 100);
        frame.setVisible(true);
        frame.addKeyListener(new FKeyListen(robot));
    }
}

