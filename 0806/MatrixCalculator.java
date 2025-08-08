import java.util.*;

public class MatrixCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 輸入第一個矩陣
        System.out.print("輸入矩陣1的列數: ");
        int r1 = sc.nextInt();
        System.out.print("輸入矩陣1的行數: ");
        int c1 = sc.nextInt();
        int[][] m1 = new int[r1][c1];
        System.out.println("輸入矩陣1的元素：");
        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c1; j++) {
                m1[i][j] = sc.nextInt();
            }
        }

        // 輸入第二個矩陣
        System.out.print("輸入矩陣2的列數: ");
        int r2 = sc.nextInt();
        System.out.print("輸入矩陣2的行數: ");
        int c2 = sc.nextInt();
        int[][] m2 = new int[r2][c2];
        System.out.println("輸入矩陣2的元素：");
        for (int i = 0; i < r2; i++) {
            for (int j = 0; j < c2; j++) {
                m2[i][j] = sc.nextInt();
            }
        }

        // 矩陣加法
        if (r1 == r2 && c1 == c2) {
            System.out.println("\n矩陣加法結果：");
            int[][] sum = new int[r1][c1];
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c1; j++) {
                    sum[i][j] = m1[i][j] + m2[i][j];
                    System.out.print(sum[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("\n矩陣無法加法（維度不符）");
        }

        // 矩陣乘法
        if (c1 == r2) {
            System.out.println("\n矩陣乘法結果：");
            int[][] product = new int[r1][c2];
            for (int i = 0; i < r1; i++) {
                for (int j = 0; j < c2; j++) {
                    for (int k = 0; k < c1; k++) {
                        product[i][j] += m1[i][k] * m2[k][j];
                    }
                    System.out.print(product[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("\n矩陣無法相乘（行列不符）");
        }

        // 矩陣轉置
        System.out.println("\n矩陣1的轉置：");
        for (int j = 0; j < c1; j++) {
            for (int i = 0; i < r1; i++) {
                System.out.print(m1[i][j] + " ");
            }
            System.out.println();
        }

        // 最大值與最小值
        int max = m1[0][0], min = m1[0][0];
        for (int[] row : m1) {
            for (int val : row) {
                if (val > max) max = val;
                if (val < min) min = val;
            }
        }
        System.out.println("\n矩陣1最大值: " + max);
        System.out.println("矩陣1最小值: " + min);
    }
}
