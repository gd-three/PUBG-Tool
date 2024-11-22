package ccw;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class CCW implements NativeKeyListener {
    private final static int C = 67;
    private final static int W = 87;
    private final static int SHIT = 16;
    private final static int UP = 38;
    private static Robot robot;
    private volatile static boolean start = true;
    private static int time = 0;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public CCW() throws AWTException {
    }


    public static void main(String[] args) throws AWTException {
        try {
            GlobalScreen.registerNativeHook();
        } catch (Exception e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new CCW());
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
        if (nativeEvent.getRawCode() == UP) {
            start = true;
            if (++time % 2 == 0) {
                start = false;
            } else {
                new Thread(() -> {
                    for (; ; ) {
                        if (!start)
                            return;
                        try {
                            robot.keyPress(W);
                            robot.keyPress(SHIT);
                            TimeUnit.MILLISECONDS.sleep(1500);
                            robot.keyRelease(W);
                            robot.keyRelease(SHIT);
                            TimeUnit.MILLISECONDS.sleep(20);
                            robot.keyPress(C);
                            TimeUnit.MILLISECONDS.sleep(10);
                            robot.keyRelease(C);
                            TimeUnit.MILLISECONDS.sleep(10);
                            robot.keyPress(C);
                            TimeUnit.MILLISECONDS.sleep(10);
                            robot.keyRelease(C);
                            TimeUnit.MILLISECONDS.sleep(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        }
    }

}
