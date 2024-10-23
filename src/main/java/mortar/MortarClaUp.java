package mortar;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;
// 截图方式 测算迫击炮距离
public class MortarClaUp {
    static final double unit = 165D;
    // 缩放最大
    static final double unit1 = 290D;

    public static void main(String[] args) throws InterruptedException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        for (; ; ) {
            try {
                Transferable content = clipboard.getContents(null);
                if (content != null && content.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    BufferedImage ob = (BufferedImage) content.getTransferData(DataFlavor.imageFlavor);
//                    System.out.println(ob.getHeight());
//                    System.out.println(ob.getWidth());
                    double th = ob.getHeight() / unit1;
                    double tw = ob.getWidth() / unit1;
                    double sqrt = Math.sqrt(Math.pow(th, 2) + Math.pow(tw, 2));
                    System.out.printf("距离：%.1fm (未包含高低坡)", sqrt * 100);
                    System.out.println();
                    clipboard.setContents( new StringSelection(""),null);
                } else {
//                    System.out.println("content is not a image");
                }
            } catch (Exception e) {
                System.out.println("出错了");
            }
            TimeUnit.SECONDS.sleep(2);
        }
    }
}
