class Solution {
    public String countAndSay(int n) {
        // base case
        if (n == 1) return "1";

        String result = "1";
        for (int i = 2; i <= n; i++) {
            result = getNext(result);
        }
        return result;
    }

    // 幫助函數：對字串做 RLE 壓縮
    private String getNext(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                sb.append(count).append(s.charAt(i - 1));
                count = 1;
            }
        }
        return sb.toString();
    }
}
