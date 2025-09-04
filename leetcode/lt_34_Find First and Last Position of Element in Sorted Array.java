class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) return result;

        int left = findFirst(nums, target);
        if (left == -1) return result; // 沒找到直接回傳

        int right = findLast(nums, target);

        result[0] = left;
        result[1] = right;
        return result;
    }

    // 找第一個出現的位置
    private int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
            if (nums[mid] == target) index = mid; // 記錄可能的答案
        }
        return index;
    }

    // 找最後一個出現的位置
    private int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            if (nums[mid] == target) index = mid; // 記錄可能的答案
        }
        return index;
    }
}
