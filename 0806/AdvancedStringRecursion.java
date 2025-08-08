import java.util.*;

public class AdvancedStringRecursion {
    static void permute(String str, String ans) {
        if (str.isEmpty()) {
            System.out.println(ans);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String rest = str.substring(0, i) + str.substring(i + 1);
            permute(rest, ans + ch);
        }
    }

    static boolean match(String text, String pattern) {
        if (pattern.isEmpty()) return true;
        if (text.isEmpty()) return false;
        if (text.startsWith(pattern)) return true;
        return match(text.substring(1), pattern);
    }

    static String removeDuplicates(String str, Set<Character> seen) {
        if (str.isEmpty()) return "";
        char ch = str.charAt(0);
        if (seen.contains(ch)) return removeDuplicates(str.substring(1), seen);
        seen.add(ch);
        return ch + removeDuplicates(str.substring(1), seen);
    }

    static void substrings(String str, String current, int index) {
        if (index == str.length()) {
            if (!current.isEmpty()) System.out.println(current);
            return;
        }
        substrings(str, current + str.charAt(index), index + 1);
        substrings(str, current, index + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("輸入字串產生排列組合: ");
        permute(sc.nextLine(), "");

        System.out.print("輸入 text: ");
        String text = sc.nextLine();
        System.out.print("輸入 pattern: ");
        String pattern = sc.nextLine();
        System.out.println("是否匹配: " + match(text, pattern));

        System.out.print("輸入字串移除重複字符: ");
        System.out.println(removeDuplicates(sc.nextLine(), new HashSet<>()));

        System.out.print("輸入字串產生所有子字串: ");
        substrings(sc.nextLine(), "", 0);
    }
}
