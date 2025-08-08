import java.util.Scanner;

public class RecursiveMathCalculator {
    static long combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    static long catalan(int n) {
        if (n <= 1) return 1;
        long result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    static long hanoi(int n) {
        if (n == 1) return 1;
        return 2 * hanoi(n - 1) + 1;
    }

    static boolean isPalindrome(int num) {
        String s = String.valueOf(num);
        return isPalindromeHelper(s, 0, s.length() - 1);
    }

    static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeHelper(s, left + 1, right - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("輸入 n 和 k 計算組合數 C(n,k): ");
        int n = sc.nextInt(), k = sc.nextInt();
        System.out.println("C(" + n + "," + k + ") = " + combination(n, k));

        System.out.print("輸入 n 計算卡塔蘭數: ");
        n = sc.nextInt();
        System.out.println("Catalan(" + n + ") = " + catalan(n));

        System.out.print("輸入 n 計算漢諾塔步數: ");
        n = sc.nextInt();
        System.out.println("Hanoi(" + n + ") = " + hanoi(n));

        System.out.print("輸入數字判斷是否回文: ");
        int num = sc.nextInt();
        System.out.println(num + (isPalindrome(num) ? " 是回文" : " 不是回文"));
    }
}
