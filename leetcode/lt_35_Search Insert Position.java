class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid; // 找到目標
            } else if (nums[mid] < target) {
                left = mid + 1; // 去右邊
            } else {
                right = mid - 1; // 去左邊
            }
        }
        
        // 沒找到，left 就是應該插入的位置
        return left;
    }
}
