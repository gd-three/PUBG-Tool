package pickitem.version1;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

// 模拟f键 但是游戏有cd，捡东西还是慢
public class PickItem implements NativeKeyListener {
    private final AtomicInteger curCount = new AtomicInteger();
    private final AtomicLong sum = new AtomicLong();
    private final static int EXPECT_COUNT = 50;
    private final static String F = "f";
    private final static String _F = "F";
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
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nativeEvent) {
//        System.out.println("key released:" + NativeKeyEvent.getKeyText(nativeEvent.getKeyCode()));
        String keyChar = NativeKeyEvent.getKeyText(nativeEvent.getKeyCode());
        if (_F.equals(keyChar) || F.equals(keyChar)) {
            System.out.println("Key released: " + keyChar + " sum: " + sum.incrementAndGet());
            if (curCount.incrementAndGet() >= EXPECT_COUNT) {
                curCount.set(0);
            } else {
                try {
                    TimeUnit.MILLISECONDS.sleep(40);
                } catch (InterruptedException e) {
                }
                robot.keyPress(nativeEvent.getRawCode());
                robot.keyRelease(nativeEvent.getRawCode());

            }
        }
    }
}

