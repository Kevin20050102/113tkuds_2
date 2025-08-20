import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 測試筆數
        long totalTax = 0;    // 累計總稅額

        for (int i = 0; i < n; i++) {
            long income = sc.nextLong();
            long tax = computeTax(income);
            totalTax += tax;
            System.out.println("Tax: " + tax);
        }

        // 計算平均稅額（整數）
        System.out.println("Average: " + (totalTax / n));
    }

    /**
     * 計算稅額（依簡化級距逐段累加）
     * 區間:
     *   0–120000        5%
     *   120001–500000   12%
     *   500001–1000000  20%
     *   1000001+        30%
     *
     * 複雜度: O(1) 固定檢查 4 段
     */
    private static long computeTax(long income) {
        long tax = 0;

        if (income <= 120000) {
            tax = Math.round(income * 0.05);
        } else if (income <= 500000) {
            tax = Math.round(120000 * 0.05 + (income - 120000) * 0.12);
        } else if (income <= 1000000) {
            tax = Math.round(120000 * 0.05 + (500000 - 120000) * 0.12
                    + (income - 500000) * 0.20);
        } else {
            tax = Math.round(120000 * 0.05 + (500000 - 120000) * 0.12
                    + (1000000 - 500000) * 0.20
                    + (income - 1000000) * 0.30);
        }

        return tax;
    }
}
