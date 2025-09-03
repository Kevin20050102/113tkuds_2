class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;

        int i = 0; // i 指向最後一個唯一元素的位置

        for (int j = 1; j < nums.length; j++) {
            // 如果遇到新的唯一值，就放到 i+1 的位置
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1; // 長度是索引 + 1
    }
}
