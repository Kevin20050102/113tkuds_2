class Solution {
    public int maxArea(int[] height) {
        // 左右指針初始化
        int left = 0;
        int right = height.length - 1;
        // 紀錄最大面積
        int maxArea = 0;

        // 當左右指針沒有相遇時持續計算
        while (left < right) {
            // 取左右指針的高度中較小者（因為水只能裝到矮的那一邊）
            int h = Math.min(height[left], height[right]);
            // 寬度 = 右指針 - 左指針
            int width = right - left;
            // 計算當前容器的面積
            int area = h * width;
            // 更新最大面積
            maxArea = Math.max(maxArea, area);

            // 移動較小高度的指針：
            // 這樣才有機會遇到更高的線，增加容器高度
            if (height[left] < height[right]) {
                left++;   // 左邊比較矮，往右移
            } else {
                right--;  // 右邊比較矮或相等，往左移
            }
        }

        // 回傳計算出的最大容器面積
        return maxArea;
    }
}