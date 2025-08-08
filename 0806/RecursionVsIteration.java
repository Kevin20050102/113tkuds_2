
public class RecursionVsIteration {
    static long binomialRec(int n, int k) {
        if (k == 0 || k == n) return 1;
        return binomialRec(n - 1, k - 1) + binomialRec(n - 1, k);
    }
    static long binomialIter(int n, int k) {
        long[][] dp = new long[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= Math.min(i, k); j++) {
                if (j == 0 || j == i) dp[i][j] = 1;
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }

    static long productRec(int[] arr, int index) {
        if (index == arr.length) return 1;
        return arr[index] * productRec(arr, index + 1);
    }
    static long productIter(int[] arr) {
        long prod = 1;
        for (int num : arr) prod *= num;
        return prod;
    }

    static int vowelRec(String s, int index) {
        if (index == s.length()) return 0;
        char ch = Character.toLowerCase(s.charAt(index));
        int count = "aeiou".indexOf(ch) != -1 ? 1 : 0;
        return count + vowelRec(s, index + 1);
    }
    static int vowelIter(String s) {
        int count = 0;
        for (char ch : s.toLowerCase().toCharArray()) {
            if ("aeiou".indexOf(ch) != -1) count++;
        }
        return count;
    }

    static boolean bracketsRec(String s, int index, int balance) {
        if (balance < 0) return false;
        if (index == s.length()) return balance == 0;
        char ch = s.charAt(index);
        if (ch == '(') return bracketsRec(s, index + 1, balance + 1);
        if (ch == ')') return bracketsRec(s, index + 1, balance - 1);
        return bracketsRec(s, index + 1, balance);
    }
    static boolean bracketsIter(String s) {
        int balance = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') balance++;
            else if (ch == ')') balance--;
            if (balance < 0) return false;
        }
        return balance == 0;
    }

    public static void main(String[] args) {
        int[] arr = {2, 3, 4};
        System.out.println("遞迴乘積: " + productRec(arr, 0));
        System.out.println("迭代乘積: " + productIter(arr));
    }
}
