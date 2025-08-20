import java.util.*;

public class M06_PalindromeClean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        if (isPalindrome(s)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    /**
     * 回文檢測（忽略非字母、大小寫）
     *
     * 複雜度分析：
     * - 雙指標左右各走一次，O(n)
     * - 空間 O(1)，不需要額外字串
     */
    private static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            // 左指標往右移到字母
            while (l < r && !Character.isLetter(s.charAt(l))) l++;
            // 右指標往左移到字母
            while (l < r && !Character.isLetter(s.charAt(r))) r--;

            if (l < r) {
                char left = Character.toLowerCase(s.charAt(l));
                char right = Character.toLowerCase(s.charAt(r));
                if (left != right) return false;
                l++;
                r--;
            }
        }
        return true;
    }
}
