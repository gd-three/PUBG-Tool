package pickitem.version2;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.concurrent.atomic.AtomicLong;

// 满足快速捡东西
public class PickItem implements NativeKeyListener {
    //    private final AtomicInteger curCount = new AtomicInteger();
    private final AtomicLong sum = new AtomicLong();
    private final static int EXPECT_COUNT = 300;
    private final static String TAB = "tab";
    private final static String _TAB = "Tab";
    private final static int WIDE = 250;
    private final static int WIDE_MOVE = 2200;
    private final static int HIGH = 185;
    private final static int HIGH_MOVE = 250;
    private final Robot robot = new Robot();

    public PickItem() throws AWTException {

    }

    public static void main(String[] args) throws AWTException {
        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new PickItem());
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nativeEvent) {
//        System.out.println("key type:" + nativeEvent.getKeyChar());
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
//        System.out.println("key pressed:" + NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()));
        String keyChar = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
        if (_TAB.equals(keyChar) || TAB.equals(keyChar)) {
            long sumL = sum.incrementAndGet();
            System.out.println("Key released: " + keyChar + " sum: " + sumL);
            if (sumL % 2 != 0) {
                for (int i = 0; i <= EXPECT_COUNT; i++) {
                    robot.mouseMove(WIDE, HIGH);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseMove(WIDE_MOVE, HIGH_MOVE);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }

            }
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
//        System.out.println("key released:" + NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()));
    }
}

