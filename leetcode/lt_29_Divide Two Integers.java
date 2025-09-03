class Solution {
    public int divide(int dividend, int divisor) {
        // 邊界處理：避免溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE; // 超過範圍，回傳最大值
        }
        if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE; // 最小值除以 1，直接回傳
        }

        // 判斷結果正負號
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 全部轉換成 long，再取絕對值，避免溢出
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;

        // 核心：使用「位移」加速減法
        while (a >= b) {
            long temp = b;
            int multiple = 1;

            // 找到最大 2^k 倍的 divisor，使得仍 <= dividend
            while (a >= (temp << 1)) {
                temp <<= 1;       // divisor 乘 2
                multiple <<= 1;   // 對應倍數也乘 2
            }

            // 減掉這個部分，並把倍數加進答案
            a -= temp;
            result += multiple;
        }

        // 根據正負號返回答案
        return negative ? -result : result;
    }
}
