class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        // 先假設第一個字串是共同前綴
        String prefix = strs[0];

        // 從第二個字串開始，逐一比較
        for (int i = 1; i < strs.length; i++) {
            // 縮短 prefix，直到它是 strs[i] 的開頭
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }
}