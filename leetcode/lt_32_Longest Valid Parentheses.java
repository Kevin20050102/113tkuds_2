import java.util.*;

class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // 初始基準點

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i); // 左括號索引入 stack
            } else {
                stack.pop(); // 嘗試匹配
                if (stack.isEmpty()) {
                    // 沒有匹配基準，更新基準點
                    stack.push(i);
                } else {
                    // 計算長度
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
