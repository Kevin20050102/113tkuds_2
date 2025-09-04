class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        
        // 1. 找到第一個下降點 i
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // 2. 如果找到 i，則找 j 並交換
        if (i >= 0) {
            int j = n - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // 3. 反轉 i+1 之後的序列
        reverse(nums, i + 1, n - 1);
    }

    // 工具函式：交換
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 工具函式：反轉區間 [start, end]
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
