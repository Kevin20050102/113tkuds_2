import java.util.*;

public class LC17_PhoneCombos_CSShift {
    static String[] map = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    static void dfs(String digits, int idx, StringBuilder path, List<String> res) {
        if (idx == digits.length()) {
            res.add(path.toString());
            return;
        }
        String letters = map[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c);
            dfs(digits, idx + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine().trim();
        if (digits.length() == 0) return;
        List<String> res = new ArrayList<>();
        dfs(digits, 0, new StringBuilder(), res);
        for (String s : res) System.out.println(s);
    }
}
