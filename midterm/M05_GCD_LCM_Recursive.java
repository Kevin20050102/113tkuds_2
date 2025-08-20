import java.util.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long g = gcd(a, b);
        // 避免溢位：先除後乘
        long l = (a / g) * b;

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    /**
     * 遞迴歐幾里得算法
     * gcd(x, y) = gcd(y, x % y)
     * 當 y = 0 時回傳 x
     *
     * 時間複雜度: O(log(min(a, b)))
     *   - 每次遞迴至少把數字縮小一半（取餘數）
     *   - 最多約 log(min(a, b)) 層
     */
    private static long gcd(long x, long y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
}
