class Solution {
    public int strStr(String haystack, String needle) {
        // 先取長度，避免重複呼叫 length()
        int n = haystack.length();
        int m = needle.length();

        // 如果 needle 比 haystack 還長，直接不可能找到
        if (m > n) {
            return -1;
        }

        // 從 haystack 的第 0 到 n - m 位置開始檢查
        for (int i = 0; i <= n - m; i++) {
            // 取出長度 m 的子字串
            String sub = haystack.substring(i, i + m);

            // 如果這個子字串等於 needle，就回傳當前索引
            if (sub.equals(needle)) {
                return i;
            }
        }

        // 如果都沒有找到，回傳 -1
        return -1;
    }
}
