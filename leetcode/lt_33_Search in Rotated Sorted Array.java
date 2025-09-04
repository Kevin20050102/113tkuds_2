class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // 找到目標
            if (nums[mid] == target) {
                return mid;
            }

            // 判斷哪一邊有序
            if (nums[left] <= nums[mid]) {
                // 左半邊有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // 在左半邊
                } else {
                    left = mid + 1;  // 在右半邊
                }
            } else {
                // 右半邊有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // 在右半邊
                } else {
                    right = mid - 1; // 在左半邊
                }
            }
        }

        return -1; // 沒找到
    }
}
