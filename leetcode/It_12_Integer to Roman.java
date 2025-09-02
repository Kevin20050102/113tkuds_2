class Solution {
    public String intToRoman(int num) {
        // 羅馬數字的所有可能組合 (含特殊情況 4, 9, 40, 90, 400, 900)
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {
            "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"
        };

        StringBuilder sb = new StringBuilder();

        // 從最大數字開始處理
        for (int i = 0; i < values.length; i++) {
            // 當前數字還大於等於 values[i]，就扣掉並加上對應符號
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }
}

