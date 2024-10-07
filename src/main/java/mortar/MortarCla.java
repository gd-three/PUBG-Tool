package mortar;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MortarCla {
    public static void main(String[] args) {
        for (; ; ) {
            try {
                Scanner scanner = new Scanner(System.in);
                String nextLine = scanner.nextLine();
                List<Float> collect = Arrays.stream(nextLine.split(" ")).map(v -> Float.parseFloat(v.trim())).collect(Collectors.toList());
                double sqrt = Math.sqrt(Math.pow(collect.get(0), 2) + Math.pow(collect.get(1), 2));
                System.out.printf("距离：%.1fm", sqrt * 100);
                System.out.println();
            } catch (Exception e) {
                System.out.println("操作错误");
            }
        }
    }
}
