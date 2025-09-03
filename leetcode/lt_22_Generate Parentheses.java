import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>(); // 用來儲存所有合法的組合
        backtrack(result, "", 0, 0, n); // 開始回溯演算法
        return result; // 回傳結果
    }

    /**
     * 回溯方法
     * @param result 儲存結果的 list
     * @param current 當前建構中的字串
     * @param open 已使用的左括號數量
     * @param close 已使用的右括號數量
     * @param max 括號對數（n）
     */
    private void backtrack(List<String> result, String current, int open, int close, int max) {
        // 如果字串長度達到 2*n，代表已經用完所有括號，且為合法組合
        if (current.length() == max * 2) {
            result.add(current); // 將合法結果加入 result
            return;
        }

        // 如果還可以加入左括號（open < n）
        if (open < max) {
            backtrack(result, current + "(", open + 1, close, max); // 加入一個左括號，繼續遞迴
        }

        // 如果右括號數量還可以增加（但不能超過左括號）
        if (close < open) {
            backtrack(result, current + ")", open, close + 1, max); // 加入一個右括號，繼續遞迴
        }
    }
}
