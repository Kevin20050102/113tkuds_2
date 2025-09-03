import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || words == null || words.length == 0) return result;

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) return result;

        // 1. 預先計算 words 中每個單字的頻率
        Map<String, Integer> wordMap = new HashMap<>();
        for (String w : words) {
            wordMap.put(w, wordMap.getOrDefault(w, 0) + 1);
        }

        // 2. 分組掃描 (避免錯位)
        for (int i = 0; i < wordLen; i++) {
            int left = i; // 左指標
            int count = 0; // 計算目前符合多少單字
            Map<String, Integer> windowMap = new HashMap<>();

            // 3. 向右滑動，每次取 wordLen 長度的子字串
            for (int j = i; j + wordLen <= s.length(); j += wordLen) {
                String word = s.substring(j, j + wordLen);

                if (wordMap.containsKey(word)) {
                    windowMap.put(word, windowMap.getOrDefault(word, 0) + 1);
                    count++;

                    // 如果某單字出現次數過多，縮小左邊界
                    while (windowMap.get(word) > wordMap.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // 如果剛好符合全部單字數，記錄答案
                    if (count == wordCount) {
                        result.add(left);
                        // 繼續往前移動一格
                        String leftWord = s.substring(left, left + wordLen);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }
                } else {
                    // 遇到不在 words 裡的單字，重置
                    windowMap.clear();
                    count = 0;
                    left = j + wordLen;
                }
            }
        }

        return result;
    }
}
